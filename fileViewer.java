import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class fileViewer {

    public static void openAndViewFiles(JFrame frame) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int result = fileChooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            openFileWithDefaultProgram(selectedFile);
        }
    }

    private static void openFileWithDefaultProgram(File file) {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.open(file);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Failed to open the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Desktop is not supported on this system.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
