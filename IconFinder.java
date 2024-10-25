import javax.swing.*;
import java.io.File;

public class IconFinder {

    // Method to find the ETIcon.jpg file in the current directory
    public static ImageIcon findIcon() {
        // Get the current directory
        String currentDirectory = System.getProperty("user.dir");

        // Create a File object for the current directory
        File directory = new File(currentDirectory);

        // List all files in the current directory
        File[] files = directory.listFiles();

        // Iterate through the files to find ETIcon.jpg
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals("ETIcon.jpg")) {
                    // Return ImageIcon with found file path
                    return new ImageIcon(file.getAbsolutePath());
                }
            }
        }

        // Return null if ETIcon.jpg is not found
        return null;
    }

    // Example usage
    public static void main(String[] args) {
        ImageIcon icon = findIcon();
        if (icon != null) {
            // Set the found icon to a JFrame
            JFrame frame = new JFrame();
            frame.setIconImage(icon.getImage());
            frame.setTitle("My Application");
            frame.setSize(400, 300);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } else {
            System.out.println("ETIcon.jpg not found in the current directory.");
        }
    }
}
