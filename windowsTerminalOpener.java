import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class windowsTerminalOpener extends JFrame {

  private static Process process;
  private static final int CONNECTION_TIMEOUT_MS = 60000; // 10 seconds timeout
  private static boolean success = false;

  public windowsTerminalOpener(boolean loginGUI, boolean restart)
    throws HeadlessException, InterruptedException {
    if (loginGUI) {
      // If called from LoginGUI, automatically execute the initiated database button
      if (startDatabase()) {
        new LoginGUI();
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Failed to initiate the database.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      }
    } else if (restart) {
      if (startDatabase()) {
        success = true;
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Failed to initiate the database.",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      }
    } else {
      // Show the GUI when not called from LoginGUI
      setTitle("Database GUI");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);

      JPanel panel = new JPanel();

      JButton startButton = new JButton("Initiate Database");
      startButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            dispose();
            try {
              if (startDatabase()) {
                new LoginGUI();
              } else {
                JOptionPane.showMessageDialog(
                  null,
                  "Failed to initiate the database.",
                  "Error",
                  JOptionPane.ERROR_MESSAGE
                );
              }
            } catch (HeadlessException | InterruptedException e1) {
              // TODO Auto-generated catch block
              e1.printStackTrace();
            }
          }
        }
      );

      JButton closeButton = new JButton("Close Window");
      closeButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            closeWindow();
          }
        }
      );

      panel.add(startButton);
      panel.add(closeButton);

      getContentPane().add(panel, BorderLayout.CENTER);
      pack();
      setLocationRelativeTo(null);
      setVisible(true);
    }
  }

  private boolean isDatabaseReady() {
    // Check if the MySQL server is ready by trying to connect to it
    try (Socket socket = new Socket()) {
      socket.connect(
        new InetSocketAddress("localhost", 3306),
        CONNECTION_TIMEOUT_MS
      );
      return true;
    } catch (IOException e) {
      return false;
    }
  }

  public static Process getProcess() {
    return process;
  }

  public static boolean isSuccess() {
    return success;
  }

  private boolean startDatabase() throws InterruptedException {
    String[] StartScript = { "cmd.exe", "/c", "sc", "start", "MySQL80" };
    String[] StatusScript = { "cmd.exe", "/c", "sc", "query", "MySQL80" };

    try {
      // Start the service
      Process start = Runtime.getRuntime().exec(StartScript);
      start.waitFor(); // Wait for the start process to complete

      // If the start process exited with a non-zero value, the start failed
      if (start.exitValue() < 0) {
        return false;
      }

      // Check the status of the service
      Process status = Runtime.getRuntime().exec(StatusScript);
      BufferedReader reader = new BufferedReader(
        new InputStreamReader(status.getInputStream())
      );
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.contains("RUNNING")) {
          return true;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return false; // If we reach this point, starting the service failed
  }

  public static void stopDatabase() {
    process.destroy();
  }

  private void closeWindow() {
    if (process != null) {
      process.destroy();
    }
    dispose();
  }

  public static void main(String[] args) {
    try {
      new windowsTerminalOpener(false, false);
    } catch (HeadlessException | InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
