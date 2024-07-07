import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class setFilePaths {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Create the frame
            JFrame frame = new JFrame("Set File Paths");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(4, 1));

            // Create buttons
            JButton setPasswordFilePathButton = new JButton("Set Password File Path");
            JButton setLogFilePathButton = new JButton("Set Log File Path");
            JButton setMessagesFilePathButton = new JButton("Set Messages File Path");
            JButton setMusicFolderPathButton = new JButton("Set Music Folder Path");
            JButton setErrorLogFilePathButton = new JButton("Set Error Log File Path");
            JButton exitButton = new JButton("Exit");
            // Add action listeners
            setPasswordFilePathButton.addActionListener(e -> chooseFile(frame, "Password file"));
            setLogFilePathButton.addActionListener(e -> chooseFile(frame, "Log file"));
            setMessagesFilePathButton.addActionListener(e -> chooseFile(frame, "Messages file"));
            setMusicFolderPathButton.addActionListener(e -> chooseDirectory(frame, "Music folder"));
            setErrorLogFilePathButton.addActionListener(e->chooseFile(frame,"Error Log File"));
            exitButton.addActionListener(e->frame.dispose());

            // Add buttons to frame
            frame.add(setPasswordFilePathButton);
            frame.add(setLogFilePathButton);
            frame.add(setMessagesFilePathButton);
            frame.add(setMusicFolderPathButton);
            frame.add(setErrorLogFilePathButton);
            frame.add(exitButton);

            // Display the frame
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    private static void chooseFile(JFrame parent, String fileType) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Replace single backslashes with double backslashes
            String filePath = selectedFile.getAbsolutePath().replace("\\", "\\\\");
            writeToFile(fileType + ": " + filePath);
        }
    }


    private static void chooseDirectory(JFrame parent, String fileType) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(parent);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedDirectory = fileChooser.getSelectedFile();
            String filePath = selectedDirectory.getAbsolutePath().replace("\\", "\\\\");
            writeToFile(fileType + ": " + filePath);
        }
    }

    private static void writeToFile(String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("file_paths.txt", true))) {
            writer.write(content);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
