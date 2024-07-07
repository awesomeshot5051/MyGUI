import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

public class windowsTerminalOpener extends JFrame {

    private static final int CONNECTION_TIMEOUT_MS = 10000; // 10 seconds timeout
    private static Process process;
    private static boolean success = false;
    private static String status;
    private static JFrame welcomeWindow;

    public windowsTerminalOpener(boolean loginGUI, boolean restart) throws HeadlessException, InterruptedException {
        status = checkStatus();
        JTextField statusField = new JTextField("Current Status: " + status);
        statusField.setEditable(false);
        status = "running";
        if (loginGUI && !restart) {
            // If called from LoginGUI, automatically execute the initiated database button
            if (status.equalsIgnoreCase("stopped")) {
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
            } else {
                if (restart) {
                    if (restartDatabase()) {
                        success = true;
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to restart the database.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
        } else {
            // Show the GUI when not called from LoginGUI
            setTitle("Database GUI");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);

            JButton startButton = new JButton("Initiate Database");
            startButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    try {
                        if (startDatabase()) {
                            setVisible(true);
                            statusField.setText("Current Status: " + status);
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Failed to initiate the database.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } catch (HeadlessException | InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            JButton restartButton = new JButton("Restart Database");
            restartButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        if (restartDatabase()) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Database restarted successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            statusField.setText("Current Status: " + status);
                        } else {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Failed to restart the database.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            startOrStop();
                            success = false;
                        }
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });

            JButton stopButton = new JButton("Stop Database");
            stopButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (stopDatabase()) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Database stopped successfully.",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                        statusField.setText("Current Status: " + status);
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "Failed to stop the database.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        startOrStop();
                        success = false;
                    }
                }
            });

            JButton closeButton = new JButton("Close Window");
            closeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    closeWindow();
                }
            });


            JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, 10px gaps
            panel.add(startButton);
            panel.add(stopButton);
            panel.add(restartButton);
            panel.add(closeButton);

            getContentPane().add(statusField, BorderLayout.NORTH);
            getContentPane().add(panel, BorderLayout.CENTER);
            getContentPane().add(panel, BorderLayout.CENTER);
            pack();
            setLocationRelativeTo(null);
            setVisible(true);
        }
    }

    public static void setWelcomeWindow(JFrame frame) {
        welcomeWindow = frame;
    }

    private static String checkStatus() {
        try {
            // Check the status of the service
            String[] statusScript = {"cmd.exe", "/c", "sc", "query", "MySQL84"};
            Process status = Runtime.getRuntime().exec(statusScript);
            BufferedReader reader = new BufferedReader(new InputStreamReader(status.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("STATE")) {
                    // Extract the state information
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length > 3) {
                        return parts[3]; // The state is usually the 4th element
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "UNKNOWN"; // Return "UNKNOWN" if the status could not be determined
    }

    public static Process getProcess() {
        return process;
    }

    public static boolean isSuccess() {
        return success;
    }

    private static boolean startDatabase() throws InterruptedException {
        String[] startScript = {"cmd.exe", "/c", "sc", "start", "MySQL84"};
        return executeServiceCommand(startScript, "RUNNING");
    }

    private static boolean stopDatabase() {
        String[] stopScript = {"cmd.exe", "/c", "sc", "stop", "MySQL84"};
        return executeServiceCommand(stopScript, "STOPPED");
    }

    private static boolean executeServiceCommand(String[] script, String expectedStatus) {
        try {
            // Execute the service command
            Process process = Runtime.getRuntime().exec(script);
            process.waitFor(); // Wait for the process to complete
            Thread.sleep(3000);
            // Check the status of the service
            String[] statusScript = {"cmd.exe", "/c", "sc", "query", "MySQL84"};
            Process Status = Runtime.getRuntime().exec(statusScript);
            BufferedReader reader = new BufferedReader(new InputStreamReader(Status.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(expectedStatus)) {
                    status = expectedStatus;
                    return true;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false; // If we reach this point, the service command failed
    }

    public static void startOrStop() {
        JFrame frame = new JFrame("Start or Stop?");
        status = checkStatus();
        JTextField statusField = new JTextField("Current Status: " + status);
        statusField.setEditable(false);
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        startButton.addActionListener(_ -> {
            frame.dispose();
            try {
                if (startDatabase()) {
                    startOrStop();
                    success = true;
                }
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to initiate the database.\nError code: " + e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                startOrStop();
                success = false;
            }
        });
        stopButton.addActionListener(_ -> {
            frame.dispose();
            if (stopDatabase()) {
                startOrStop();
                success = true;
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed to stop the database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                startOrStop();
                success = false;
            }
        });
        JPanel panel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, 10px gaps
        panel.add(startButton);
        panel.add(stopButton);
        frame.getContentPane().add(statusField, BorderLayout.NORTH);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        panel.add(startButton);
        panel.add(stopButton);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                welcomeWindow.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        try {
            new windowsTerminalOpener(false, false);
        } catch (HeadlessException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isDatabaseReady() {
        // Check if the MySQL server is ready by trying to connect to it
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress("localhost", 3306), CONNECTION_TIMEOUT_MS);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    private boolean restartDatabase() throws InterruptedException {
        String[] stopScript = {"cmd.exe", "/c", "sc", "stop", "MySQL84"};
        String[] startScript = {"cmd.exe", "/c", "sc", "start", "MySQL84"};

        // Stop the service
        if (!executeServiceCommand(stopScript, "STOPPED")) {
            return false;
        }

        // Start the service
        return executeServiceCommand(startScript, "RUNNING");
    }

    private void closeWindow() {
        if (process != null) {
            process.destroy();
        }
        dispose();
    }

}
