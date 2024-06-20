

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

import javax.swing.*;

public class database {
    private static String enteredPassword = "";

    public static void main(String[] args) {
        // Database connection details
        String databaseUrl = "jdbc:mysql://localhost:3306/userDatabase";
        String username = "root";
        String password = "";

        // Prompt the user for username
        Scanner in = new Scanner(System.in);
        System.out.print("Enter username: ");
        String enteredUsername = in.nextLine().trim();

        // Create a CountDownLatch with count 1
        CountDownLatch latch = new CountDownLatch(1);

        // Show the password input popup
        showPasswordInputPopup(latch);

        try {
            // Wait until the latch count reaches zero
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hash the entered password
        String hashedPassword = hashPassword(enteredPassword);

        // SQL query
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";

        // Establish the connection
        try (Connection connection = DriverManager.getConnection(databaseUrl, username, password)) {
            // Prepare the statement
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, enteredUsername);
            statement.setString(2, hashedPassword);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            // Check if the user is valid
            if (resultSet.next()) {
                String retrievedUsername = resultSet.getString("username");
                String retrievedPassword = resultSet.getString("password");

                System.out.println("Login successful");
                System.out.println("Retrieved username: " + retrievedUsername);
                System.out.println("Retrieved password: " + retrievedPassword);
            } else {
                System.out.println("Invalid username or password");
            }

            // Close the ResultSet, statement, and connection
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void showPasswordInputPopup(CountDownLatch latch) {
        JFrame frame = new JFrame();
        JPasswordField passwordField = new JPasswordField();

        // Add an ActionListener to the password field
        passwordField.addActionListener(e -> {
            // Get the entered password
            char[] passwordChars = passwordField.getPassword();
            enteredPassword = new String(passwordChars);

            // Close the window
            frame.dispose();

            // Count down the latch
            latch.countDown();
        });

        frame.getContentPane().add(passwordField);
        frame.setSize(200, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
