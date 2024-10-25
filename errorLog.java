import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Random;
import java.util.Scanner;

public class errorLog {
    private static String errorLogFilePath;

    private static void loadFilePaths() throws IOException {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("file_paths.txt"))) {
            properties.load(reader);
            errorLogFilePath = properties.getProperty("Error");
        }
        errorLogFilePath = errorLogFilePath.replace("Log File: ", "");
    }

    private static void writeErrorLog(String logEntry) {
        try (
                PrintWriter out = new PrintWriter(
                        new FileWriter(
                                errorLogFilePath,
                                true
                        )
                )
        ) {
            out.println(logEntry);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    public static void setErrorLogPath() {
        try {
            loadFilePaths();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final String[] REMARKS = {
            "Whoops my bad. \nIt seems I made a mistake in my code. \nOr something else happened. \nEh, either way, here's what happened",
            "Oh no! Something went wrong. \nHere's the scoop on the error:",
            "Yikes! An error occurred. \nLet's see what went wrong:",
            "Oops! Looks like I tripped over a bug. \nHere's the error report:",
            "Well, this is embarrassing. \nAn error happened. \nHere's the details:",
            "You should have seen the other guy!\nHere's the play-by-play:"
    };

    public static int writeErrorLog(Throwable throwable) throws IOException {
        Random random = new Random();
        int remarkIndex = random.nextInt(REMARKS.length);
        String selectedRemark = REMARKS[remarkIndex];

        try (PrintWriter pw = new PrintWriter(new FileWriter(errorLogFilePath, true))) {
            pw.println(selectedRemark);
            throwable.printStackTrace(pw);
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {

        try {
            loadFilePaths();
            testErrors();
        } catch (IOException | RuntimeException e) {
//            writeErrorLog(new IOException());
            writeErrorLog(e);
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void testErrors() throws IOException {
        Scanner in = new Scanner(System.in);
        while (true) { // Changed to an infinite loop
            try {
                System.out.println("Input something");
                in.next();
                System.out.println("What next?\n1: /\n2: +\n3: -\n4: *\n5: Exit");
                String choice = in.next();
                switch (choice) {
                    case "1":
                        // Intentionally causing a division by zero error
                        int result = 1 / 0;
                        break;
                    case "2":
                        // Intentionally causing an array index out of bounds exception
                        int[] arr = new int[1];
                        int number = arr[2];
                        break;
                    case "3":
                        // Intentionally causing a null pointer exception
                        String str = null;
                        int length = str.length();
                        break;
                    case "4":
                        // Intentionally causing an input mismatch exception
                        System.out.println("Enter an integer:");
                        int integer = in.nextInt(); // Causes an exception if the input is not an integer
                        break;
                    case "5":
                        System.out.println("Exiting...");
                        in.close();
                        return; // Use return to exit the method cleanly
                    case "6":
//                        Scanner New = new Scanner(System.in);
                        System.out.println("Input Something Please!");
                        throw new IllegalStateException();
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } catch (InputMismatchException ime) {
                System.out.println("That's not an integer. Try again.");
                writeErrorLog(ime);
                in.nextLine(); // Consume the invalid input
            } catch (IllegalStateException e) {
                writeErrorLog(e);
//                in.nextLine();
            } catch (Exception e) {
                writeErrorLog(e);
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public static void checkErrorLog(int option) {
        if (option == 0) {
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                try {
                    desktop.open(new File(errorLogFilePath));
                    System.exit(-1);
                } catch (IOException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Failed to open the file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Desktop is not supported on this system.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.exit(-1);
        }
    }
}
