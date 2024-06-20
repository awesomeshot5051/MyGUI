import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class LoginGUI implements ActionListener {

    public class MyWindowAdapter extends WindowAdapter {

        // Override the windowClosing method
        @Override
        public void windowClosing(WindowEvent e) {
            // Call your disconnectFromDatabase method
            JFrame frame = (JFrame) e.getSource();
            frame.dispose();
            try {
                disconnectFromDatabase();
            } catch (SQLException ex) {
                // Handle any SQLExceptions
                ex.printStackTrace();
            }
            // Dispose the frame
        }
    }

    // Create an instance of your custom class
    MyWindowAdapter myWindowAdapter = new MyWindowAdapter();
    // private final Semaphore semaphore = new Semaphore(0);
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(
            "hh:mm:ss a"
    );
    // private final AtomicBoolean isAdminAuthenticated = new AtomicBoolean(false);
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JFrame frame;
    private JFrame welcomeFrame;
    // private JFrame UAframe;
    private String currentUsername;
    private String currentPassword;
    private boolean isDefaultCredentials;
    private String user;
    private String currentUser;
    private String group;
    private static boolean isAdmin;
    String currentStatus = "";
    // private JTextField newUser;
    private String PasswordHash;
    private String capturedPassword;
    // private String userNameHash;
    private static Connection connection;
    private static Connection connection2;
    private String status;
    private JComboBox<String>[] statusComboBoxes;
    // private JButton updatebutton
    private int i = 0;
    private boolean isUpdateSuccessful = false;

    private boolean isAlsoAdmin = false;
    private boolean clearingLogs = false;
    private boolean setToDelete = false;
    private Clip clip;
    private String songTitle;
    private JFrame songFrame;
    private JPanel songJPanel;
    private boolean adminIsHacked = false;
    private JButton hackAdminButton = null;
    private List<String> messagesForAdminList = new ArrayList<>();
    private static boolean addToMessage = false;
    private int notificationCount;
    private JButton displayMessages;
    private List<String> disabledUsers = new ArrayList<>();
    private List<String> bannedUsers = new ArrayList<>();
    private String messages =
            "C:\\Users\\Traee\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\messages.txt";
    private JPanel loginPanel;
    private boolean clearingOnLogin;
    private Queue<String> songQueue;
    private boolean passwordChange;
    private static LoginGUI loginGUI;
    private boolean superAdmin = false;
    private int passwordExpirationTime;
    private final int DEFAULT_PASSWORD_EXPIRATION_TIME = 30;
    private Process process;
    private int attempts = 0;
    private JSpinner expirationTime;
    private boolean strong = false;

    public void connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/userDatabase";
        String url2 = "jdbc:mysql://localhost:3306/Budget";
        connection = DriverManager.getConnection(url, "root", "S@rdine855051!");
        connection2 = DriverManager.getConnection(url2, "root", "S@rdine855051!");
        // usernameTextField = new JTextField(20);
        // passwordField = new JPasswordField(20);
        // frame = new JFrame("Login");
        // welcomeFrame = new JFrame("Welcome");

        // frame.setSize(400, 300);
        // // Set up the login form
        // loginPanel = new JPanel();
        // loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        // Dimension panelSize = new Dimension(200, 100);
        // loginPanel.setPreferredSize(panelSize);
        // // Add the login button
        // JButton loginButton = new JButton("Login to the database");
        // // Add KeyListener to the username and password fields
        // usernameTextField.addKeyListener(new KeyAdapter() {
        // @Override
        // public void keyPressed(KeyEvent e) {
        // if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        // try {
        // String username = usernameTextField.getText();
        // char[] passwordChars = passwordField.getPassword();
        // String password = new String(passwordChars);
        // String url2 = "jdbc:mysql://localhost:3306/Budget";
        // connection = DriverManager.getConnection(url, "root", "S@rdine855051!");
        // connection2 = DriverManager.getConnection(url2, "root", "S@rdine855051!");
        // frame.dispose();
        // initializeGUI();
        // } catch (SQLException e1) {
        // JOptionPane.showMessageDialog(null, "Failed" + e1.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }
        // }
        // });

        // passwordField.addKeyListener(new KeyAdapter() {
        // @Override
        // public void keyPressed(KeyEvent e) {
        // if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        // try {
        // String username = usernameTextField.getText();
        // char[] passwordChars = passwordField.getPassword();
        // String password = new String(passwordChars);
        // String url2 = "jdbc:mysql://localhost:3306/Budget";
        // System.out.println(password.toString());
        // connection = DriverManager.getConnection(url, username, password);
        // connection2 = DriverManager.getConnection(url2, username, password);
        // frame.dispose();
        // initializeGUI();
        // } catch (SQLException e1) {
        // JOptionPane.showMessageDialog(null, "Failed" + e1.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // }
        // }
        // });

        // // Add ActionListener to the login button
        // loginButton.addActionListener(e -> {
        // try {
        // String username = usernameTextField.getText();
        // char[] passwordChars = passwordField.getPassword();
        // String password = new String(passwordChars);
        // String url2 = "jdbc:mysql://localhost:3306/Budget";
        // connection = DriverManager.getConnection(url, "root", "S@rdine855051!");
        // connection2 = DriverManager.getConnection(url2, "root", "S@rdine855051!");
        // frame.dispose();
        // initializeGUI();
        // } catch (SQLException e1) {
        // JOptionPane.showMessageDialog(null, "Failed" + e1.getMessage(), "Error",
        // JOptionPane.ERROR_MESSAGE);
        // }
        // });
        // loginPanel.add(new JLabel("Username:"));
        // loginPanel.add(usernameTextField);
        // loginPanel.add(new JLabel("Password:"));
        // loginPanel.add(passwordField);
        // loginPanel.add(loginButton);
        // frame.addWindowListener(myWindowAdapter);
        // // Add the login panel to the frame
        // frame.setContentPane(loginPanel);
        // frame.pack();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setLocationRelativeTo(null);
        // frame.setVisible(true);
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        LoginGUI.isAdmin = isAdmin;
    }

    public void disconnectFromDatabase() throws SQLException {
        // Use try-with-resources to close the connections
        try (
                PreparedStatement statement1 = connection.prepareStatement("SHUTDOWN");
                PreparedStatement statement2 = connection2.prepareStatement("SHUTDOWN")
        ) {
            // Execute the SHUTDOWN command on the database engine
            statement1.execute();
            statement2.execute();
        }

        // Close the connections
        if (
                connection != null &&
                        !connection.isClosed() &&
                        connection2 != null &&
                        !connection2.isClosed()
        ) {
            connection.close();
            connection2.close();
        }
    }

    public LoginGUI() throws InterruptedException {
        songQueue = new LinkedList<>();
        // Set a maximum number of attempts
        // Set a delay between each attempt in milliseconds
        // Initialize a variable to store the connection status
        // Use a loop to try to connect to the database until successful or maximum
        // attempts reached
        try {
            // Try to connect to the database
            connectToDatabase();
            // If no exception is thrown, set the connection status to true
            // Initialize the GUI
            initializeGUI();
        } catch (SQLException e) {
            // If an exception is thrown, increment the number of attempts
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured.\nError message: " + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            new windowsTerminalOpener(true, false);
            process = windowsTerminalOpener.getProcess();
            // Wait for some time before trying again
            // try {
            // Thread.sleep(1000);
            // } catch (InterruptedException e2) {
            // // If an exception is thrown, print the stack trace to the console
            // e2.printStackTrace();
            // }
        }
        // If the connection status is still false after the loop, show a popup message
        // that all attempts have failed

    }

    private void getExpirationTime() {
        try (
                PreparedStatement statement = connection.prepareStatement(
                        "SELECT expiration_time FROM users WHERE `group`='superadmin'"
                )
        ) {
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                passwordExpirationTime =
                        Integer.parseInt(resultSet.getString("expiration_time"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to get the expiration time\n " + e.getMessage(),
                    "Error getting expiration time",
                    JOptionPane.ERROR_MESSAGE
            );
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    null,
                    "Unable to get the expiration time\n " + ex.getMessage(),
                    "Error getting expiration time",
                    JOptionPane.ERROR_MESSAGE
            );
            passwordExpirationTime = DEFAULT_PASSWORD_EXPIRATION_TIME;
        }
    }

    private void initializeGUI() {
        getExpirationTime();
        // Initialize the text fields and frames
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        frame = new JFrame("Login");
        welcomeFrame = new JFrame("Welcome");

        frame.setSize(400, 300);
        // Set up the login form
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(200, 100);
        loginPanel.setPreferredSize(panelSize);
        // Add the login button
        JButton loginButton = new JButton("Login");
        // Add KeyListener to the username and password fields
        usernameTextField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            try {
                                login();
                            } catch (SQLException e1) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Failed" + e1.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }
                }
        );

        passwordField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            try {
                                login();
                            } catch (SQLException e1) {
                                JOptionPane.showMessageDialog(
                                        null,
                                        "Failed" + e1.getMessage(),
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        }
                    }
                }
        );

        // Add ActionListener to the login button
        loginButton.addActionListener(e -> {
            try {
                login();
            } catch (SQLException e1) {
                JOptionPane.showMessageDialog(
                        null,
                        "Failed" + e1.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameTextField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);
        frame.addWindowListener(myWindowAdapter);
        // Add the login panel to the frame
        frame.setContentPane(loginPanel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void checkAndHandleNewMonth(String user) {
        // Get the last login date for the user from the database
        LocalDate lastLoginDate = getLastLoginDateForUser(user);

        // Get the current date
        LocalDate currentDate = LocalDate.now();

        // Compare the months
        if (
                lastLoginDate != null &&
                        lastLoginDate.getMonth() != currentDate.getMonth()
        ) {
            // It's a new month, perform the necessary actions
            newMonth();
        }

        // Update the last login date for the user in the database
        updateLastLoginDateForUser(user, currentDate);
    }

    private LocalDate getLastLoginDateForUser(String user) {
        LocalDate lastLoginDate = null;
        String selectLastLoginQuery = "SELECT last_login FROM users WHERE name = ?";
        try (
                PreparedStatement selectLastLoginStatement = connection.prepareStatement(
                        selectLastLoginQuery
                )
        ) {
            // Set the user parameter value
            selectLastLoginStatement.setString(1, user);

            // Execute the query and retrieve the result set
            ResultSet resultSet = selectLastLoginStatement.executeQuery();

            // Check if the result set has a row
            if (resultSet.next()) {
                // Retrieve the last login date from the result set
                Date lastLogin = resultSet.getDate("last_login");
                if (lastLogin != null) {
                    lastLoginDate =
                            new java.util.Date(lastLogin.getTime())
                                    .toInstant()
                                    .atZone(ZoneId.systemDefault())
                                    .toLocalDate();
                    // Compare lastLoginDate with the current date
                    LocalDate currentDate = LocalDate.now();

                    // Check if the month has changed
                    if (lastLoginDate.getMonthValue() != currentDate.getMonthValue()) {
                        // Execute newMonth() method
                        clearingOnLogin = true;
                        newMonth();
                    }
                }
            }

            // Close the result set
            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any potential exceptions
        }
        return lastLoginDate;
    }

    private void updateLastLoginDateForUser(String user, LocalDate currentDate) {
        String updateLastLoginQuery =
                "UPDATE users SET last_login = ? WHERE name = ?";
        try (
                PreparedStatement updateLastLoginStatement = connection.prepareStatement(
                        updateLastLoginQuery
                )
        ) {
            // Set the last login date and user parameter values
            updateLastLoginStatement.setDate(1, java.sql.Date.valueOf(currentDate));
            updateLastLoginStatement.setString(2, user);

            // Execute the update statement
            updateLastLoginStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle any potential exceptions
        }

        System.out.println(
                "Last Login date for user " +
                        user +
                        " was on " +
                        currentDate.toString() +
                        " or " +
                        java.sql.Date.valueOf(currentDate)
        );
    }

    public void login() throws SQLException {
        // LocalTime currentTime = LocalTime.now();
        // int hour = currentTime.getHour();
        // int minute = currentTime.getMinute();
        // int second = currentTime.getSecond();

        // Get the username and password entered by the user
        String username = usernameTextField.getText();
        char[] password = passwordField.getPassword();

        // Hash the entered password
        String hashedPassword = oldhashPassword(password);
        currentPassword = hashedPassword;
        // Check if the entered username and password match the database
        boolean loginSuccessful = checkCredentials(username, hashedPassword);
        getUserValue(username, hashedPassword);
        String getSaltQuery = "SELECT salt FROM users WHERE name=?";
        String updateQuery = "UPDATE users SET salt = ? WHERE name = ?";

        // Check if the user already has a salt
        String existingSalt = null;
        try (
                PreparedStatement statement = connection.prepareStatement(getSaltQuery)
        ) {
            statement.setString(1, user);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                existingSalt = resultSet.getString("salt");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        if (loginSuccessful && existingSalt == null) {
            getUserValue(username, hashedPassword);
            JOptionPane.showMessageDialog(
                    null,
                    "We are converting to a more secure password storing algorithm. \nPlease change your password so that it is more secure.",
                    "Change Required",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Check if the user already has a salt
            try (
                    PreparedStatement statement = connection.prepareStatement(getSaltQuery)
            ) {
                statement.setString(1, user);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    existingSalt = resultSet.getString("salt");
                }

                if (existingSalt == null) {
                    // Generate a new salt and insert it into the database
                    String salt = generateRandomSalt();
                    try (
                            PreparedStatement statement2 = connection.prepareStatement(
                                    updateQuery
                            )
                    ) {
                        statement2.setString(1, salt);
                        statement2.setString(2, user);
                        int rowsAffected = statement2.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println(
                                    "Salt inserted successfully for user: " + user
                            );
                        } else {
                            System.out.println("User not found or no rows affected.");
                        }
                    } catch (SQLException e) {
                        JOptionPane.showMessageDialog(
                                null,
                                "An error occured\nError code " + e.getStackTrace(),
                                "Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }

                // Now proceed to prompt the user to change their password
                changePasswordRequired();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else {
            getUserValue(username, hashedPassword);
            getSaltQuery = "SELECT salt FROM users WHERE username=?";
            existingSalt = null;
            try (
                    PreparedStatement statement = connection.prepareStatement(getSaltQuery)
            ) {
                statement.setString(1, username);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    existingSalt = resultSet.getString("salt");
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            hashedPassword =
                    hashPassword((String.valueOf(password) + existingSalt).toCharArray());
            currentPassword = hashedPassword;
            loginSuccessful = checkCredentials(username, hashedPassword);
        }
        status = isEnabled(username, hashedPassword);
        boolean testAdmin = testCredentials(username, hashedPassword);
        if (testAdmin) {
            capturedPassword = String.valueOf(password);
        }
        if (status.equalsIgnoreCase("disabled")) {
            JOptionPane.showMessageDialog(
                    frame,
                    " Invalid username or password\nThis account has been disabled\nPlease refer to an administrator to renable it.",
                    "Login Error",
                    JOptionPane.ERROR_MESSAGE
            );
            loginSuccessful = false;
        }
        if (loginSuccessful) {
            int count = getNotificationCount();
            if (isAdmin()) {
                if (count > 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "You have messages",
                            "Messages",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
            currentUsername = username;
            getUserValue(username, hashedPassword);
            currentUser = user;
            if (!user.equals("default")) {
                boolean passwordMeets = isPasswordChangeRequired(
                        String.valueOf(password)
                );
                if (!passwordMeets) {
                    passwordChange = true;
                }
            }
            // Open a new window if the login is successful
            String formattedTime = getFormattedTime();
            if (!user.equals("default")) {
                isDefaultCredentials = false;
            }
            if (isAlsoAdmin) {
                writeLog(
                        "Successful login at " +
                                formattedTime +
                                " by " +
                                currentUser +
                                " as " +
                                user
                );
            } else if (!isDefaultCredentials) {
                checkAndHandleNewMonth(user);
                writeLog("Successful login at " + formattedTime + " by " + user);
            }
            openWelcomeWindow(testAdmin);
        } else {
            if (status.equalsIgnoreCase("disabled")) {
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
                panel.add(new JLabel("Invalid username or password"));
                panel.add(new JLabel("This account has been disabled"));
                panel.add(
                        new JLabel("Please refer to an administrator to re-enable it.")
                );
                panel.add(new JLabel("This will be reported!"));

                // Create the buttons
                JButton okButton = new JButton("OK");
                JButton requestButton = new JButton("Request Account Unlock");

                // Add button listeners
                okButton.addActionListener(e -> {
                    // OK button clicked, perform any necessary actions
                    frame.dispose();
                });

                requestButton.addActionListener(e -> {
                    // Request button clicked, set the flag to add the user to the queue
                    addToMessage = true;
                    frame.dispose();
                });

                // Add buttons to the panel
                panel.add(requestButton);

                // Show the custom dialog
                JOptionPane.showMessageDialog(
                        frame,
                        panel,
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );

                // Check the flag to add the user to the queue
                if (addToMessage) {
                    getUserValue(username, hashedPassword);
                    if (messagesForAdminList.contains(user + " has been disabled")) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "Request has already been sent",
                                "Request already Sent",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        messagesForAdminList.add(user + " has been disabled");
                        JOptionPane.showMessageDialog(
                                frame,
                                "Request sent",
                                "Request Sent",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    }
                    initializeGUI();
                }
                String formattedTime = getFormattedTime();
                writeLog("Failed attempt at " + formattedTime + " by a disabled user.");
                // loginSuccessful = false;
            } else {
                JPanel panel = new JPanel();
                // Notify the user if the login is unsuccessful
                JOptionPane.showMessageDialog(
                        frame,
                        "Invalid username or password",
                        "Login Error",
                        JOptionPane.ERROR_MESSAGE
                );
                String formattedTime = getFormattedTime();
                attempts += 1;
                writeLog("Failed attempt at " + formattedTime);

                // Check if the attempts reach 5 or 10
                if (attempts == 5) {
                    panel.add(new JLabel("Invalid username or password"));
                    panel.add(new JLabel("Would you like to request a password change?"));
                    JButton yesButton = new JButton("Yes");
                    JButton noButton = new JButton("No");

                    // Add button listeners
                    yesButton.addActionListener(e -> {
                        // Request button clicked, set the flag to add the user to the queue
                        addToMessage = true;
                        frame.dispose();
                        initializeGUI();
                    });

                    noButton.addActionListener(e -> {
                        // No button clicked, perform any necessary actions
                        frame.dispose();
                        initializeGUI();
                    });

                    // Add buttons to the panel
                    panel.add(yesButton);
                    panel.add(noButton);

                    // Show the custom dialog
                    int option = JOptionPane.showOptionDialog(
                            frame,
                            panel,
                            "Login Error",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            new Object[] { yesButton, noButton },
                            yesButton
                    );

                    // Check the option selected
                    if (option == 0) {
                        // Yes button clicked, set the flag to add the user to the queue
                        addToMessage = true;
                    }

                    // Check the flag to add the user to the queue
                    if (addToMessage) {
                        getUserValue(username, hashedPassword);
                        if (messagesForAdminList.contains(user + " has been disabled")) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Request has already been sent",
                                    "Request already Sent",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            messagesForAdminList.add(user + " has been disabled");
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Request sent",
                                    "Request Sent",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                } else if (attempts > 10) {
                    panel.add(
                            new JLabel("Invalid username or password\nToo many failed attempts")
                    );
                    panel.add(new JLabel("This account has been disabled"));
                    panel.add(
                            new JLabel("Please refer to an administrator to re-enable it.")
                    );
                    panel.add(new JLabel("This will be reported!"));
                    updateAccountStatusInDatabase(username, "disabled");
                    JButton okButton = new JButton("OK");

                    // Add button listener
                    okButton.addActionListener(e -> {
                        // OK button clicked, perform any necessary actions
                        addToMessage = true;
                        frame.dispose();
                    });
                    if (addToMessage) {
                        getUserValue(username, hashedPassword);
                        if (messagesForAdminList.contains(user + " has been disabled")) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Request has already been sent",
                                    "Request already Sent",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        } else {
                            messagesForAdminList.add(user + " has been disabled");
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Request sent",
                                    "Request Sent",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                    }
                    // Add button to the panel
                    panel.add(okButton);

                    // Show the custom dialog
                    JOptionPane.showOptionDialog(
                            frame,
                            panel,
                            "Login Error",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            new Object[] { okButton },
                            okButton
                    );
                }

                // Clear the password char array for security reasons
                Arrays.fill(password, '\0');
                // attempts++;
            }
        }
    }

    private boolean isPasswordChangeRequired(String valueOf) {
        boolean longEnough = PasswordStrengthTester.isStrongPassword(valueOf);
        String query =
                "SELECT lastChangedDate FROM passwordExpiration WHERE `user`=?";
        LocalDate lastChangedLocalDate = null;
        boolean passwordNotExpired = true;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user); // Set the username as the parameter in the prepared statement
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Retrieve the date from the result set
                java.sql.Date sqlLastChangedDate = resultSet.getDate("lastChangedDate");
                lastChangedLocalDate = sqlLastChangedDate.toLocalDate();

                // Get the current date as LocalDate
                LocalDate currentDate = LocalDate.now();

                // Calculate the difference in days between lastChangedDate and currentDate
                long differenceInDays = ChronoUnit.DAYS.between(
                        lastChangedLocalDate,
                        currentDate
                );
                System.out.println(
                        lastChangedLocalDate.toString() + " " + currentDate.toString()
                );
                passwordNotExpired = differenceInDays < passwordExpirationTime;
                if (superAdmin) {
                    passwordNotExpired = true;
                } else {
                    if (passwordNotExpired) {
                        // Password change is required as more than 30 days have passed
                        long timeUntilChange = passwordExpirationTime - differenceInDays;
                        if (timeUntilChange < 10) {
                            if (superAdmin) {
                                // do nothing
                            } else {
                                // Display a message using JOptionPane
                                JOptionPane.showMessageDialog(
                                        null,
                                        "You must change your password in " +
                                                timeUntilChange +
                                                " days",
                                        "Password change coming up",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                            }
                        }
                    } else {
                        return false;
                    }
                }
            } else {
                System.out.println("No record found for user '" + user + "'.");
                LocalDate localDate = LocalDate.now();

                // Convert it to a java.sql.Date
                java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);
                query =
                        "INSERT INTO passwordExpiration (user,lastChangedDate) VALUES (?,?)";

                try (
                        PreparedStatement preparedStatement = connection.prepareStatement(
                                query
                        )
                ) {
                    // Set the values for the placeholders
                    preparedStatement.setString(1, user); // Replace "username" with the actual username
                    preparedStatement.setDate(2, sqlDate);

                    // Execute the INSERT statement
                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        // System.out.println("Data inserted successfully.");
                        JOptionPane.showMessageDialog(
                                null,
                                "Data inserted successfully",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE
                        );
                    } else {
                        System.out.println("Insertion failed.");
                        JOptionPane.showMessageDialog(
                                null,
                                "Insertion Failed",
                                "Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "An error occured\n",
                            "Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }

        // Return true only if the password is long enough and password change is
        // required
        return longEnough && passwordNotExpired;
    }

    private int getNotificationCount() {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(messages))) {
            while (reader.readLine() != null) {
                count++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return count;
    }

    private static String getFormattedTime() {
        LocalTime currentTime = LocalTime.now();
        return currentTime.format(timeFormatter);
    }

    private void getUserValue(String username, String password)
            throws SQLException {
        // String user = null;
        String query =
                "SELECT name, `group` FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = resultSet.getString("name");
                group = resultSet.getString("group");
            }
        }
    }

    private void getGroup(String username) throws SQLException {
        String query = "SELECT `group` FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                group = resultSet.getString("group");
            }
        }
    }

    private boolean testCredentials(String username, String hashedPassword)
            throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String group = resultSet.getString("group");
                setAdmin(
                        group.equalsIgnoreCase("admin") ||
                                group.equalsIgnoreCase("superadmin")
                );
                if (group.equalsIgnoreCase("superadmin")) {
                    superAdmin = true;
                } else {
                    superAdmin = false;
                }
                if (!isAdmin()) {
                    isDefaultCredentials = group.equalsIgnoreCase("default");
                    setAdmin(false);
                }
            }
        }

        return isAdmin();
    }

    private boolean checkCredentials(String username, String hashedPassword)
            throws SQLException {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, hashedPassword);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();
        }
    }

    public static String isEnabled(String username, String password) {
        String localStatus = "Disabled"; // Default status
        String query =
                "SELECT status FROM users WHERE username = ? AND password = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    localStatus = resultSet.getString("status");
                } else {
                    localStatus = "";
                }
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        return localStatus;
    }

    public void openWelcomeWindow(boolean admin) {
        JButton setNewUserButton = null;
        JButton updateAccountStatusButton = null;
        JButton switchUserButton = null;
        JButton deleteUsersButton = null;
        JButton displayAllUsersButton = null;
        JButton displayLogFilesButton = null;
        JButton changeRequirmentsButton = null;
        JButton changeExpirationTimeButton = null;
        JButton restartDatabaseButton = null;
        if (isDefaultCredentials) {
            String sql = "SELECT status FROM users WHERE name='default'";

            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                boolean isDisabled = false;
                if (resultSet.next()) {
                    isDisabled =
                            resultSet.getString("status").equalsIgnoreCase("Disabled");
                }

                if (!isDisabled) {
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(
                            frame,
                            "You MUST set up a new user!",
                            "Alert",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            try (
                    PreparedStatement statement = connection.prepareStatement(
                            "UPDATE users SET status='Disabled' WHERE name=?"
                    )
            ) {
                statement.setString(1, "default");
                statement.executeUpdate();
                // System.out.println("SQL statement executed successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            setNewUserButton = new JButton("Set New User");
            setNewUserButton.addActionListener(e -> {
                try {
                    setNewUser();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JLabel welcomeLabel = new JLabel("Welcome, " + user + "!");
            JPanel welcomePanel = new JPanel();
            welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
            Dimension panelSize = new Dimension(200, 240);
            welcomePanel.setPreferredSize(panelSize);
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
                welcomeFrame.dispose();
                String formattedTime = getFormattedTime();
                writeLog(" Exited at " + formattedTime);
                initializeGUI();
            });

            welcomePanel.add(welcomeLabel);
            if (setNewUserButton != null) {
                welcomePanel.add(setNewUserButton);
            }
            welcomePanel.add(exitButton);

            welcomeFrame.setContentPane(welcomePanel);
            welcomeFrame.addWindowListener(myWindowAdapter);
            welcomeFrame.pack();
            welcomeFrame.setLocationRelativeTo(null);
            welcomeFrame.setVisible(true);

            // Close the login window
            frame.dispose();
        } else {
            if (passwordChange) {
                if (!superAdmin) {
                    try {
                        changePasswordRequired();
                        openWelcomeWindow(isAdmin);
                    } catch (SQLException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(
                                null,
                                "An Error has occured: \nError message: " + e.getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            }
            // LocalTime currentTime = LocalTime.now();
            // int hour = currentTime.getHour();
            // int minute = currentTime.getMinute();
            // int second = currentTime.getSecond();
            welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // welcomeFrame.setSize(400, 300);

            JLabel welcomeLabel = new JLabel("Welcome, " + user + "!");
            JPanel welcomePanel = new JPanel();
            welcomePanel.setLayout(new BoxLayout(welcomePanel, BoxLayout.PAGE_AXIS));
            JScrollPane scrollPane = new JScrollPane(welcomePanel);
            Dimension panelSize = new Dimension(229, 405);
            scrollPane.setPreferredSize(panelSize);

            JButton changePasswordButton = new JButton("Change Password");
            changePasswordButton.addActionListener(e -> {
                try {
                    changePassword();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });

            JButton changeUsernameButton = new JButton("Change Username");
            changeUsernameButton.addActionListener(e -> {
                welcomeFrame.dispose();
                changeUsername();
            });

            JButton updateUserGroupButton = null;
            if (adminIsHacked) {
                setNewUserButton = new JButton("Set New User");
                setNewUserButton.addActionListener(e -> {
                    welcomeFrame.dispose();
                    promptAdminCredentials();
                });
            }
            if (admin) {
                setNewUserButton = new JButton("Set New User");
                setNewUserButton.addActionListener(e -> {
                    // welcomeFrame.dispose();
                    promptAdminCredentials();
                });
                updateUserGroupButton = new JButton("Update User Group");
                updateUserGroupButton.addActionListener(e -> {
                    welcomeFrame.dispose();
                    updateUserGroup();
                });
                updateAccountStatusButton = new JButton("Update Account Status");
                updateAccountStatusButton.addActionListener(e -> {
                    welcomeFrame.dispose();
                    updateUserAccountStatus();
                });
                if (!isAlsoAdmin) {
                    switchUserButton = new JButton("Switch Users");
                    switchUserButton.addActionListener(e -> {
                        welcomeFrame.dispose();
                        switchUser();
                    });
                }
                deleteUsersButton = new JButton("Delete Users");
                deleteUsersButton.addActionListener(e -> {
                    welcomeFrame.dispose();
                    deleteUserAccounts();
                });
                displayAllUsersButton = new JButton("Display Users");
                displayAllUsersButton.addActionListener(e -> {
                    welcomeFrame.dispose();
                    displayAllUsers();
                });
                displayLogFilesButton = new JButton("Display log");
                displayLogFilesButton.addActionListener(e -> {
                    String formattedTime = getFormattedTime();
                    if (isAlsoAdmin) {
                        writeLog(
                                currentUser +
                                        " is accessing the log files as " +
                                        user +
                                        " at + " +
                                        formattedTime +
                                        "."
                        );
                    } else {
                        writeLog(
                                user + " is accessing the log files at " + formattedTime + "."
                        );
                    }
                    readLog();
                });
                displayMessages = new JButton("Display messages");

                displayMessages.addActionListener(e -> {
                    readFromFile(messages);
                    clearFile(messages);
                    displayMessages();
                });
                if (superAdmin) {
                    changeRequirmentsButton =
                            new JButton("Change Password Complexibilty");
                    changeRequirmentsButton.addActionListener(e -> {
                        changePasswordRequirments();
                    });
                    changeExpirationTimeButton = new JButton("Change Expiration Time");
                    changeExpirationTimeButton.addActionListener(e -> {
                        changeExpirationTime();
                    });
                    restartDatabaseButton = new JButton("Restart the database Server");
                    restartDatabaseButton.addActionListener(e -> {
                        try {
                            restartDatabases();
                        } catch (InterruptedException ex) {
                            throw new RuntimeException(ex);
                        }
                    });
                }
            }
            JButton budgetButton = new JButton("Budget");
            budgetButton.addActionListener(e -> {
                welcomeFrame.dispose();
                try {
                    budget();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            });
            JButton songLibraryButton = new JButton("Song Player");
            songLibraryButton.addActionListener(e -> {
                welcomeFrame.dispose();
                SongLibraryGUI();
            });
            JButton codeCrackerButton = new JButton("Code Cracker");
            codeCrackerButton.addActionListener(e -> {
                welcomeFrame.dispose();
                SwingUtilities.invokeLater(
                        new Runnable() {
                            public void run() {
                                new CodeCracker(loginGUI);
                            }
                        }
                );
            });
            JButton numberGameButton = new JButton("Number Game");
            numberGameButton.addActionListener(e -> {
                welcomeFrame.dispose();
                SwingUtilities.invokeLater(
                        new Runnable() {
                            public void run() {
                                new numberGame(loginGUI);
                            }
                        }
                );
            });

            JButton investmentButton = new JButton("Investment Calculator");
            investmentButton.addActionListener(e -> {
                welcomeFrame.dispose();
                InvestmentCalculator();
            });
            JButton exitButton = new JButton("Exit");
            if (isAlsoAdmin) {
                exitButton.addActionListener(e -> {
                    isAlsoAdmin = false;
                    try {
                        setAdmin(isUserAdmin());
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    welcomeFrame.dispose();
                    String formattedTime = getFormattedTime();
                    writeLog(
                            currentUser + " exited " + user + "'s account at " + formattedTime
                    );
                    switchUser();
                });
            } else {
                exitButton.addActionListener(e -> {
                    if (setToDelete) {
                        deleteAccountFromDatabase(currentUser);
                        JOptionPane.showMessageDialog(
                                null,
                                "Since you have attempted to do something outside of regulation\nyour account will be deleted.\nPlease contact your administrator to unban your account and create a new account.",
                                "Your account is about to be deleted",
                                JOptionPane.ERROR_MESSAGE
                        );
                        setToDelete = false;
                    }
                    welcomeFrame.dispose();
                    String formattedTime = getFormattedTime();
                    writeLog(user + " Exited at " + formattedTime);
                    initializeGUI();
                });
            }
            welcomePanel.add(welcomeLabel);
            welcomePanel.add(changeUsernameButton);
            welcomePanel.add(changePasswordButton);
            if (setNewUserButton != null) {
                welcomePanel.add(setNewUserButton);
            }
            if (updateUserGroupButton != null) {
                welcomePanel.add(updateUserGroupButton);
            }
            if (updateAccountStatusButton != null) {
                welcomePanel.add(updateAccountStatusButton);
            }
            if (switchUserButton != null) {
                welcomePanel.add(switchUserButton);
            }
            if (deleteUsersButton != null) {
                welcomePanel.add(deleteUsersButton);
            }
            if (displayAllUsersButton != null) {
                welcomePanel.add(displayAllUsersButton);
            }
            if (displayLogFilesButton != null) {
                welcomePanel.add(displayLogFilesButton);
            }
            if (displayMessages != null) {
                welcomePanel.add(displayMessages);
            }
            if (changeRequirmentsButton != null) {
                welcomePanel.add(changeRequirmentsButton);
            }
            if (changeExpirationTimeButton != null) {
                welcomePanel.add(changeExpirationTimeButton);
            }
            if (restartDatabaseButton != null) {
                welcomePanel.add(restartDatabaseButton);
            }

            welcomePanel.add(budgetButton);
            welcomePanel.add(songLibraryButton);
            welcomePanel.add(investmentButton);
            welcomePanel.add(codeCrackerButton);
            welcomePanel.add(numberGameButton);
            welcomePanel.add(exitButton);
            welcomeFrame.setContentPane(scrollPane);
            welcomeFrame.addWindowListener(myWindowAdapter);
            welcomeFrame.pack();
            welcomeFrame.setLocationRelativeTo(null);
            welcomeFrame.setVisible(true);
            // Close the login window
            frame.dispose();
            if (admin) {
                int messageCount = checkMessageQueue();
                if (messageCount > 0) {
                    JOptionPane.showMessageDialog(
                            frame,
                            "You have messages!",
                            "Admin Messages",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }
        }
    }

    private void restartDatabases() throws InterruptedException {
        process = windowsTerminalOpener.getProcess();
        if (process != null) {
            process.destroy();
        } else {
            try (
                    PreparedStatement statement1 = connection.prepareStatement("SHUTDOWN");
                    PreparedStatement statement2 = connection2.prepareStatement("SHUTDOWN")
            ) {
                // Execute the SHUTDOWN command on the database engine
                statement1.execute();
                statement2.execute();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "An error occurred",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
        new windowsTerminalOpener(false, true);

        boolean success = windowsTerminalOpener.isSuccess();
        if (success) {
            JOptionPane.showMessageDialog(
                    null,
                    "Database successfully restarted",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    null,
                    "Database failed to restart",
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void changeExpirationTime() {
        // Create a input dialog to get the number of days from the user
        String input = JOptionPane.showInputDialog(
                null,
                "Enter the number of days for password expiration:",
                "Change Expiration Time",
                JOptionPane.QUESTION_MESSAGE
        );

        try {
            int days = Integer.parseInt(input);
            if (days <= 0 || days >= 80) {
                JOptionPane.showMessageDialog(
                        null,
                        "Invalid input! Please enter a number between 0 and 80.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                changeExpirationTime();
            } else {
                passwordExpirationTime = days;
                try (
                        PreparedStatement statement = connection.prepareStatement(
                                "UPDATE users SET expiration_time=? WHERE `group`='superadmin'"
                        )
                ) {
                    statement.setInt(1, days);
                    int rowsAffected = statement.executeUpdate();
                    if (rowsAffected > 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Expiration Time has been changed.\n All users who have not updated their passwords in " +
                                        days +
                                        " day(s) will be asked to do so.",
                                "Expiration updated",
                                JOptionPane.DEFAULT_OPTION
                        );
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Expiration Time has not been changed. Please try again later\nError message: " +
                                    e.getMessage(),
                            "Expiration failed to update",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Invalid input! Please enter a valid number.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void InvestmentCalculator() {
        JFrame frame = new JFrame("Investment Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(450, 200);
        frame.setPreferredSize(dimension);

        JLabel principalLabel = new JLabel("Principal Amount:");
        JTextField principalField = new JTextField(10);

        JLabel rateLabel = new JLabel("Annual Interest Rate:");
        JTextField rateField = new JTextField(10);

        JLabel yearsLabel = new JLabel("Number of Years:");
        JTextField yearsField = new JTextField(10);

        JButton calculateButton = new JButton("Calculate");
        calculateButton.setBackground(Color.RED);
        JLabel resultLabel = new JLabel();
        JButton exitButton = new JButton("Exit");

        calculateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        calculateInvestment(
                                principalField,
                                rateField,
                                yearsField,
                                resultLabel
                        );
                    }
                }
        );

        exitButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        frame.dispose();
                        openWelcomeWindow(isAdmin);
                    }
                }
        );

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        mainPanel.add(principalLabel);
        mainPanel.add(principalField);

        mainPanel.add(rateLabel);
        mainPanel.add(rateField);

        mainPanel.add(yearsLabel);
        mainPanel.add(yearsField);

        mainPanel.add(calculateButton);
        mainPanel.add(resultLabel);

        JPanel exitPanel = new JPanel();
        exitPanel.add(exitButton);

        JPanel containerPanel = new JPanel();
        containerPanel.setLayout(new BorderLayout());
        containerPanel.add(mainPanel, BorderLayout.CENTER);
        containerPanel.add(exitPanel, BorderLayout.SOUTH);

        frame.setLayout(new BorderLayout());
        frame.add(containerPanel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void calculateInvestment(
            JTextField principalField,
            JTextField rateField,
            JTextField yearsField,
            JLabel resultLabel
    ) {
        double principal = Double.parseDouble(principalField.getText());
        double rate = Double.parseDouble(rateField.getText());
        int years = Integer.parseInt(yearsField.getText());

        double result = principal * Math.pow(1 + rate / 100, years);
        if (result >= 1000000000) {
            resultLabel.setText("Future Value: A really big number");
        } else {
            resultLabel.setText("Future Value: " + String.format("%.2f", result));
        }
    }

    private void displayMessages() {
        if (messagesForAdminList.isEmpty()) {
            JOptionPane.showMessageDialog(
                    frame,
                    "No admin messages.",
                    "Admin Messages",
                    JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
        JFrame frame = new JFrame("Messages", null);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        try {
            getDisabledUsernames();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        for (String message : messagesForAdminList) {
            JPanel messagePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JLabel messageLabel = new JLabel(message);
            JButton actionButton = new JButton("Take Action");
            actionButton.addActionListener(e -> {
                String disabledUser = null;
                for (final String Message : messagesForAdminList) {
                    for (String User : disabledUsers) {
                        if (message.contains(User)) {
                            Message.chars();
                            disabledUser = User;
                            break;
                        }
                    }
                    if (disabledUser != null) {
                        break;
                    }
                }

                Object[] options = { "Yes", "Dismiss", "Cancel" };

                int choice = JOptionPane.showOptionDialog(
                        frame,
                        "Do you want to enable this account?",
                        "Enable Account?",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.QUESTION_MESSAGE,
                        null,
                        options,
                        null
                );

                if (choice == JOptionPane.YES_OPTION) {
                    updateAccountStatusInDatabase(disabledUser, "enabled");
                    disabledUsers.remove(disabledUser);
                    messagesForAdminList.remove(message);
                    writeToFile(messages);
                    frame.dispose();
                    notificationCount -= 1;
                    displayMessages();
                } else if (choice == JOptionPane.NO_OPTION) {
                    // Dismiss option selected
                    disabledUsers.remove(disabledUser);
                    messagesForAdminList.remove(message);
                    writeToFile(messages);
                    frame.dispose();
                    notificationCount -= 1;
                    displayMessages();
                    // Add your code here for handling the "Dismiss" action
                } else if (choice == JOptionPane.CANCEL_OPTION) {
                    frame.dispose();
                    displayMessages();
                }
                // Perform action for the specific message here
                // You can access the message using 'message' variable
                // Remove the message from the list if needed:
                // messagesForAdminList.remove(message);
            });

            JButton dismissButton = new JButton("Dismiss");
            dismissButton.addActionListener(e -> {
                // Remove the message from the list
                messagesForAdminList.remove(message);
                panel.remove(messagePanel);
                panel.revalidate();
                panel.repaint();
            });

            messagePanel.add(messageLabel);
            if (message.contains("has been disabled")) {
                messagePanel.add(actionButton);
            } else {
                messagePanel.add(dismissButton);
            }

            panel.add(messagePanel);
        }
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            openWelcomeWindow(isAdmin());
        });
        panel.add(exitButton);
        frame.setContentPane(panel);
        frame.addWindowListener(myWindowAdapter);
        frame.pack();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        // JOptionPane.showMessageDialog(frame, panel, "Admin Messages",
        // JOptionPane.INFORMATION_MESSAGE);

        notificationCount = 0;
        writeToFile(messages);
        // updatenotificationcount();
    }

    // Method to update the notification count and button text
    // private void //updatenotificationcount() {
    // if (notificationCount > 0) {
    // displayMessages.setText("Display messages (" + notificationCount + ")");
    // } else {
    // displayMessages.setText("Display messages");
    // }
    // }

    // Method to check the queue size and update the notification count
    private int checkMessageQueue() {
        if (!messagesForAdminList.isEmpty()) {
            notificationCount = messagesForAdminList.size();
            // updatenotificationcount();
            return notificationCount;
        }
        return 0;
    }

    private List<Character> crackedPasswd;
    private List<Character> characters;
    private JTextArea outputTextArea;
    private JPasswordField PasswordField;
    private JButton crackbutton;

    public void CodeCracker() {
        crackedPasswd = new ArrayList<>();
        characters =
                new ArrayList<>(
                        Arrays.asList(
                                ' ',
                                '!',
                                '"',
                                '#',
                                '$',
                                '%',
                                '&',
                                '\'',
                                '(',
                                ')',
                                '*',
                                '+',
                                ',',
                                '-',
                                '.',
                                '/',
                                ':',
                                ';',
                                '<',
                                '=',
                                '>',
                                '?',
                                '@',
                                '[',
                                '\\',
                                ']',
                                '^',
                                '_',
                                '`',
                                '{',
                                '|',
                                '}',
                                '~',
                                '0',
                                '1',
                                '2',
                                '3',
                                '4',
                                '5',
                                '6',
                                '7',
                                '8',
                                '9',
                                'A',
                                'B',
                                'C',
                                'D',
                                'E',
                                'F',
                                'G',
                                'H',
                                'I',
                                'J',
                                'K',
                                'L',
                                'M',
                                'N',
                                'O',
                                'P',
                                'Q',
                                'R',
                                'S',
                                'T',
                                'U',
                                'V',
                                'W',
                                'X',
                                'Y',
                                'Z',
                                'a',
                                'b',
                                'c',
                                'd',
                                'e',
                                'f',
                                'g',
                                'h',
                                'i',
                                'j',
                                'k',
                                'l',
                                'm',
                                'n',
                                'o',
                                'p',
                                'q',
                                'r',
                                's',
                                't',
                                'u',
                                'v',
                                'w',
                                'x',
                                'y',
                                'z'
                        )
                );

        JFrame frame = new JFrame("Code Cracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        PasswordField = new JPasswordField(20);
        crackbutton = new JButton("Crack Password");
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            openWelcomeWindow(isAdmin);
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(crackbutton);
        buttonPanel.add(exitButton);

        frame.add(outputTextArea, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        crackbutton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        char[] passwordChars = PasswordField.getPassword();
                        String passwordForCracking = new String(passwordChars).trim();
                        startCracking(passwordForCracking);
                    }
                }
        );

        frame.setVisible(true);
    }

    private void startCracking(String password) {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                StringBuilder crackedPassword = new StringBuilder();

                for (int i = 0; i < password.length(); i++) {
                    List<Character> tempList = new ArrayList<>(characters);
                    char targetChar = password.charAt(i);
                    boolean found = false;

                    Random random = new Random();

                    for (int k = 0; k < characters.size(); k++) {
                        int pos = random.nextInt(tempList.size());
                        char c = tempList.get(pos);
                        publish(crackedPassword.toString() + c);
                        Thread.sleep(100); // Adjust the delay as needed

                        if (c == targetChar) {
                            found = true;
                            break;
                        } else {
                            tempList.remove(pos);
                        }
                    }

                    if (found) {
                        crackedPasswd.add(targetChar);
                        crackedPassword.append(targetChar);
                    } else {
                        crackedPassword.append("?");
                    }
                }

                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                String latestChunk = chunks.get(chunks.size() - 1);
                outputTextArea.setText(latestChunk);
            }

            @Override
            protected void done() {
                String crackedPassword = crackedPasswd.toString();
                crackedPassword = crackedPassword.replaceAll("[\\[\\], ]", "");
                outputTextArea.append("\n\nPassword cracked: " + crackedPassword);
            }
        };

        worker.execute();
    }

    public void CodeHacker() {
        crackedPasswd = new ArrayList<>();
        characters =
                new ArrayList<>(
                        Arrays.asList(
                                ' ',
                                '!',
                                '"',
                                '#',
                                '$',
                                '%',
                                '&',
                                '\'',
                                '(',
                                ')',
                                '*',
                                '+',
                                ',',
                                '-',
                                '.',
                                '/',
                                ':',
                                ';',
                                '<',
                                '=',
                                '>',
                                '?',
                                '@',
                                '[',
                                '\\',
                                ']',
                                '^',
                                '_',
                                '`',
                                '{',
                                '|',
                                '}',
                                '~',
                                '0',
                                '1',
                                '2',
                                '3',
                                '4',
                                '5',
                                '6',
                                '7',
                                '8',
                                '9',
                                'A',
                                'B',
                                'C',
                                'D',
                                'E',
                                'F',
                                'G',
                                'H',
                                'I',
                                'J',
                                'K',
                                'L',
                                'M',
                                'N',
                                'O',
                                'P',
                                'Q',
                                'R',
                                'S',
                                'T',
                                'U',
                                'V',
                                'W',
                                'X',
                                'Y',
                                'Z',
                                'a',
                                'b',
                                'c',
                                'd',
                                'e',
                                'f',
                                'g',
                                'h',
                                'i',
                                'j',
                                'k',
                                'l',
                                'm',
                                'n',
                                'o',
                                'p',
                                'q',
                                'r',
                                's',
                                't',
                                'u',
                                'v',
                                'w',
                                'x',
                                'y',
                                'z'
                        )
                );

        JFrame frame = new JFrame("Code Cracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        outputTextArea = new JTextArea();
        outputTextArea.setEditable(false);
        outputTextArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        // PasswordField = new JPasswordField(20);
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            openWelcomeWindow(isAdmin);
        });
        // JPanel inputPanel = new JPanel();
        // inputPanel.add(passwordField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(exitButton);

        frame.add(outputTextArea, BorderLayout.CENTER);
        // frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        startHacking(capturedPassword);
        frame.setVisible(true);
    }

    private void startHacking(String password) {
        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                StringBuilder crackedPassword = new StringBuilder();

                for (int i = 0; i < password.length(); i++) {
                    List<Character> tempList = new ArrayList<>(characters);
                    char targetChar = password.charAt(i);
                    boolean found = false;

                    Random random = new Random();

                    for (int k = 0; k < characters.size(); k++) {
                        int pos = random.nextInt(tempList.size());
                        char c = tempList.get(pos);
                        publish(crackedPassword.toString() + c);
                        Thread.sleep(100); // Adjust the delay as needed

                        if (c == targetChar) {
                            found = true;
                            break;
                        } else {
                            tempList.remove(pos);
                        }
                    }

                    if (found) {
                        crackedPasswd.add(targetChar);
                        crackedPassword.append(targetChar);
                    } else {
                        crackedPassword.append("?");
                    }
                }

                return null;
            }

            @Override
            protected void process(List<String> chunks) {
                String latestChunk = chunks.get(chunks.size() - 1);
                outputTextArea.setText(latestChunk);
            }

            @Override
            protected void done() {
                String crackedPassword = crackedPasswd.toString();
                crackedPassword = crackedPassword.replaceAll("[\\[\\], ]", "");
                outputTextArea.append("\n\nPassword cracked: " + crackedPassword);
            }
        };

        worker.execute();
    }

    private void SongLibraryGUI() {
        songFrame = new JFrame("Song Library");
        songJPanel = new JPanel();
        songFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the layout of songJPanel to GridLayout
        // songJPanel.setLayout(new GridLayout(rows, columns));
        songJPanel.setLayout(new BoxLayout(songJPanel, BoxLayout.PAGE_AXIS));
        JScrollPane scrollPane = new JScrollPane(songJPanel);

        // Set the preferred size of the scroll pane
        Dimension dimension = new Dimension(330, 430);
        scrollPane.setPreferredSize(dimension);
        if (!isAdmin() && !adminIsHacked) {
            hackAdminButton = new JButton("Hack the admin account");
            hackAdminButton.addActionListener(e -> {
                hackAdminButton = null;
                songFrame.dispose();

                SwingUtilities.invokeLater(
                        new Runnable() {
                            public void run() {
                                CodeHacker();
                            }
                        }
                );

                adminIsHacked = true;
                String formattedTime = getFormattedTime();
                writeLog(
                        user + " is attempting to hack the admin account at " + formattedTime
                );
            });
        }

        // Create song buttons
        JButton song1Button = new JButton("The Joe Biden Song");
        JButton song2Button = new JButton("Imperial March");
        JButton song3Button = new JButton("Sunday's on the way");
        JButton song4Button = new JButton("Only Jesus by Casting Crowns");
        JButton song5Button = new JButton("Nobody by Casting Crowns");
        JButton song6Button = new JButton("Desert Road by Casting Crowns");
        JButton song7Button = new JButton("Jesus Freak by D.C. Talk");
        JButton song8Button = new JButton("We Believe by Newsboys");
        JButton song9Button = new JButton("God's Not Dead by Newsboys");
        JButton song10Button = new JButton(
                "What Are We Waiting For by For King and Country"
        );
        JButton song11Button = new JButton("Heaven Came Down By YB");
        JButton song12Button = new JButton("Ayo By YB");
        JButton song13Button = new JButton("Unity By For King And Country");
        JButton song14Button = new JButton("Clark Kent By YB");
        // Create stop button

        JButton addToQueue1Button = new JButton("Add to Queue");
        JButton addToQueue2Button = new JButton("Add to Queue");
        JButton addToQueue3Button = new JButton("Add to Queue");
        JButton addToQueue4Button = new JButton("Add to Queue");
        JButton addToQueue5Button = new JButton("Add to Queue");
        JButton addToQueue6Button = new JButton("Add to Queue");
        JButton addToQueue7Button = new JButton("Add to Queue");
        JButton addToQueue8Button = new JButton("Add to Queue");
        JButton addToQueue9Button = new JButton("Add to Queue");
        JButton addToQueue10Button = new JButton("Add to Queue");
        JButton addToQueue11Button = new JButton("Add to Queue");
        JButton addToQueue12Button = new JButton("Add to Queue");
        JButton addToQueue13Button = new JButton("Add to Queue");
        JButton addToQueue14Button = new JButton("Add to Queue");
        Dimension button = new Dimension(1, 1);
        addToQueue1Button.setPreferredSize(button);
        addToQueue2Button.setPreferredSize(button);
        addToQueue3Button.setPreferredSize(button);
        addToQueue4Button.setPreferredSize(button);
        addToQueue5Button.setPreferredSize(button);
        addToQueue6Button.setPreferredSize(button);
        addToQueue7Button.setPreferredSize(button);
        addToQueue8Button.setPreferredSize(button);
        addToQueue9Button.setPreferredSize(button);
        addToQueue10Button.setPreferredSize(button);
        addToQueue11Button.setPreferredSize(button);
        addToQueue12Button.setPreferredSize(button);
        addToQueue13Button.setPreferredSize(button);
        addToQueue14Button.setPreferredSize(button);
        addToQueue1Button.addActionListener(e -> {
            addToQueue(song1Button.getText());
        });
        addToQueue2Button.addActionListener(e -> {
            addToQueue(song2Button.getText());
        });
        addToQueue3Button.addActionListener(e -> {
            addToQueue(song3Button.getText());
        });
        addToQueue4Button.addActionListener(e -> {
            addToQueue(song4Button.getText());
        });
        addToQueue5Button.addActionListener(e -> {
            addToQueue(song5Button.getText());
        });
        addToQueue6Button.addActionListener(e -> {
            addToQueue(song6Button.getText());
        });
        addToQueue7Button.addActionListener(e -> {
            addToQueue(song7Button.getText());
        });
        addToQueue8Button.addActionListener(e -> {
            addToQueue(song8Button.getText());
        });
        addToQueue9Button.addActionListener(e -> {
            addToQueue(song9Button.getText());
        });
        addToQueue10Button.addActionListener(e -> {
            addToQueue(song10Button.getText());
        });
        addToQueue11Button.addActionListener(e -> {
            addToQueue(song11Button.getText());
        });
        addToQueue12Button.addActionListener(e -> {
            addToQueue(song12Button.getText());
        });
        addToQueue13Button.addActionListener(e -> {
            addToQueue(song13Button.getText());
        });
        addToQueue14Button.addActionListener(e -> {
            addToQueue(song14Button.getText());
        });
        JButton stopButton = new JButton("Stop");
        JButton exitButton = new JButton("Exit");
        // Add action listeners to song buttons
        song1Button.addActionListener(this);
        song2Button.addActionListener(this);
        song3Button.addActionListener(this);
        song4Button.addActionListener(this);
        song5Button.addActionListener(this);
        song6Button.addActionListener(this);
        song7Button.addActionListener(this);
        song8Button.addActionListener(this);
        song9Button.addActionListener(this);
        song10Button.addActionListener(this);
        song11Button.addActionListener(this);
        song12Button.addActionListener(this);
        song13Button.addActionListener(this);
        song14Button.addActionListener(this);

        // Add action listener to stop button
        stopButton.addActionListener(this);
        exitButton.addActionListener(e -> {
            songFrame.dispose();
            openWelcomeWindow(isAdmin());
        });
        // Add buttons to the frame

        songJPanel.add(song1Button);
        // songJPanel.add(addToQueue1Button);
        songJPanel.add(song2Button);
        // songJPanel.add(addToQueue2Button);
        songJPanel.add(song3Button);
        // songJPanel.add(addToQueue3Button);
        songJPanel.add(song4Button);
        // songJPanel.add(addToQueue4Button);
        songJPanel.add(song5Button);
        // songJPanel.add(addToQueue5Button);
        songJPanel.add(song6Button);
        // songJPanel.add(addToQueue6Button);
        songJPanel.add(song7Button);
        // songJPanel.add(addToQueue7Button);
        songJPanel.add(song8Button);
        // songJPanel.add(addToQueue8Button);
        songJPanel.add(song9Button);
        // songJPanel.add(addToQueue9Button);
        songJPanel.add(song10Button);
        // songJPanel.add(addToQueue10Button);
        songJPanel.add(song11Button);
        // songJPanel.add(addToQueue11Button);
        songJPanel.add(song12Button);
        // songJPanel.add(addToQueue12Button);
        songJPanel.add(song13Button);
        // songJPanel.add(addToQueue13Button);
        songJPanel.add(song14Button);
        // songJPanel.add(addToQueue14Button);
        if (hackAdminButton != null) {
            songJPanel.add(hackAdminButton);
        }
        songJPanel.add(stopButton);
        songJPanel.add(exitButton);

        songFrame.setContentPane(scrollPane);
        songFrame.addWindowListener(myWindowAdapter);
        songFrame.pack();
        songFrame.setLocationRelativeTo(null);
        songFrame.setVisible(true);
    }

    private void changePasswordRequirments() {
        try {
            int requriredLength = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            null,
                            "What is the required length of the password",
                            "Password Requirments",
                            JOptionPane.QUESTION_MESSAGE
                    )
            );
            if (PasswordStrengthTester.setRequiredLength(requriredLength)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Required Password Length has been changed to " + requriredLength,
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Required Password Length has not been changed\nInputted Length is too short",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Required Password Length MUST be a number!\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void addToQueue(String text) {
        TerminalUtils.clearTerminal();
        songQueue.add(text);
        for (String song : songQueue) {
            System.out.println(song);
        }
    }

    private String currentSong;

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Stop")) {
            currentSong = songTitle;
            stopSong();
        } else {
            songTitle = e.getActionCommand();
            if (clip != null && clip.isRunning()) {
                stopSong();
                currentSong = songTitle;
                playSong(); // Stop the current song if it's playing
            } else {
                playSong();
            }
        }
    }

    private void playSong() {
        if (!songQueue.isEmpty()) {
            for (String song : songQueue) {
                playSong(song);
                songQueue.clear();
            }
        }
        Object[] options = { "View Music Video", "Play only audio" };
        int choice = JOptionPane.showOptionDialog(
                songFrame,
                "Playing " + songTitle,
                "Playing",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );
        if (choice == JOptionPane.YES_OPTION) {
            try {
                // Specify the path to your song files
                String filePath =
                        "C:\\Users\\Traee.TRAES_GAMING_PC\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\Song Library\\" +
                                songTitle.toLowerCase().replace(" ", "_") +
                                ".wav";
                File audioFile = new File(filePath);
                // Create an AudioInputStream from the file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                        audioFile
                );
                // Get the clip for playback
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                FloatControl volume = (FloatControl) clip.getControl(
                        FloatControl.Type.MASTER_GAIN
                );
                volume.setValue(-80f); // -80 dB is very close to silent

                viewMusicVideo(songTitle);
                Thread.sleep(2000);
                clip.start();
            } catch (
                    InterruptedException
                    | LineUnavailableException
                    | IOException
                    | UnsupportedAudioFileException e
            ) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (choice == JOptionPane.NO_OPTION) {
            try {
                // Specify the path to your song files
                String filePath =
                        "C:\\Users\\Traee.TRAES_GAMING_PC\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\Song Library\\" +
                                songTitle.toLowerCase().replace(" ", "_") +
                                ".wav";
                File audioFile = new File(filePath);
                // Create an AudioInputStream from the file
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                        audioFile
                );

                // Get the clip for playback
                clip = AudioSystem.getClip();
                clip.open(audioStream);

                // Start playing the song
                clip.start();
                currentSong = songTitle;
                JOptionPane.showMessageDialog(
                        songFrame,
                        "Playing " + songTitle,
                        "Playing",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (
                    UnsupportedAudioFileException
                    | IOException
                    | LineUnavailableException ex
            ) {
                JOptionPane.showMessageDialog(
                        songFrame,
                        "No audio found!" + ex.getMessage(),
                        "Play Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } else if (choice == JOptionPane.CANCEL_OPTION) {
            // Add to Queue option selected
            // Your code for adding the song to the queue
            songQueue.add(songTitle);
            JOptionPane.showMessageDialog(
                    songFrame,
                    "Added " + songTitle + " to the queue",
                    "Queue",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
    }

    private void playSong(String song) {
        songTitle = song;
        try {
            // Specify the path to your song files
            String filePath =
                    "C:\\Users\\Traee.TRAES_GAMING_PC\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\Song Library\\" +
                            songTitle.toLowerCase().replace(" ", "_") +
                            ".wav";
            File audioFile = new File(filePath);
            // Create an AudioInputStream from the file
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

            // Get the clip for playback
            clip = AudioSystem.getClip();
            clip.open(audioStream);

            // Start playing the song
            clip.start();
            currentSong = songTitle;
            JOptionPane.showMessageDialog(
                    songFrame,
                    "Playing " + songTitle,
                    "Playing",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (
                UnsupportedAudioFileException | IOException | LineUnavailableException ex
        ) {
            JOptionPane.showMessageDialog(
                    songFrame,
                    "No audio found!" + ex.getMessage(),
                    "Play Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void readFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                messagesForAdminList.add(line);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        clearFile(filePath);
    }

    public void writeToFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String message : messagesForAdminList) {
                writer.write(message);
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Writing an empty string to clear the file
            writer.write("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private File videoFile;

    private void stopSong() {
        if (clip == null) {
            JOptionPane.showMessageDialog(
                    songFrame,
                    "Nothing is playing",
                    "Song Error",
                    JOptionPane.ERROR_MESSAGE
            );
        } else {
            if (clip != null && clip.isRunning()) {
                if (videoFile != null) {
                    try {
                        Runtime
                                .getRuntime()
                                .exec(
                                        new String[] {
                                                "taskkill",
                                                "/f",
                                                "/im",
                                                "Microsoft.Media.Player.exe",
                                        }
                                );
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        JOptionPane.showMessageDialog(
                                null,
                                "An error occured\nError code " + e.getStackTrace(),
                                "Failed",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }
                    clip.stop();
                    // clip = null;
                    JOptionPane.showMessageDialog(
                            songFrame,
                            "Stopping " + currentSong,
                            "Stopping",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    // songTitle = null;
                    // videoFile = null;
                } else {
                    clip.stop();
                    // clip = null;
                    JOptionPane.showMessageDialog(
                            songFrame,
                            "Stopping " + currentSong,
                            "Stopping",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    // songTitle = null;
                }
            }
        }
    }

    private void viewMusicVideo(String videoFileName) {
        String videoFilePath =
                "C:\\Users\\Traee.TRAES_GAMING_PC\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\Song Library\\" +
                        videoFileName.toLowerCase().replace(" ", "_") +
                        ".mp4";
        videoFile = new File(videoFilePath);

        if (videoFile.exists()) {
            try {
                // String windowsMediaPath = "C:\\Program Files (x86)\\Windows Media
                // Player\\wmplayer.exe";
                // Open the video file with the default associated program
                Desktop.getDesktop().open(videoFile);
                // Time.wait(2);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(
                        songFrame,
                        "Failed to open video",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(
                    songFrame,
                    "No video found for " + songTitle,
                    "Video Not Found",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private void budget() throws IOException {
        balance = 0.0;
        String getBalanceQuery = "SELECT * FROM total WHERE user = '" + user + "'";
        String setBalance =
                "INSERT INTO total (total,user) VALUES(0.0,'" + user + "')";
        try (
                PreparedStatement getBalance = connection2.prepareStatement(
                        getBalanceQuery
                );
                PreparedStatement setBalanceStatement = connection2.prepareStatement(
                        setBalance
                )
        ) {
            ResultSet resultSet = getBalance.executeQuery();

            // Process the result set and display or use the retrieved data
            if (resultSet.next()) {
                // Retrieve the values from the result set
                balance = resultSet.getDouble("total");
                // ... process the retrieved data as needed
            } else {
                setBalanceStatement.executeUpdate();
            }

            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while getting the balance from the table: " +
                            ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        LocalDate currentDate = LocalDate.now();
        int dayOfMonth = currentDate.getDayOfMonth();

        if (dayOfMonth == 1) {
            // It's the first day of the month, clear the budget and subcategories databases
            newMonth();
        }
        String BudgetTable;
        String subcategoriesTable;
        String budgetQuery;
        String subcategoriesQuery;
        String totalQuery;

        if (isAlsoAdmin) {
            // Use the variable 'user' for admin
            BudgetTable = user + "_budget";
            subcategoriesTable = user + "_subcategories";
        } else {
            // Use the variable 'currentUser' for non-admin user
            BudgetTable = currentUser + "_budget";
            subcategoriesTable = currentUser + "_subcategories";
        }
        boolean budgetTableExists = false;
        boolean subcategoriesTableExists = false;
        try {
            budgetTableExists = checkTableExists(BudgetTable);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        try {
            subcategoriesTableExists = checkTableExists(subcategoriesTable);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        if (!subcategoriesTableExists) {
            if (!budgetTableExists) {
                try {
                    createTables(BudgetTable, subcategoriesTable);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(
                            null,
                            "An error occured\nError code " + e.getStackTrace(),
                            "Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
        budgetQuery =
                "SELECT Category, MaxAllowedMoney, IFNULL(AmountSpent, 0) AS AmountSpent FROM " +
                        BudgetTable;
        subcategoriesQuery =
                "SELECT category_name, subcategory_name, IFNULL(AmountSpent, 0) AS AmountSpent " +
                        "FROM " +
                        subcategoriesTable;
        totalQuery =
                "SELECT SUM(AmountSpent) AS TotalAmountSpent, SUM(MaxAllowedMoney) AS TotalMaxAllowedMoney " +
                        "FROM " +
                        BudgetTable;

        try (
                PreparedStatement budgetStatement = connection2.prepareStatement(
                        budgetQuery
                );
                PreparedStatement subcategoriesStatement = connection2.prepareStatement(
                        subcategoriesQuery
                );
                PreparedStatement totalStatement = connection2.prepareStatement(
                        totalQuery
                )
        ) {
            // String userToQuery = isAlsoAdmin ? user : currentUser;

            // budgetStatement.setString(1, userToQuery);
            // subcategoriesStatement.setString(1, userToQuery);
            // totalStatement.setString(1, userToQuery);

            ResultSet budgetResultSet = budgetStatement.executeQuery();
            ResultSet subcategoriesResultSet = subcategoriesStatement.executeQuery();
            ResultSet totalResultSet = totalStatement.executeQuery();

            // Create a map to store the subcategories for each category
            Map<String, List<Object[]>> categorySubcategoriesMap = new HashMap<>();

            // Populate the categorySubcategoriesMap with subcategories for each category
            while (subcategoriesResultSet.next()) {
                String category = subcategoriesResultSet.getString("category_name");
                String subcategory = subcategoriesResultSet.getString(
                        "subcategory_name"
                );
                String amountSpent =
                        "$" + subcategoriesResultSet.getString("AmountSpent");

                List<Object[]> subcategoriesList = categorySubcategoriesMap.getOrDefault(
                        category,
                        new ArrayList<>()
                );
                subcategoriesList.add(new Object[] { subcategory, "", amountSpent });
                categorySubcategoriesMap.put(category, subcategoriesList);
            }

            // Create the table model with column names

            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[] { "Current Balance", "", "$" + balance },
                    0
            );
            Object[] headerRow = { "Category", "Max Allowed Money", "Amount Spent" };

            // Insert the current balance row at the beginning of the table model
            tableModel.insertRow(0, headerRow);
            // Populate the table model with data from the result set
            while (budgetResultSet.next()) {
                String category = budgetResultSet.getString("Category");
                String maxMoney = budgetResultSet.getString("MaxAllowedMoney");
                String amountSpent = "$" + budgetResultSet.getString("AmountSpent");
                tableModel.addRow(
                        new Object[] { category, "$" + maxMoney, amountSpent }
                );

                // Add the subcategories for the current category to the table model
                List<Object[]> subcategoriesList = categorySubcategoriesMap.getOrDefault(
                        category,
                        new ArrayList<>()
                );
                for (Object[] subcategory : subcategoriesList) {
                    tableModel.addRow(subcategory);
                }
            }

            // Calculate total amount spent and total maximum allowed money
            double totalAmountSpent = 0.00;
            double totalMaxAllowedMoney = 0.00;
            if (totalResultSet.next()) {
                totalAmountSpent = totalResultSet.getDouble("TotalAmountSpent");
                totalMaxAllowedMoney = totalResultSet.getDouble("TotalMaxAllowedMoney");
            }

            // Add total rows to the table model
            tableModel.addRow(
                    new Object[] {
                            "Total",
                            "$" + totalMaxAllowedMoney,
                            "$" + totalAmountSpent,
                    }
            );

            // Create the JTable and set the table model
            JTable budgetJTable = new JTable(tableModel);
            JPanel budgetPanel = new JPanel();
            budgetPanel.setLayout(new BoxLayout(budgetPanel, BoxLayout.PAGE_AXIS));

            // Create the main frame and add the components
            JFrame budgetFrame = new JFrame("Budget Handling");
            budgetFrame.getContentPane().setLayout(new BorderLayout());
            budgetFrame
                    .getContentPane()
                    .add(new JScrollPane(budgetJTable), BorderLayout.CENTER);

            JButton setNewBudgetButton = new JButton("Set up new budget");
            setNewBudgetButton.addActionListener(e -> {
                budgetFrame.dispose();
                createBudget();
            });

            JButton editBudgetButton = new JButton("Edit existing budget");
            editBudgetButton.addActionListener(e -> {
                int selectedRow = budgetJTable.getSelectedRow();
                if (selectedRow != -1) {
                    String category = budgetJTable.getValueAt(selectedRow, 0).toString();
                    String maxMoney = budgetJTable.getValueAt(selectedRow, 1).toString();
                    maxMoney = maxMoney.replace("$", "");
                    budgetFrame.dispose();
                    editBudgetDetails(category, maxMoney);
                } else {
                    JOptionPane.showMessageDialog(
                            budgetFrame,
                            "Please select a budget to edit.",
                            "No Budget Selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            });

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
                budgetFrame.dispose();
                openWelcomeWindow(isAdmin());
            });
            JButton clearSubcategoriesButton = new JButton("Clear Subcategories");
            clearSubcategoriesButton.addActionListener(e -> {
                budgetFrame.dispose();
                int confirmDialogResult = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to clear the subcategories?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmDialogResult == JOptionPane.YES_OPTION) {
                    // User confirmed, proceed with clearing the budget
                    // It's not the first day of the month, display a message
                    newMonth();
                } else {
                    // User chose not to clear the budget
                    // Add any desired handling or display a message
                    try {
                        budget();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            JButton clearBudgetButton = new JButton("Clear Budget");
            clearBudgetButton.addActionListener(e -> {
                budgetFrame.dispose();
                int confirmDialogResult = JOptionPane.showConfirmDialog(
                        null,
                        "Are you sure you want to clear the budget?",
                        "Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirmDialogResult == JOptionPane.YES_OPTION) {
                    // User confirmed, proceed with clearing the budget
                    // It's not the first day of the month, display a message
                    clearBudget();
                } else {
                    // User chose not to clear the budget
                    // Add any desired handling or display a message
                    try {
                        budget();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            });
            JButton deleteSubcategoryButton = new JButton("Delete row");
            deleteSubcategoryButton.addActionListener(e -> {
                int selectedRow = budgetJTable.getSelectedRow();
                if (selectedRow != -1) {
                    selectedRow = budgetJTable.convertRowIndexToModel(selectedRow);
                    String category = tableModel.getValueAt(selectedRow, 0).toString();
                    String maxMoney = tableModel.getValueAt(selectedRow, 1).toString();
                    maxMoney = maxMoney.replace("$", "");
                    budgetFrame.dispose();
                    deleteRow(category, maxMoney);
                }
            });
            JButton addAmountButton = new JButton("Add Amount Spent");
            addAmountButton.addActionListener(a -> {
                int selectedRow = budgetJTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Subtract 1 from the selected row index
                    selectedRow = budgetJTable.convertRowIndexToModel(selectedRow);

                    String category = tableModel.getValueAt(selectedRow, 0).toString();
                    String maxMoney = tableModel.getValueAt(selectedRow, 1).toString();
                    maxMoney = maxMoney.replace("$", "");
                    budgetFrame.dispose();
                    addAmountSpent(category, maxMoney);
                } else {
                    JOptionPane.showMessageDialog(
                            budgetFrame,
                            "Please select a budget to add amount spent.",
                            "No Budget Selected",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            });
            JButton addIncomeButton = new JButton("Add Income");
            addIncomeButton.addActionListener(e -> {
                budgetFrame.dispose();
                addIncome();
            });
            // TODO add edit balance button
            JButton editBalanceButton = new JButton("Edit balance");
            editBalanceButton.addActionListener(e -> {
                budgetFrame.dispose();
                editCurrentBalance();
            });
            // TODO add view income button
            JButton viewIncomeButton = new JButton("View Income");
            viewIncomeButton.addActionListener(e -> {
                budgetFrame.dispose();
                viewIncome();
            });
            // TODO add add target goal button? Maybe
            // TODO add check to ensure I don't go over balance-$100
            // TODO add whatif button to check if I have enough
            addAmountButton.setVisible(false);
            deleteSubcategoryButton.setVisible(false);

            budgetJTable
                    .getSelectionModel()
                    .addListSelectionListener(e -> {
                        if (!e.getValueIsAdjusting()) {
                            int selectedRow = budgetJTable.getSelectedRow();
                            boolean isRowSelected = selectedRow != -1;
                            addAmountButton.setVisible(isRowSelected);
                            deleteSubcategoryButton.setVisible(isRowSelected);
                        }
                    });

            // Apply row filter to hide rows with empty Subcategory Name and Amount Spent
            TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(tableModel);
            rowSorter.setRowFilter(RowFilter.regexFilter(".+", 0, 1));
            budgetJTable.setRowSorter(rowSorter);
            budgetPanel.add(editBudgetButton);
            budgetPanel.add(setNewBudgetButton);
            budgetPanel.add(clearSubcategoriesButton);
            budgetPanel.add(clearBudgetButton);
            budgetPanel.add(addAmountButton);
            budgetPanel.add(addIncomeButton);
            budgetPanel.add(viewIncomeButton);
            budgetPanel.add(editBalanceButton);
            budgetPanel.add(deleteSubcategoryButton);
            budgetPanel.add(exitButton);

            budgetFrame.getContentPane().add(budgetPanel, BorderLayout.SOUTH);

            budgetFrame.pack();
            budgetFrame.setLocationRelativeTo(null);
            budgetFrame.setVisible(true);

            // Close the resources
            budgetResultSet.close();
            subcategoriesResultSet.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while retrieving the budget data.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void viewIncome() {
        JFrame frame = new JFrame("Income");
        String query =
                "SELECT amount, `date` FROM income WHERE `user` = '" + user + "'";
        try (PreparedStatement statement = connection2.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery(query);

            DefaultTableModel tableModel = new DefaultTableModel(
                    new Object[] { "Amount", "Date" },
                    0
            );
            double totalIncome = 0.0;
            while (resultSet.next()) {
                double amount = resultSet.getDouble("amount");
                Date date = resultSet.getDate("date");
                tableModel.addRow(new Object[] { amount, date });
                totalIncome += amount;
            }

            JTable incomeTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(incomeTable);

            JPanel buttonPanel = new JPanel();

            JButton editButton = new JButton("Edit Income Amount");
            editButton.addActionListener(e -> {
                JOptionPane.showMessageDialog(null, "This option is not available yet");
            });

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
                frame.dispose();
                try {
                    budget();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });

            buttonPanel.add(editButton);
            buttonPanel.add(exitButton);

            JLabel totalIncomeLabel = new JLabel("Total Income: $" + totalIncome);

            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.fill = GridBagConstraints.BOTH;
            mainPanel.add(scrollPane, gbc);

            gbc.gridy = 1;
            gbc.weighty = 0.0;
            mainPanel.add(totalIncomeLabel, gbc);

            gbc.gridy = 2;
            mainPanel.add(buttonPanel, gbc);

            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.getContentPane().add(mainPanel);
            frame.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while retrieving income data.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            try {
                budget();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    private void editCurrentBalance() {
        try (
                PreparedStatement editBalanceStatement = connection2.prepareStatement(
                        "SELECT * FROM total WHERE user = ?"
                )
        ) {
            editBalanceStatement.setString(1, user);
            ResultSet resultSet = editBalanceStatement.executeQuery();

            // Process the result set and display or use the retrieved data
            while (resultSet.next()) {
                // Retrieve the values from the result set
                balance = resultSet.getDouble("total");
                // ... process the retrieved data as needed
            }

            resultSet.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while retrieving the balance data.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        JFrame editFrame = new JFrame("Edit Balance");
        JPanel editPanel = new JPanel();
        Dimension dimension = new Dimension(300, 90);
        editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.PAGE_AXIS));
        editPanel.setPreferredSize(dimension);

        // Create a text field to display and edit the current balance
        JTextField balanceTextField = new JTextField(Double.toString(balance));
        balanceTextField.setEditable(true);
        JButton setButton = new JButton("Set");
        // Add a KeyListener to the text field to listen for the Enter key
        balanceTextField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            setButton.doClick(); // Simulate a click on the Set button
                        }
                    }
                }
        );

        // Create a set button
        setButton.addActionListener(e -> {
            String newBalanceText = balanceTextField.getText();
            double newBalance = Double.parseDouble(newBalanceText);

            try (
                    PreparedStatement editBalanceStatement = connection2.prepareStatement(
                            "UPDATE total SET total = ? WHERE user = ?"
                    )
            ) {
                editBalanceStatement.setDouble(1, newBalance);
                editBalanceStatement.setString(2, user);
                editBalanceStatement.executeUpdate();
                balance = newBalance;
                JOptionPane.showMessageDialog(
                        null,
                        "Balance updated successfully.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "An error occurred while updating the balance.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

            editFrame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Create a cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            editFrame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Add the text field, set button, and cancel button to the panel
        editPanel.add(balanceTextField);
        editPanel.add(setButton);
        editPanel.add(cancelButton);

        // Add the panel to the frame and set its properties
        editFrame.getContentPane().add(editPanel);
        editFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editFrame.pack();
        editFrame.setLocationRelativeTo(null);
        editFrame.setVisible(true);
    }

    public static int convertMonthNameToNumber(String monthName) {
        Month month = Month.valueOf(monthName.toUpperCase());
        return month.getValue();
    }

    double balance = 0.0;

    protected Object ignore;

    private void addIncome() {
        String getBalanceQuery = "SELECT * FROM total WHERE user = ?";
        try (
                PreparedStatement getBalance = connection2.prepareStatement(
                        getBalanceQuery
                )
        ) {
            getBalance.setString(1, user);
            ResultSet resultSet = getBalance.executeQuery();

            // Process the result set and display or use the retrieved data
            while (resultSet.next()) {
                // Retrieve the values from the result set
                balance = resultSet.getDouble("total");
                // ... process the retrieved data as needed
            }

            resultSet.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while getting the balance from the table: " +
                            ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        // Create and configure the GUI components
        frame = new JFrame("Add Income");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel incomeLabel = new JLabel("Income:");
        JTextField incomeField = new JTextField();

        JLabel dateLabel = new JLabel("Date:");

        // Create three drop-downs for day, month, and year
        JComboBox<String> dayComboBox = new JComboBox<>();
        JComboBox<String> monthComboBox = new JComboBox<>();
        JComboBox<String> yearComboBox = new JComboBox<>();

        // Add items to the day combo box
        for (int i = 1; i <= 31; i++) {
            dayComboBox.addItem(String.valueOf(i));
        }

        // Add items to the month combo box
        String[] months = {
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "August",
                "September",
                "October",
                "November",
                "December",
        };
        for (String month : months) {
            monthComboBox.addItem(month);
        }

        // Add items to the year combo box
        int currentYear = Year.now().getValue();
        for (int i = currentYear; i >= currentYear - 10; i--) {
            yearComboBox.addItem(String.valueOf(i));
        }

        JButton addButton = new JButton("Add");
        addButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String income = incomeField.getText().trim();
                        String day = (String) dayComboBox.getSelectedItem();
                        String month = (String) monthComboBox.getSelectedItem();
                        int monthNumber = convertMonthNameToNumber(month);

                        String year = (String) yearComboBox.getSelectedItem();
                        String date = year + "-" + monthNumber + "-" + day;
                        // Perform database insert operation with the new income information
                        String insertIncomeQuery =
                                "INSERT INTO income (amount, date, user) VALUES (?, ?, ?)";
                        try (
                                PreparedStatement insertIncome = connection2.prepareStatement(
                                        insertIncomeQuery
                                )
                        ) {
                            insertIncome.setDouble(1, Double.parseDouble(income)); // Set the income amount
                            insertIncome.setDate(2, java.sql.Date.valueOf(date)); // Set the date
                            insertIncome.setString(3, user);

                            insertIncome.executeUpdate();
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    null,
                                    "An error occurred while inserting the income to the table." +
                                            ex.getMessage(),
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                        // try (PreparedStatement totalStatement = connection2.prepareStatement(
                        // "SELECT SUM(amount) AS total_amount FROM income WHERE user=?")) {
                        // totalStatement.setString(1, user);
                        // ResultSet resultSet = totalStatement.executeQuery();
                        // if (resultSet.next()) {
                        // balance = resultSet.getDouble("total_amount");
                        // }
                        // } catch (SQLException e1) {
                        // e1.printStackTrace();
                        // }

                        // String updateTotal = "UPDATE total SET total=? WHERE user=?";
                        try (
                                PreparedStatement totalStatement = connection2.prepareStatement(
                                        "UPDATE total SET total=? WHERE user=?"
                                )
                        ) {
                            totalStatement.setDouble(1, balance + Double.parseDouble(income));
                            totalStatement.setString(2, user);
                            totalStatement.executeUpdate();
                            JOptionPane.showMessageDialog(
                                    null,
                                    "Current Balance updated successfully",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            frame.dispose();
                            try {
                                budget();
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                e1.printStackTrace();
                            }
                        } catch (SQLException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    }
                }
        );
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        // Add components to the panel
        panel.add(incomeLabel);
        panel.add(incomeField);
        panel.add(dateLabel);
        panel.add(dayComboBox);
        panel.add(monthComboBox);
        panel.add(yearComboBox);
        panel.add(new JLabel());
        panel.add(addButton);
        panel.add(exitButton);

        // Add the panel to the frame and make it visible
        frame.add(panel);
        frame.setVisible(true);
    }

    private void deleteRow(String category, String maxMoney) {
        // Delete from master_budget table
        String masterBudgetQuery = "DELETE FROM master_budget WHERE Category = ?";
        try (
                PreparedStatement masterBudgetStatement = connection2.prepareStatement(
                        masterBudgetQuery
                )
        ) {
            masterBudgetStatement.setString(1, category);
            masterBudgetStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while deleting the row from master_budget table.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Delete from user_budget table
        String userBudgetTable = user + "_budget";
        String userBudgetQuery =
                "DELETE FROM " + userBudgetTable + " WHERE Category = ?";
        try (
                PreparedStatement userBudgetStatement = connection2.prepareStatement(
                        userBudgetQuery
                )
        ) {
            userBudgetStatement.setString(1, category);
            userBudgetStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while deleting the row from user_budget table.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Delete from user_subcategories table
        String userSubcategoriesTable = user + "_subcategories";
        String userSubcategoriesQuery =
                "DELETE FROM " + userSubcategoriesTable + " WHERE Category_name = ?";
        try (
                PreparedStatement userSubcategoriesStatement = connection2.prepareStatement(
                        userSubcategoriesQuery
                )
        ) {
            userSubcategoriesStatement.setString(1, category);
            userSubcategoriesStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while deleting the row from user_subcategories table.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        // Delete from master_subcategories table
        String masterSubcategoriesQuery =
                "DELETE FROM master_subcategories WHERE Category_name = ?";
        try (
                PreparedStatement masterSubcategoriesStatement = connection2.prepareStatement(
                        masterSubcategoriesQuery
                )
        ) {
            masterSubcategoriesStatement.setString(1, category);
            masterSubcategoriesStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while deleting the row from master_subcategories table.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "Row deleted successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

        // Refresh the budget table to reflect the changes
        try {
            budget();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void newMonth() {
        String budget_table = "";
        String subcategories_table = "";
        if (isAlsoAdmin) {
            budget_table = user + "_budget";
        } else {
            budget_table = currentUser + "_budget";
        }
        if (isAlsoAdmin) {
            subcategories_table = user + "_subcategories";
        } else {
            subcategories_table = currentUser + "_subcategories";
        }
        String selectBudgetQuery =
                "SELECT MaxAllowedMoney FROM master_budget WHERE user = '" +
                        currentUser +
                        "'";
        String deleteSubcategoriesQuery = "DELETE FROM " + subcategories_table;
        String updateBudgetQuery =
                "UPDATE " + budget_table + " SET AmountSpent='0.0'";

        try (
                PreparedStatement subcategoriesStatement = connection2.prepareStatement(
                        deleteSubcategoriesQuery
                );
                PreparedStatement budgetStatement = connection2.prepareStatement(
                        updateBudgetQuery
                )
        ) {
            // Set the user parameter for subcategories deletion
            // subcategoriesStatement.setString(1, user);
            // Delete subcategories for the specified user
            subcategoriesStatement.executeUpdate();

            // Set the user parameter for budget deletion
            // budgetStatement.setString(1, user);
            // Delete budget entries for the specified user
            budgetStatement.executeUpdate();

            // Execute the select query to retrieve MaxAllowedMoney value
            double maxAllowedMoney = 0.0;
            try (
                    PreparedStatement selectBudgetStatement = connection2.prepareStatement(
                            selectBudgetQuery
                    )
            ) {
                try (ResultSet resultSet = selectBudgetStatement.executeQuery()) {
                    if (resultSet.next()) {
                        maxAllowedMoney = resultSet.getDouble("MaxAllowedMoney");
                    }
                }
            }

            // Update the budget table by setting MaxAllowedMoney and AmountSpent to 0
            String updateMaxAllowedMoneyQuery =
                    "UPDATE " + budget_table + " SET MaxAllowedMoney = ?, AmountSpent = 0";
            try (
                    PreparedStatement updateMaxAllowedMoneyStatement = connection2.prepareStatement(
                            updateMaxAllowedMoneyQuery
                    )
            ) {
                updateMaxAllowedMoneyStatement.setDouble(1, maxAllowedMoney);
                updateMaxAllowedMoneyStatement.executeUpdate();
            }
            if (clearingOnLogin) {
                openWelcomeWindow(isAdmin());
            } else {
                JOptionPane.showMessageDialog(
                        null,
                        "Budget has been cleared.",
                        "Budget Cleared",
                        JOptionPane.INFORMATION_MESSAGE
                );

                try {
                    budget(); // Refresh the budget display
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(
                            null,
                            "An error occured\nError code " + e.getStackTrace(),
                            "Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while clearing the budget: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            if (clearingOnLogin) {
                openWelcomeWindow(isAdmin());
            } else {
                try {
                    budget();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    JOptionPane.showMessageDialog(
                            null,
                            "An error occured\nError code " + e.getStackTrace(),
                            "Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        }
    }

    private static void createTables(
            String budget_table,
            String subcategories_Table
    ) throws SQLException {
        String createBudgetTableQuery =
                "CREATE TABLE IF NOT EXISTS " +
                        budget_table +
                        " (" +
                        "Category VARCHAR(50) NOT NULL, " +
                        "MaxAllowedMoney DOUBLE, " +
                        "AmountSpent DOUBLE, " +
                        "user VARCHAR(50) NOT NULL, " +
                        "PRIMARY KEY (Category, user)" +
                        ")";

        String createSubcategoriesTableQuery =
                "CREATE TABLE IF NOT EXISTS " +
                        subcategories_Table +
                        " (" +
                        "category_name VARCHAR(50) NOT NULL, " +
                        "subcategory_name VARCHAR(50) NOT NULL DEFAULT '', " +
                        "AmountSpent DOUBLE, " +
                        "user VARCHAR(50) NOT NULL, " +
                        "PRIMARY KEY (category_name, subcategory_name, user)" +
                        ")";

        try (
                PreparedStatement budgetStatement = connection2.prepareStatement(
                        createBudgetTableQuery
                );
                PreparedStatement subcategoriesStatement = connection2.prepareStatement(
                        createSubcategoriesTableQuery
                )
        ) {
            budgetStatement.executeUpdate();
            subcategoriesStatement.executeUpdate();
        }
    }

    private void clearBudget() {
        String budget_table = "";
        if (isAlsoAdmin) {
            budget_table = user + "_budget";
        } else {
            budget_table = currentUser + "_budget";
        }
        String subcategories_table = "";
        if (isAlsoAdmin) {
            subcategories_table = user + "_subcategories";
        } else {
            subcategories_table = currentUser + "_subcategories";
        }
        String clearMasterBudget = "DELETE FROM master_budget";
        String deleteSubcategoriesQuery = "DELETE FROM " + subcategories_table;
        String deleteBudgetQuery = "DELETE FROM " + budget_table;

        try (
                PreparedStatement masterBudgeStatement = connection2.prepareStatement(
                        clearMasterBudget
                );
                PreparedStatement subcategoriesStatement = connection2.prepareStatement(
                        deleteSubcategoriesQuery
                );
                PreparedStatement budgetStatement = connection2.prepareStatement(
                        deleteBudgetQuery
                )
        ) {
            // Set the user parameter for subcategories deletion
            // subcategoriesStatement.setString(1, user);
            // Delete subcategories for the specified user
            subcategoriesStatement.executeUpdate();

            // Set the user parameter for budget deletion
            // budgetStatement.setString(1, user);
            // Delete budget entries for the specified user
            budgetStatement.executeUpdate();
            masterBudgeStatement.executeUpdate();

            JOptionPane.showMessageDialog(
                    null,
                    "Budget has been cleared.",
                    "Budget Cleared",
                    JOptionPane.INFORMATION_MESSAGE
            );

            try {
                budget(); // Refresh the budget display
            } catch (IOException e) {
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        } catch (SQLException ex) {
            // ex.printStackTrace();
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred while clearing the budget: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            try {
                budget();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // private int getCategoryID(String category) {
    // int categoryID = -1;
    // String query = "SELECT ID FROM budget WHERE Category = ?";
    // try (PreparedStatement statement = connection2.prepareStatement(query)) {
    // statement.setString(1, category);
    // ResultSet resultSet = statement.executeQuery();
    // if (resultSet.next()) {
    // categoryID = resultSet.getInt("ID");
    // }
    // resultSet.close();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // return categoryID;
    // }

    private void addAmountSpent(String category, String maxMoney) {
        // Create the main frame
        JFrame addAmountFrame = new JFrame("Add Amount Spent");
        addAmountFrame.setLayout(new BorderLayout());

        // Create the category label
        JLabel categoryLabel = new JLabel("Category: " + category);

        // Create the subcategory text field
        JTextField subcategoryTextField = new JTextField(20);
        JPanel subcategoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        subcategoryPanel.add(new JLabel("Subcategory:"));
        subcategoryPanel.add(subcategoryTextField);

        // Create the amount spent text field
        JTextField amountSpentTextField = new JTextField(10);
        JPanel amountSpentPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        amountSpentPanel.add(new JLabel("Amount Spent:"));
        amountSpentPanel.add(amountSpentTextField);

        // Create the set button
        JButton setButton = new JButton("Set");
        setButton.addActionListener(e -> {
            String selectedSubcategory = subcategoryTextField.getText();
            String amountSpent = amountSpentTextField.getText();

            // Validate the input
            if (selectedSubcategory.isEmpty() || amountSpent.isEmpty()) {
                JOptionPane.showMessageDialog(
                        addAmountFrame,
                        "Please enter a subcategory and amount spent.",
                        "Incomplete Information",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            // Update the subcategory table with the selected category, subcategory, amount
            // spent, and user
            String subcategories_table = "";
            if (isAlsoAdmin) {
                subcategories_table = user + "_subcategories";
            } else {
                subcategories_table = currentUser + "_subcategories";
            }
            try (
                    PreparedStatement updateStatement = connection2.prepareStatement(
                            "INSERT INTO " +
                                    subcategories_table +
                                    "(category_name, subcategory_name, AmountSpent, user) VALUES (?, ?, ?, ?)"
                    )
            ) {
                updateStatement.setString(1, category);
                updateStatement.setString(2, selectedSubcategory);
                updateStatement.setDouble(3, Double.parseDouble(amountSpent));
                updateStatement.setString(4, user); // Assuming 'user' is the global variable representing the current
                // user
                updateStatement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "An error occurred while updating the database.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            String maxMoneyBefore = retrieveMaxMoney(category);
            String amountSpentBefore = retrieveAmountSpent(category);
            if (amountSpentBefore == null) {
                amountSpentBefore = "0";
            }
            Double amountSpentAfter =
                    Double.parseDouble(amountSpent) + Double.parseDouble(amountSpentBefore);
            Double maxMoneyAfter = Double.parseDouble(maxMoneyBefore);
            Double updateMaxMoney = maxMoneyAfter - amountSpentAfter;

            // Check if the new amount spent exceeds the maxAllowedMoney
            if (updateMaxMoney < 0) {
                JOptionPane.showMessageDialog(
                        addAmountFrame,
                        "You have exceeded the maximum allowed amount.",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE
                );
                // try {
                // budget();
                // } catch (IOException e1) {
                // // TODO Auto-generated catch block
                // e1.printStackTrace();
                // }
            }
            String budget_table = "";
            if (isAlsoAdmin) {
                budget_table = user + "_budget";
            } else {
                budget_table = currentUser + "_budget";
            }
            String getBalanceQuery = "SELECT * FROM total WHERE user = ?";
            try (
                    PreparedStatement getBalance = connection2.prepareStatement(
                            getBalanceQuery
                    )
            ) {
                getBalance.setString(1, user);
                ResultSet resultSet = getBalance.executeQuery();

                // Process the result set and display or use the retrieved data
                while (resultSet.next()) {
                    // Retrieve the values from the result set
                    balance = resultSet.getDouble("total");
                    // ... process the retrieved data as needed
                }

                resultSet.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        null,
                        "An error occurred while getting the balance from the table: " +
                                ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            String query =
                    "UPDATE " +
                            budget_table +
                            " SET AmountSpent=?, MaxAllowedMoney=? WHERE Category=?";
            try (PreparedStatement statement = connection2.prepareStatement(query)) {
                statement.setString(1, String.valueOf(amountSpentAfter));
                statement.setString(2, String.valueOf(updateMaxMoney));
                statement.setString(3, category);
                statement.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            balance -= amountSpentAfter;
            try (
                    PreparedStatement totalStatement = connection2.prepareStatement(
                            "UPDATE total SET total=? WHERE user=?"
                    )
            ) {
                totalStatement.setDouble(1, balance);
                totalStatement.setString(2, user);
                totalStatement.executeUpdate();
                JOptionPane.showMessageDialog(
                        null,
                        "Current Balance updated successfully",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // Close the add amount spent frame
            addAmountFrame.dispose();

            // Refresh the budget table to reflect the changes
            try {
                budget();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        amountSpentTextField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            setButton.doClick();
                        }
                    }
                }
        );
        // Create the cancel button
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            // Close the add amount spent frame
            addAmountFrame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        // Create the buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonsPanel.add(setButton);
        buttonsPanel.add(cancelButton);

        // Create the main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(categoryLabel);
        mainPanel.add(subcategoryPanel);
        mainPanel.add(amountSpentPanel);

        // Add components to the add amount spent frame
        addAmountFrame.add(mainPanel, BorderLayout.CENTER);
        addAmountFrame.add(buttonsPanel, BorderLayout.SOUTH);

        addAmountFrame.pack();
        addAmountFrame.setLocationRelativeTo(null);
        addAmountFrame.setVisible(true);
    }

    private static boolean checkTableExists(String tableName)
            throws SQLException {
        DatabaseMetaData metadata = connection2.getMetaData();
        try (
                ResultSet resultSet = metadata.getTables(null, null, tableName, null)
        ) {
            return resultSet.next();
        }
    }

    private void createBudget() {
        JFrame frame = new JFrame("Create Budget");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Dimension dimension = new Dimension(350, 150);
        panel.setPreferredSize(dimension);

        JLabel subcategoryLabel = new JLabel("Category:");
        JTextField subcategoryField = new JTextField(20);

        JLabel maxMoneyLabel = new JLabel("Max Money:");
        JTextField maxMoneyField = new JTextField(20);

        JButton setButton = new JButton("Set");
        setButton.addActionListener(e -> {
            String subcategory = subcategoryField.getText();
            String maxMoney = maxMoneyField.getText();
            String budgetTable;
            String subcategoriesTable;

            if (isAlsoAdmin) {
                budgetTable = user + "_budget";
                subcategoriesTable = user + "_subcategories";
            } else {
                budgetTable = currentUser + "_budget";
                subcategoriesTable = currentUser + "_subcategories";
            }

            try {
                // Check if the budget table exists for the user
                DatabaseMetaData metaData = connection2.getMetaData();
                ResultSet resultSet = metaData.getTables(null, null, budgetTable, null);
                boolean budgetTableExists = resultSet.next();

                if (!budgetTableExists) {
                    // Create the budget table for the user
                    try (
                            PreparedStatement createBudgetTableStatement = connection2.prepareStatement(
                                    "CREATE TABLE " +
                                            budgetTable +
                                            " (Category VARCHAR(255) PRIMARY KEY, MaxAllowedMoney DECIMAL(10,2), user VARCHAR(255))"
                            )
                    ) {
                        createBudgetTableStatement.executeUpdate();
                    }

                    // Create the subcategories table for the user
                    try (
                            PreparedStatement createSubcategoriesTableStatement = connection2.prepareStatement(
                                    "CREATE TABLE " +
                                            subcategoriesTable +
                                            " (Category_name VARCHAR(255), AmountSpent DECIMAL(10,2), user VARCHAR(255), FOREIGN KEY(Category_name) REFERENCES " +
                                            budgetTable +
                                            "(Category))"
                            )
                    ) {
                        createSubcategoriesTableStatement.executeUpdate();
                    }
                }

                // Insert the budget entry into the budget table
                try (
                        PreparedStatement budgetStatement = connection2.prepareStatement(
                                "INSERT INTO " +
                                        budgetTable +
                                        " (Category, MaxAllowedMoney, user) VALUES (?, ?, ?)"
                        );
                        PreparedStatement budgetStatement2 = connection2.prepareStatement(
                                "INSERT INTO master_budget (Category, MaxAllowedMoney, user) VALUES (?, ?, ?)"
                        )
                ) {
                    budgetStatement.setString(1, subcategory);
                    budgetStatement.setString(2, maxMoney);
                    budgetStatement.setString(3, user);
                    budgetStatement2.setString(1, subcategory);
                    budgetStatement2.setString(2, maxMoney);
                    budgetStatement2.setString(3, user);
                    budgetStatement.executeUpdate();
                    budgetStatement2.executeUpdate();
                }

                // Insert the subcategory entry into the subcategories table
                try (
                        PreparedStatement subcategoryStatement = connection2.prepareStatement(
                                "INSERT INTO " +
                                        subcategoriesTable +
                                        " (Category_name, AmountSpent, user) VALUES (?, 0, ?)"
                        );
                        PreparedStatement masterSubcategoryStatement = connection2.prepareStatement(
                                "INSERT INTO master_subcategories (Category_name, AmountSpent, user) VALUES (?, 0, ?)"
                        )
                ) {
                    subcategoryStatement.setString(1, subcategory);
                    subcategoryStatement.setString(2, user);
                    subcategoryStatement.executeUpdate();
                    masterSubcategoryStatement.setString(1, subcategory);
                    masterSubcategoryStatement.setString(2, user);
                    masterSubcategoryStatement.executeUpdate();
                }
                // try (PreparedStatement totalStatement = connection2.prepareStatement(
                // "SELECT total FROM total WHERE user=?")) {
                // totalStatement.setString(1, user);
                // resultSet = totalStatement.executeQuery();
                // while (resultSet.next()) {
                // balance = resultSet.getDouble("total");
                // }
                // }
                // try (PreparedStatement totalStatement = connection2.prepareStatement(
                // "INSERT INTO total (total, user) VALUES (?, ?)")) {
                // totalStatement.setDouble(1, balance);
                // totalStatement.setString(2, user);
                // totalStatement.executeUpdate();
                // }
                JOptionPane.showMessageDialog(
                        frame,
                        "Budget created successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Error occurred while creating the budget: " + ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                ex.printStackTrace();
            }

            try {
                budget();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

            frame.dispose();
        });
        // Add KeyListener to the subcategoryField
        subcategoryField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            setButton.doClick();
                        }
                    }
                }
        );

        // Add KeyListener to the maxMoneyField
        maxMoneyField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            setButton.doClick();
                        }
                    }
                }
        );
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        panel.add(subcategoryLabel);
        panel.add(subcategoryField);
        panel.add(maxMoneyLabel);
        panel.add(maxMoneyField);
        panel.add(setButton);
        panel.add(exitButton);

        frame.setContentPane(panel);
        frame.addWindowListener(myWindowAdapter);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void editBudgetDetails(String category, String maxMoney) {
        JFrame detailsFrame = new JFrame("Edit Budget Details");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        JLabel categoryLabel = new JLabel("Category:");
        JTextField categoryField = new JTextField(category);

        JLabel maxMoneyLabel = new JLabel("Max Money:");
        JTextField maxMoneyField = new JTextField(maxMoney);

        JButton setButton = new JButton("Set");
        setButton.addActionListener(e -> {
            String newMaxMoney = maxMoneyField.getText();
            String newCategory = categoryField.getText();
            // Update the database with the new values
            updateBudget(category, newCategory, newMaxMoney);

            // Show a success message
            JOptionPane.showMessageDialog(
                    detailsFrame,
                    "Budget updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Close the details window
            detailsFrame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            detailsFrame.dispose();
            try {
                budget();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        });

        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(maxMoneyLabel);
        panel.add(maxMoneyField);
        panel.add(setButton);
        panel.add(exitButton);

        detailsFrame.setContentPane(panel);
        detailsFrame.addWindowListener(myWindowAdapter);
        detailsFrame.pack();
        detailsFrame.setLocationRelativeTo(null);
        detailsFrame.setVisible(true);
    }

    private String retrieveAmountSpent(String category) {
        String budget_table = "";
        if (isAlsoAdmin) {
            budget_table = user + "_budget";
        } else {
            budget_table = currentUser + "_budget";
        }
        String query =
                "SELECT AmountSpent FROM " + budget_table + " WHERE Category = ?";
        try (PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setString(1, category);
            // statement.setString(2, user); // Assuming 'user' is the global variable
            // representing the current user
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("AmountSpent");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private String retrieveMaxMoney(String category) {
        String budget_table = "";
        if (isAlsoAdmin) {
            budget_table = user + "_budget";
        } else {
            budget_table = currentUser + "_budget";
        }
        String query =
                "SELECT MaxAllowedMoney FROM " +
                        budget_table +
                        " WHERE Category = ? AND user = ?";
        try (PreparedStatement statement = connection2.prepareStatement(query)) {
            statement.setString(1, category);
            statement.setString(2, user); // Assuming 'user' is the global variable representing the current user
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("MaxAllowedMoney");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
        return null;
    }

    private void updateBudget(
            String category,
            String newCategory,
            String newMaxMoney
    ) {
        String budget_table = "";
        if (isAlsoAdmin) {
            budget_table = user + "_budget";
        } else {
            budget_table = currentUser + "_budget";
        }
        String query =
                "UPDATE " +
                        budget_table +
                        " SET Category = ?, MaxAllowedMoney = ? WHERE Category = ? AND user = ?";
        String query2 =
                "UPDATE master_budget SET Category = ?, MaxAllowedMoney = ? WHERE Category = ? AND user = ?";
        try (
                PreparedStatement statement = connection2.prepareStatement(query);
                PreparedStatement statement2 = connection2.prepareStatement(query2);
        ) {
            statement.setString(1, newCategory);
            statement.setString(2, newMaxMoney);
            statement.setString(3, category);
            statement.setString(4, user);
            statement2.setString(1, newCategory);
            statement2.setString(2, newMaxMoney);
            statement2.setString(3, category);
            statement2.setString(4, user); // Assuming 'user' is the global variable representing the current user
            statement.executeUpdate();
            statement2.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // private void updateAmountSpent(String category, double spentAmount) {
    // String query = "UPDATE budget SET AmountSpent = AmountSpent + ? WHERE
    // Category = ?";
    // try (PreparedStatement statement = connection2.prepareStatement(query)) {
    // statement.setDouble(1, spentAmount);
    // statement.setString(2, category);
    // statement.executeUpdate();
    // } catch (SQLException e) {
    // e.printStackTrace();
    // }
    // }

    public void deleteUserAccounts() {
        JFrame frame = new JFrame("Delete User Accounts");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(300, 238);
        panel.setPreferredSize(panelSize);

        // Retrieve user information from the database
        String query = "SELECT name, username,`group` FROM users";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Create a scroll pane to hold the user information
            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setPreferredSize(new Dimension(300, 200));

            // Create a panel to hold the user information and delete buttons
            JPanel userPanel = new JPanel();
            userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.PAGE_AXIS));

            // Retrieve user information and populate the panel
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String group = resultSet.getString("group");
                JLabel nameLabel = new JLabel(name);
                JButton deleteButton = new JButton("Delete");

                deleteButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (name.equals("default")) {
                                    if (superAdmin) {
                                        // do nothing
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                frame,
                                                "You can't delete the default user!\nOnly disable it!",
                                                "Warning",
                                                JOptionPane.WARNING_MESSAGE
                                        );
                                        return;
                                    }
                                } else if (name.equals(user) && !superAdmin) {
                                    JOptionPane.showMessageDialog(
                                            frame,
                                            "You can't delete your own account!\nContact another admin to delete your account!",
                                            "Warning",
                                            JOptionPane.WARNING_MESSAGE
                                    );
                                    return;
                                } else if (group.equalsIgnoreCase("superadmin") && !superAdmin) {
                                    JOptionPane.showMessageDialog(
                                            frame,
                                            "Warning! You can not delete the super admin account. \nOnly the super admin account can perform this action",
                                            "Warning",
                                            JOptionPane.WARNING_MESSAGE
                                    );
                                    return;
                                } else if (superAdmin && group.equalsIgnoreCase("superadmin")) {
                                    JOptionPane.showMessageDialog(
                                            frame,
                                            "Seriously?\nYou want to delete your own account?\nYou worked so hard on this program!\nYou know if you delete your account, you'll be logged out and won't be able to log back in right?\nLet's not delete your account ok? Okay cool.",
                                            "Seriously?",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    return;
                                }
                                int confirmDialogResult = JOptionPane.showConfirmDialog(
                                        frame,
                                        "Are you sure you want to delete the account?",
                                        "Confirmation",
                                        JOptionPane.YES_NO_OPTION
                                );

                                if (confirmDialogResult == JOptionPane.YES_OPTION) {
                                    // Perform the delete operation in the database
                                    boolean isUpdateSuccessful = deleteAccountFromDatabase(
                                            username
                                    );
                                    if (isUpdateSuccessful) {
                                        frame.dispose();
                                        JOptionPane.showMessageDialog(
                                                frame,
                                                "User deleted successfully!",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        deleteUserAccounts(); // Refresh the GUI
                                    } else {
                                        frame.dispose();
                                        JOptionPane.showMessageDialog(
                                                frame,
                                                "Failed to delete user!",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                        deleteUserAccounts(); // Refresh the GUI
                                    }
                                }
                            }
                        }
                );

                userPanel.add(nameLabel);
                userPanel.add(deleteButton);
            }

            // Set the user panel as the viewport of the scroll pane
            scrollPane.setViewportView(userPanel);

            // Add the scroll pane to the main panel
            panel.add(scrollPane);

            // Add an exit button
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                            openWelcomeWindow(isAdmin());
                        }
                    }
            );
            panel.add(exitButton);

            // Set up the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public boolean deleteAccountFromDatabase(String username) {
        String query = "DELETE FROM users WHERE username = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private void displayAllUsers() {
        // LogFileViewer viewer = new LogFileViewer();
        JFrame frame = new JFrame("All Users");
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        Dimension panelSize = new Dimension(500, 400);
        panel.setPreferredSize(panelSize);

        // Retrieve user information from the database
        String query = "SELECT name, username, `group`, status FROM users";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Create a DefaultTableModel to store the user data
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Name");
            model.addColumn("Username");
            model.addColumn("Group");
            model.addColumn("Status");

            // Retrieve user information and add them to the model
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String group = resultSet.getString("group");
                String status = resultSet.getString("status");
                model.addRow(new Object[] { name, username, group, status });
            }

            // Create a JTable using the model
            JTable table = new JTable(model);

            // Add the table to a JScrollPane for scrolling if necessary
            JScrollPane scrollPane = new JScrollPane(table);

            // Add the scroll pane to the panel
            panel.add(scrollPane, BorderLayout.CENTER);

            // Create the exit button
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            frame.dispose();
                            openWelcomeWindow(isAdmin());
                        }
                    }
            );

            // Add the exit button to the panel
            panel.add(exitButton, BorderLayout.SOUTH);

            // Set up the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel);
            frame.pack(); // Pack the components to fit the preferred sizes
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public void switchUser() {
        JFrame frame = new JFrame("Switch User");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(300, 100);
        panel.setPreferredSize(panelSize);

        // Retrieve usernames from the database
        String query = "SELECT username FROM users";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Create an array to store usernames
            List<String> usernames = new ArrayList<>();

            // Retrieve usernames and populate the array
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
            usernames.remove("admin");
            usernames.remove(currentUsername);
            // Create the username dropdown
            JComboBox<String> usernameDropdown = new JComboBox<>(
                    usernames.toArray(new String[0])
            );
            panel.add(usernameDropdown);

            // Create the switch user button
            JButton switchUserButton = new JButton("Switch User");
            switchUserButton.addActionListener(e -> {
                String selectedUsername = (String) usernameDropdown.getSelectedItem();
                // String tempUsername = selectedUsername;
                String tempPassword = "";
                String query2 = "SELECT password FROM users WHERE username = ?";
                try (
                        PreparedStatement statement2 = connection.prepareStatement(query2)
                ) {
                    statement2.setString(1, selectedUsername);
                    ResultSet resultSet2 = statement2.executeQuery();

                    if (resultSet2.next()) {
                        String password = resultSet2.getString("password");
                        // Use the password retrieved from the database as needed
                        tempPassword = password;
                    } else {
                        // System.out.println("User not found");
                        // do nothing
                    }
                } catch (SQLException ex) {
                    // Handle any SQL exceptions here
                    ex.printStackTrace();
                }

                try {
                    getUserValue(selectedUsername, tempPassword);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

                boolean testAdmin = false;
                try {
                    testAdmin = testCredentials(selectedUsername, tempPassword);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                isAlsoAdmin = true;
                superAdmin = false;
                String formattedTime = getFormattedTime();
                if (isAlsoAdmin) {
                    writeLog(
                            "Successful login at " +
                                    formattedTime +
                                    " by " +
                                    currentUser +
                                    " as " +
                                    user
                    );
                } else {
                    writeLog(
                            "Successful login at " + formattedTime + " by " + currentUser
                    );
                }
                frame.dispose();
                openWelcomeWindow(testAdmin);
            });

            // Add key listener to usernameDropdown
            usernameDropdown.addKeyListener(
                    new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                                switchUserButton.doClick();
                            }
                        }
                    }
            );

            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
                frame.dispose();
                if (isAlsoAdmin) {
                    openWelcomeWindow(isAdmin());
                } else {
                    try {
                        setAdmin(isUserAdmin());
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    try {
                        getUserValue(currentUsername, currentPassword);
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    openWelcomeWindow(isAdmin());
                }
            });

            JPanel budgetPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

            // Add the buttons to the button panel
            budgetPanel.add(switchUserButton);
            budgetPanel.add(exitButton);

            // Add the button panel below the dropdown options panel
            panel.add(Box.createVerticalGlue()); // Add vertical glue for centering
            panel.add(budgetPanel);

            // Set up the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.getContentPane().add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } catch (SQLException e) {
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void updateUserGroup() {
        welcomeFrame.dispose();
        JFrame updateUserGroupFrame = new JFrame("Update User Group");
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create the scrollable panel
        JPanel updateUserGroupPanel = new JPanel();
        updateUserGroupPanel.setLayout(
                new BoxLayout(updateUserGroupPanel, BoxLayout.PAGE_AXIS)
        );

        // Retrieve user information from the database and store it in a Map
        String query = "SELECT name, `group` FROM users";
        Map<String, String> userGroupsMap = new HashMap<>();
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Retrieve user information and populate the Map
            while (resultSet.next()) {
                String username = resultSet.getString("name");
                String group = resultSet.getString("group");
                userGroupsMap.put(username, group);
            }

            // Remove the unwanted usernames from the Map
            userGroupsMap.remove("default");
            if (!superAdmin) {
                userGroupsMap.remove("Trae");
            }
            userGroupsMap.remove(user);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }

        // Display user information and status dropdowns in the GUI
        for (String username : userGroupsMap.keySet()) {
            JComboBox<String> groupComboBox;
            String group = userGroupsMap.get(username);
            JLabel usernameLabel = new JLabel(username);
            if (superAdmin) {
                groupComboBox =
                        new JComboBox<>(new String[] { "Standard", "Admin", "SuperAdmin" });
            } else {
                groupComboBox = new JComboBox<>(new String[] { "Standard", "Admin" });
            }
            groupComboBox.setSelectedItem(group);

            updateUserGroupPanel.add(usernameLabel);
            updateUserGroupPanel.add(groupComboBox);
        }

        // Create the scroll pane and add the update user group panel
        JScrollPane scrollPane = new JScrollPane(updateUserGroupPanel);

        // Add the scroll pane to the main panel
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Create the button panel
        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update");
        JButton exitButton = new JButton("Exit");

        updateButton.addActionListener(e -> {
            updateButton.removeActionListener(this);
            // Perform the update operation in the database
            for (Component component : updateUserGroupPanel.getComponents()) {
                if (component instanceof JComboBox) {
                    JComboBox<String> groupComboBox = (JComboBox<String>) component;
                    String username =
                            (
                                    (JLabel) updateUserGroupPanel.getComponent(
                                            updateUserGroupPanel.getComponentZOrder(groupComboBox) - 1
                                    )
                            ).getText();
                    if (username.equalsIgnoreCase("Trae")) {
                        String newGroup = groupComboBox.getSelectedItem().toString();
                        JOptionPane.showMessageDialog(
                                updateUserGroupFrame,
                                "Hey man. So, unfortunately this is the original SuperAdmin account. \nThis guy create the whole gosh darn thing you're interacting with.\nAs such, you are not able to set his group type to anything below SuperAdmin. \nHave a nice day. Oh also, this will be reported. Good day.",
                                "You cannot update that person's group",
                                JOptionPane.WARNING_MESSAGE
                        );
                        banUser(currentUser, currentUsername);
                        writeLog(
                                "Warning " +
                                        user +
                                        " has attempted to set Trae, the original SuperAdmin account, and creator to type " +
                                        newGroup +
                                        ". " +
                                        user +
                                        " will be deleted on next logout. This action cannot be revoked."
                        );
                        setToDelete = true;
                        updateUserGroupFrame.dispose();
                        updateUserGroup();
                    } else {
                        String newGroup = groupComboBox.getSelectedItem().toString();
                        try {
                            getGroup(username);
                        } catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                        if (!newGroup.equalsIgnoreCase(userGroupsMap.get(username))) {
                            if (newGroup.equalsIgnoreCase("superadmin")) {
                                Object[] options = {
                                        "Yes, I know and I'm okay with it",
                                        "Oh crap, I didn't realize that. Sorry.",
                                };
                                int choice = JOptionPane.showOptionDialog(
                                        null,
                                        "If you set another user as superadmin, they will then have the same access as you.\n That means that they can delete your account,disable your account,access your account, and many other dangerous things involving your account. \nAre you absolutly sure that you want to do this?",
                                        "Warning!",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.WARNING_MESSAGE,
                                        null,
                                        options,
                                        options[0]
                                );
                                if (choice == JOptionPane.NO_OPTION) {
                                    JOptionPane.showMessageDialog(
                                            updateUserGroupFrame,
                                            "You did not update the user group",
                                            "Update did not occur",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    updateUserGroupFrame.dispose();
                                    updateUserGroup();
                                } else {
                                    isUpdateSuccessful =
                                            updateUserGroupInDatabase(username, newGroup);
                                    if (isUpdateSuccessful) {
                                        JOptionPane.showMessageDialog(
                                                updateUserGroupFrame,
                                                "User status updated successfully!",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        updateUserGroupFrame.dispose();
                                        updateUserGroup();
                                        if (isAlsoAdmin) {
                                            writeLog(
                                                    currentUser +
                                                            " has updated " +
                                                            username +
                                                            " to group type of " +
                                                            newGroup +
                                                            " as " +
                                                            user
                                            );
                                        } else {
                                            writeLog(
                                                    currentUser +
                                                            " has updated " +
                                                            username +
                                                            " to group type of " +
                                                            newGroup
                                            );
                                        }
                                    } else {
                                        JOptionPane.showMessageDialog(
                                                updateUserGroupFrame,
                                                "Failed to update user group",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                    }
                                }
                            } else {
                                isUpdateSuccessful =
                                        updateUserGroupInDatabase(username, newGroup);
                                if (isUpdateSuccessful) {
                                    JOptionPane.showMessageDialog(
                                            updateUserGroupFrame,
                                            "User status updated successfully!",
                                            "Success",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    updateUserGroupFrame.dispose();
                                    updateUserGroup();
                                    if (isAlsoAdmin) {
                                        writeLog(
                                                currentUser +
                                                        " has updated " +
                                                        username +
                                                        " to group type of " +
                                                        newGroup +
                                                        " as " +
                                                        user
                                        );
                                    } else {
                                        writeLog(
                                                currentUser +
                                                        " has updated " +
                                                        username +
                                                        " to group type of " +
                                                        newGroup
                                        );
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(
                                            updateUserGroupFrame,
                                            "Failed to update user group",
                                            "Error",
                                            JOptionPane.ERROR_MESSAGE
                                    );
                                }
                            }
                        } else if (newGroup.equalsIgnoreCase("superadmin")) {
                            Object[] options = {
                                    "Yes, I know and I'm okay with it",
                                    "Oh crap, I didn't realize that. Sorry.",
                            };
                            int choice = JOptionPane.showOptionDialog(
                                    null,
                                    "If you set another user as superadmin, they will then have the same access as you. That means that they can delete your account,disable your account,access your account, and many other dangerous things involving your account. Are you absolutly sure that you want to do this?",
                                    "Warning!",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE,
                                    null,
                                    options,
                                    options[0]
                            );
                            if (choice == JOptionPane.NO_OPTION) {
                                // Code to handle the override logic goes here
                                // For example, update the password history without checking for duplicates
                                // or update the password complexity requirements
                            } else {
                                // Code to handle the "Ok" option (regular display message) goes here
                            }
                        }
                    }
                }
            }
        });

        exitButton.addActionListener(e -> {
            updateUserGroupFrame.dispose();

            openWelcomeWindow(isAdmin());
        });

        buttonPanel.add(updateButton);
        buttonPanel.add(exitButton);

        // Add the button panel to the main panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        updateUserGroupFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        updateUserGroupFrame.setSize(400, 330);
        updateUserGroupFrame.getContentPane().add(mainPanel);
        updateUserGroupFrame.setLocationRelativeTo(null);
        if (!superAdmin) {
            JOptionPane.showMessageDialog(
                    null,
                    "SuperAdmin is not shown because you don't have access",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE
            );
        }
        updateUserGroupFrame.setVisible(true);
    }

    private boolean banUser(String user, String username) {
        String query = "insert into bannedusers set username=?,name=?; ";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user);
            statement.setString(2, username);
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were returned
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Failed to check user banned status",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private boolean updateUserGroupInDatabase(
            String currentName,
            String newGroup
    ) {
        try {
            // Prepare the SQL statement
            String sql = "UPDATE users SET `group` = ? WHERE name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newGroup);
            statement.setString(2, currentName);

            // Execute the update statement
            int rowsAffected = statement.executeUpdate();

            // Close the statement (no need to close the connection since it's passed from
            // outside)

            // Check the number of rows affected to determine if the update was successful
            if (rowsAffected > 0) {
                // Show a success message or perform any additional actions
                setAdmin(isUserAdmin());
                return true;
            } else {
                // Show a failed message or perform any additional actions
                // JOptionPane.showMessageDialog(null, "User status failed to update!");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
            // Handle any errors that occurred during the database operation
        }
    }

    public void updateUserAccountStatus() {
        // GUI setup
        JFrame frame = new JFrame("User Account Status");
        JPanel mainPanel = new JPanel(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));

        // Retrieve user information from the database
        String query = "SELECT name, username, status FROM users";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // Create arrays to store user information
            String[] names = new String[100]; // Assuming a maximum of 100 users
            String[] usernames = new String[100];
            String[] statuses = new String[100];
            statusComboBoxes = new JComboBox[100];

            // Retrieve user information and populate arrays
            i = 0;
            while (resultSet.next()) {
                names[i] = resultSet.getString("name");
                usernames[i] = resultSet.getString("username");
                statuses[i] = resultSet.getString("status");

                // Create a new JComboBox and store it in the array
                statusComboBoxes[i] =
                        new JComboBox<>(new String[] { "Enabled", "Disabled" });

                // Set the initial status based on the retrieved value
                String currentStatus = statuses[i];
                statusComboBoxes[i].setSelectedItem(currentStatus);

                i++;
            }
            int count = i;
            // Display user information and status dropdowns in the GUI
            for (int j = 0; j < i; j++) {
                JLabel nameLabel = new JLabel(names[j]);
                if (names[j].equals("default") || names[j].equals(user)) {
                    continue;
                }
                JComboBox<String> statusComboBox = new JComboBox<>(
                        new String[] { "Enabled", "Disabled" }
                );

                // Set the initial status based on the retrieved value
                String currentStatus = statuses[j];
                statusComboBox.setSelectedItem(currentStatus);

                panel.add(nameLabel);
                panel.add(statusComboBox);

                // Store the JComboBox reference for later use
                statusComboBoxes[j] = statusComboBox;
            }

            // Create a scroll pane for the user information panel
            JScrollPane scrollPane = new JScrollPane(panel);
            scrollPane.setVerticalScrollBarPolicy(
                    JScrollPane.VERTICAL_SCROLLBAR_ALWAYS
            );
            scrollPane.getVerticalScrollBar().setUnitIncrement(20);

            // Add an update button panel
            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JButton updateButton = new JButton("Update");
            updateButton.addActionListener(
                    new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            i = count;
                            String selectedStatus = "";
                            // Update account status in the database based on the selected values
                            for (int k = 0; k < i; k++) {
                                String currentStatus = statuses[k];
                                String usernameToBeUpdated = usernames[k];
                                selectedStatus = (String) statusComboBoxes[k].getSelectedItem();
                                if (!selectedStatus.equals(currentStatus)) {
                                    if (
                                            usernameToBeUpdated.equals("s412924") &&
                                                    !group.equalsIgnoreCase("superadmin")
                                    ) {
                                        JOptionPane.showMessageDialog(
                                                null,
                                                "You can not disable this account!\nYou do not have access",
                                                "Warning",
                                                JOptionPane.WARNING_MESSAGE
                                        );
                                        frame.dispose();
                                        updateUserAccountStatus();
                                        return;
                                    }
                                    boolean isUpdateSuccessful = updateAccountStatusInDatabase(
                                            usernames[k],
                                            selectedStatus
                                    );
                                    if (isUpdateSuccessful) {
                                        String formattedTime = getFormattedTime();
                                        if (isAlsoAdmin) {
                                            writeLog(
                                                    currentUser +
                                                            " has updated " +
                                                            usernames[k] +
                                                            " from " +
                                                            currentStatus +
                                                            " to " +
                                                            selectedStatus +
                                                            " at " +
                                                            formattedTime +
                                                            " as " +
                                                            user
                                            );
                                        } else {
                                            writeLog(
                                                    currentUser +
                                                            " has updated " +
                                                            usernames[k] +
                                                            " from " +
                                                            currentStatus +
                                                            " to " +
                                                            selectedStatus +
                                                            " at " +
                                                            formattedTime
                                            );
                                        }
                                        JOptionPane.showMessageDialog(
                                                frame,
                                                "User status updated successfully!",
                                                "Success",
                                                JOptionPane.INFORMATION_MESSAGE
                                        );
                                        frame.dispose();
                                        updateUserAccountStatus();
                                    } else {
                                        String formattedTime = getFormattedTime();
                                        writeLog(
                                                currentUser +
                                                        " failed to update " +
                                                        usernames[k] +
                                                        " from " +
                                                        currentStatus +
                                                        " to " +
                                                        selectedStatus +
                                                        " at " +
                                                        formattedTime
                                        );
                                        JOptionPane.showMessageDialog(
                                                frame,
                                                "Failed to update user status",
                                                "Error",
                                                JOptionPane.ERROR_MESSAGE
                                        );
                                        frame.dispose();
                                        updateUserAccountStatus();
                                    }
                                }
                            }
                        }
                    }
            );

            buttonPanel.add(updateButton);
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e -> {
                frame.dispose();
                openWelcomeWindow(isAdmin);
            });
            buttonPanel.add(exitButton);

            // Set up the frame
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            mainPanel.add(scrollPane, BorderLayout.CENTER);
            mainPanel.add(buttonPanel, BorderLayout.SOUTH);
            frame.getContentPane().add(mainPanel);
            frame.setVisible(true);
        } catch (SQLException e) {
            // Handle any SQL exceptions
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public String getName(String username) {
        String name = null;
        String query = "SELECT name FROM users WHERE username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                name = resultSet.getString("name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return name;
    }

    private boolean accountDisabled(String username) {
        if (disabledUsers.contains(username)) {
            return true;
        }
        return false;
    }

    private boolean updateAccountStatusInDatabase(
            String username,
            String status
    ) {
        String query = "";
        if (!accountDisabled(username)) {
            username = getName(username);
        }
        query = "UPDATE users SET status = ? WHERE name = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, status);
            statement.setString(2, username);
            int affectedRows = statement.executeUpdate();

            // Check if any rows were affected by the update operation
            if (affectedRows > 0) {
                // Increase window height by 5
                int currentHeight = frame.getHeight();
                int newHeight = currentHeight + 10;
                frame.setSize(frame.getWidth(), newHeight);

                return true;
                // updateUserAccountStatus();
            } else {
                return false;
                // JOptionPane.showMessageDialog(frame, "Failed to update user status", "Error",
                // JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Failed to update user status",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private boolean isUserAdmin() throws SQLException {
        String query = "SELECT `group` FROM users WHERE name = ? AND username = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, currentUser);
            statement.setString(2, currentUsername);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String group = resultSet.getString("group");
                    if ("superadmin".equalsIgnoreCase(group)) {
                        superAdmin = true;
                    }
                    return (
                            "admin".equalsIgnoreCase(group) ||
                                    "superadmin".equalsIgnoreCase(group)
                    );
                }
            }
        }
        return false;
    }

    private void changeUsername() {
        // LocalTime currentTime = LocalTime.now();
        // int hour = currentTime.getHour();
        // int minute = currentTime.getMinute();
        // int second = currentTime.getSecond();
        String newUsername = JOptionPane.showInputDialog(
                frame,
                "Enter new username:",
                "Change Username",
                JOptionPane.OK_CANCEL_OPTION
        );
        // Notify the user that the password has been changed
        if (newUsername != null) {
            try {
                updateUsernameInDatabase(newUsername);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                JOptionPane.showMessageDialog(
                        null,
                        "An error occured\nError code " + e.getStackTrace(),
                        "Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }
            String formattedTime = getFormattedTime();
            if (isAlsoAdmin) {
                writeLog(
                        currentUser +
                                " changed " +
                                user +
                                "'s username successfully at " +
                                formattedTime +
                                " as " +
                                currentUser
                );
            } else {
                writeLog("Username changed successfully at " + formattedTime);
            }
        } else {
            openWelcomeWindow(isAdmin());
        }
    }

    private void setNewUser() throws SQLException {
        // LocalTime currentTime = LocalTime.now();
        // int hour = currentTime.getHour();
        // int minute = currentTime.getMinute();
        // int second = currentTime.getSecond();
        JTextField newUser = new JTextField(20);
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JFrame newUserframe = new JFrame("Set new User");

        // Set up the login form
        JPanel newUserPanel = new JPanel();
        newUserPanel.setLayout(new BoxLayout(newUserPanel, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(250, 250);
        newUserPanel.setPreferredSize(panelSize);
        newUserPanel.add(new JLabel("Name: "));
        newUserPanel.add(newUser);
        newUserPanel.add(new JLabel("Username:"));
        newUserPanel.add(usernameTextField);
        newUserPanel.add(new JLabel("Password:"));
        newUserPanel.add(passwordField);
        String[] userOptions = { "Standard", "Admin" };
        JComboBox<String> userDropdown = new JComboBox<>(userOptions);
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(30, 1, 365, 1);
        expirationTime = new JSpinner(spinnerModel);
        newUserPanel.add(new JLabel("Expiration time"));
        newUserPanel.add(expirationTime);
        if (isAdmin() || isDefaultCredentials) {
            newUserPanel.add(new JLabel("Select User Type: "));
            newUserPanel.add(userDropdown);
        }
        // Add the login button
        JButton setButton = new JButton("Set");
        Action setButtonAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedUserType = (String) userDropdown.getSelectedItem();
                String newUserString = newUser.getText();
                String newUsernameString = usernameTextField.getText();
                boolean isBanned = checkIfBanned(newUsernameString, newUserString);

                char[] newPasswordChars = passwordField.getPassword();
                String newPasswordHash = new String(newPasswordChars);
                if (newPasswordHash.contains(newUsernameString)) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Password is not secure\nSet a different one",
                            "Unsecure Password",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                boolean strong = PasswordStrengthTester.isStrongPassword(
                        newPasswordHash
                );
                if (!strong) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Password is not secure\nSet a different one",
                            "Unsecure Password",
                            JOptionPane.WARNING_MESSAGE
                    );
                    return;
                }
                // Hash the new password
                String salt = generateRandomSalt();
                newPasswordHash = hashPassword((newPasswordHash + salt).toCharArray());

                // Clear the password char array for security reasons
                Arrays.fill(newPasswordChars, '\0');
                Object[] options = {
                        "Unban account and create user",
                        "Keep account banned and don't create user",
                };
                if (isBanned) {
                    if (superAdmin) {
                        int choice = JOptionPane.showOptionDialog(
                                null,
                                "This user account has been banned because \nthey did something outside of the regulation guidlines. \nYou may unban it if you want.",
                                "Banned Account",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE,
                                null,
                                options,
                                options[0]
                        );
                        if (choice == 0) {
                            unbanAccount(newUsernameString);
                            try {
                                int newUserId = generateUniqueId(); // Generate a unique id for the new user
                                int expTime = (int) expirationTime.getValue();

                                writeDatabase(
                                        newUserId,
                                        newUserString,
                                        newUsernameString,
                                        newPasswordHash,
                                        selectedUserType,
                                        "enabled",
                                        null,
                                        salt,
                                        expTime
                                );
                                String formattedTime = getFormattedTime();
                                if (isAlsoAdmin) {
                                    writeLog(
                                            "New user: " +
                                                    newUserString +
                                                    " created successfully at " +
                                                    formattedTime +
                                                    " as a " +
                                                    selectedUserType +
                                                    " by " +
                                                    currentUser +
                                                    " as " +
                                                    user
                                    );
                                } else {
                                    writeLog(
                                            "New user: " +
                                                    newUserString +
                                                    " created successfully at " +
                                                    formattedTime +
                                                    " as a " +
                                                    selectedUserType +
                                                    " by " +
                                                    currentUser
                                    );
                                }
                                if (!user.equals("default")) {
                                    isDefaultCredentials = false;
                                }
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "New user " + newUserString + " created!",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                newUserframe.dispose();
                                openWelcomeWindow(isAdmin());
                            } catch (SQLException ex) {
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Failed to create a new user.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE
                                );
                            }
                        } else {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "New user " +
                                            newUsernameString +
                                            " will not be created. They will also not be unbanned",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else if (isAdmin) {
                        JOptionPane.showMessageDialog(
                                frame,
                                "New user " +
                                        newUsernameString +
                                        " will not be created. \nThey will also not be unbanned\nPlease contact a SuperAdmin to unban the account",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                    } else {
                        try {
                            int newUserId = generateUniqueId(); // Generate a unique id for the new user
                            int expTime = (int) expirationTime.getValue();

                            writeDatabase(
                                    newUserId,
                                    newUserString,
                                    newUsernameString,
                                    newPasswordHash,
                                    selectedUserType,
                                    "enabled",
                                    null,
                                    salt,
                                    expTime
                            );
                            String formattedTime = getFormattedTime();
                            if (isAlsoAdmin) {
                                writeLog(
                                        "New user: " +
                                                newUserString +
                                                " created successfully at " +
                                                formattedTime +
                                                " as a " +
                                                selectedUserType +
                                                " by " +
                                                currentUser +
                                                " as " +
                                                user
                                );
                            } else {
                                writeLog(
                                        "New user: " +
                                                newUserString +
                                                " created successfully at " +
                                                formattedTime +
                                                " as a " +
                                                selectedUserType +
                                                " by " +
                                                currentUser
                                );
                            }
                            if (!user.equals("default")) {
                                isDefaultCredentials = false;
                            }
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "New user " + newUserString + " created!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                            newUserframe.dispose();
                            openWelcomeWindow(isAdmin());
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Failed to create a new user.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                }
            }
        };

        setButton.addActionListener(setButtonAction);

        // Add key listener to passwordField
        passwordField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            setButtonAction.actionPerformed(null);
                        }
                    }
                }
        );

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            newUserframe.dispose();
            if (!user.equals("default")) {
                isDefaultCredentials = false;
            }
            openWelcomeWindow(isAdmin());
        });
        newUserPanel.add(setButton);
        newUserPanel.add(exitButton);
        newUserframe.setContentPane(newUserPanel);
        newUserframe.addWindowListener(myWindowAdapter);
        newUserframe.pack();
        newUserframe.setLocationRelativeTo(null);

        newUserframe.setVisible(true);

        // Close the login window
        welcomeFrame.dispose();
    }

    private boolean unbanAccount(String username) {
        String query = "DELETE FROM bannedUsers where username=?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were returned
            if (rowsAffected > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Failed to delete banned user status",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private boolean checkIfBanned(String username, String name) {
        String query = "SELECT username FROM bannedusers WHERE username=?";
        String query2 = "Select name FROM bannedusers where name=?";
        try (
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement statement2 = connection.prepareStatement(query2)
        ) {
            statement.setString(1, username);
            statement2.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            ResultSet resultSet2 = statement2.executeQuery();

            // Check if any rows were returned
            if (resultSet.next() || resultSet2.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    frame,
                    "Failed to check user banned status",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
            // Handle any SQL exceptions here
            JOptionPane.showMessageDialog(
                    null,
                    "An error occurred\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            return false;
        }
    }

    private static int generateUniqueId() {
        Random random = new Random();
        int min = 10000000; // Minimum value for the random number
        int max = 99999999; // Maximum value for the random number
        int uniqueId = random.nextInt(max - min + 1) + min;
        return uniqueId;
    }

    private void promptAdminCredentials() {
        JFrame UAframe = new JFrame("User Authentication");
        JPanel UC = new JPanel();
        // Set up the login form
        UC.setLayout(new BoxLayout(UC, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(275, 125);
        UC.setPreferredSize(panelSize);
        usernameTextField = new JTextField(20);
        passwordField = new JPasswordField(20);
        UC.add(new JLabel("Username:"));
        UC.add(usernameTextField);
        UC.add(new JLabel("Password:"));
        UC.add(passwordField);
        JButton setButton = new JButton("Set");
        setButton.addActionListener(e -> {
            String UsernameString = usernameTextField.getText();
            char[] newPasswordChars = passwordField.getPassword();
            // Hash the new password
            PasswordHash = hashPassword(newPasswordChars);
            // Clear the password char array for security reasons
            java.util.Arrays.fill(newPasswordChars, '\0');
            UAframe.dispose();
            try {
                testAdminCredentials(UsernameString, PasswordHash);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
        usernameTextField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            UAframe.dispose();
                            try {
                                setNewUser();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
        );

        // Add key listener to passwordField
        passwordField.addKeyListener(
                new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            UAframe.dispose();
                            try {
                                setNewUser();
                            } catch (SQLException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
        );
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            UAframe.dispose();
            openWelcomeWindow(isAdmin());
        });
        UC.add(setButton);
        UC.add(cancelButton);
        UAframe.setContentPane(UC);
        UAframe.addWindowListener(myWindowAdapter);
        UAframe.pack();
        UAframe.setLocationRelativeTo(null);
        UAframe.setVisible(true);
    }

    private void testAdminCredentials(String username, String passwordHash)
            throws SQLException {
        String query =
                "SELECT * FROM users WHERE username = ? AND password = ? AND `group` = 'admin' OR `group` = superadmin";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, passwordHash);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    setAdmin(true);
                    String formattedTime = getFormattedTime();
                    if (adminIsHacked) {
                        if (isAlsoAdmin) {
                            setAdmin(true);
                            adminIsHacked = false;
                        } else {
                            setAdmin(false);
                            writeLog(
                                    user +
                                            " has attempted to hack the admin account at " +
                                            formattedTime +
                                            " Account will be disabled on logout"
                            );
                            updateAccountStatusInDatabase(user, "disabled");
                            adminIsHacked = false;
                            openWelcomeWindow(isAdmin());
                            return;
                        }
                    }
                    if (clearingLogs) {
                        clearLogs();
                    } else {
                        try {
                            setNewUser();
                        } catch (SQLException e) {
                            JOptionPane.showMessageDialog(
                                    null,
                                    "An error occured\nError code " + e.getStackTrace(),
                                    "Failed",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                } else {
                    if (isAlsoAdmin) {
                        setAdmin(true);
                        if (adminIsHacked) {
                            String formattedTime = getFormattedTime();
                            adminIsHacked = false;
                            writeLog(
                                    currentUser +
                                            " was testing the security at " +
                                            formattedTime +
                                            "." +
                                            user +
                                            " will not be affected since it's a security test." +
                                            formattedTime
                            );
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Invalid username or password\nYou've attempted to hack the admin account\nYour account has been disabled and you will be logged out\nContact an administrator to unlock your account",
                                    "Login Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            boolean success = updateAccountStatusInDatabase(user, "disabled");
                            if (success) {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Security test complete. \nRe-enabling " + user,
                                        "Test complete",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                updateAccountStatusInDatabase(user, "enabled");
                                writeLog(
                                        "Security test on " +
                                                user +
                                                " complete at " +
                                                formattedTime +
                                                " " +
                                                user +
                                                " has been re-enabled"
                                );
                            } else {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        "Security test failed. Check code and try again.\nRe-enabling " +
                                                user,
                                        "Test failed",
                                        JOptionPane.ERROR_MESSAGE
                                );
                                writeLog(
                                        "Security test on " +
                                                user +
                                                " failed at " +
                                                formattedTime +
                                                " " +
                                                user +
                                                " has been re-enabled"
                                );
                            }
                            switchUser();
                            return;
                        }
                    } else {
                        setAdmin(false);
                        if (adminIsHacked) {
                            adminIsHacked = false;
                            String formattedTime = getFormattedTime();
                            writeLog(
                                    user +
                                            " has attempted to hack the admin account at " +
                                            formattedTime +
                                            " Account has been disabled"
                            );
                            updateAccountStatusInDatabase(user, "disabled");
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Invalid username or password\nYou've attempted to hack the admin account\nYour account has been disabled and you will be logged out\nContact an administrator to unlock your account",
                                    "Login Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            initializeGUI();
                            return;
                        }
                        String formattedTime = getFormattedTime();
                        if (adminIsHacked) {
                            adminIsHacked = false;
                            writeLog(
                                    user +
                                            " has attempted to hack the admin account at " +
                                            formattedTime +
                                            " Account has been disabled"
                            );
                            updateAccountStatusInDatabase(user, "disabled");
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Invalid username or password\nYou've attempted to hack the admin account\nYour account has been disabled and you will be logged out\nContact an administrator to unlock your account",
                                    "Login Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            initializeGUI();
                            return;
                        } else {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Invalid username or password",
                                    "Login Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                            writeLog("Failed admin login attempt at " + formattedTime);
                            openWelcomeWindow(isAdmin());
                        }
                    }
                }
            }
        }
    }

    public List<String> getDisabledUsernames() throws SQLException {
        String query = "SELECT name FROM users WHERE status = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "disabled");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String username = resultSet.getString("name");
                disabledUsers.add(username);
            }
        }

        return disabledUsers;
    }

    // private boolean adminPrompt(String username, String passwordHash) {
    // String query = "SELECT * FROM users WHERE username = ? AND password = ? AND
    // `group` = 'admin'";
    // try (PreparedStatement statement = connection.prepareStatement(query)) {
    // statement.setString(1, username);
    // statement.setString(2, passwordHash);
    // try (ResultSet resultSet = statement.executeQuery()) {
    // if (resultSet.next()) {
    // isAdmin = true;
    // return true;
    // } else {
    // isAdmin = false;
    // JOptionPane.showMessageDialog(frame, "Invalid username or password", "Login
    // Error",
    // JOptionPane.ERROR_MESSAGE);
    // String formattedTime = getFormattedTime();
    // writeLog("Failed admin login attempt at " + formattedTime);
    // return false;
    // }
    // }
    // } catch (SQLException e) {
    // e.printStackTrace();
    // return false;
    // }
    // }

    private void updateUsernameInDatabase(String newUsername)
            throws SQLException {
        String query = "UPDATE users SET username = ? WHERE name = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newUsername);
            statement.setString(2, user);

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(
                        frame,
                        "Username updated successfully",
                        "Change Password",
                        JOptionPane.INFORMATION_MESSAGE
                );
                openWelcomeWindow(isAdmin());
            } else {
                JOptionPane.showMessageDialog(
                        frame,
                        "Failed to update the username.",
                        "Change Password",
                        JOptionPane.INFORMATION_MESSAGE
                );
                openWelcomeWindow(isAdmin());
            }
        }
    }

    private void changePasswordRequired() throws SQLException {
        String newPasswordHash = "";
        while (!strong) {
            // Create a custom JPanel to hold the password field
            JPanel panel = new JPanel();
            JPasswordField passwordField = new JPasswordField(10);
            passwordField.setEchoChar('*'); // Set the echo character to hide the input
            panel.add(new JLabel("Enter new password:"));
            panel.add(passwordField);

            // Show the input dialog with the custom panel
            int option = JOptionPane.showOptionDialog(
                    frame,
                    panel,
                    "Change Password",
                    JOptionPane.OK_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    null,
                    null
            );

            if (option == JOptionPane.OK_OPTION) {
                // Retrieve the new password
                char[] newPasswordChars = passwordField.getPassword();
                String newPassword = new String(newPasswordChars);
                strong = PasswordStrengthTester.isStrongPassword(newPassword);
                List<String> passwords = RetrievePasswords.retrievePasswords(
                        user,
                        connection
                );
                if (passwords.contains(newPassword)) {
                    if (superAdmin) {
                        Object[] options = { "Ok", "Override" };
                        int choice = JOptionPane.showOptionDialog(
                                null,
                                "You can't use an old password!\nPlease try again",
                                "Error",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.ERROR_MESSAGE,
                                null,
                                options,
                                options[0]
                        );

                        if (choice == JOptionPane.NO_OPTION) {
                            // Code to handle the override logic goes here
                            // For example, update the password history without checking for duplicates
                            // or update the password complexity requirements
                        } else {
                            // Code to handle the "Ok" option (regular display message) goes here
                            changePasswordRequired();
                        }
                    } else {
                        JOptionPane.showMessageDialog(
                                null,
                                "You can't use an old password!\nPlease try again",
                                "Error",
                                JOptionPane.ERROR_MESSAGE
                        );
                        changePasswordRequired();
                    }
                }

                // Clear the password char array for security reasons
                java.util.Arrays.fill(newPasswordChars, '\0');

                if (strong && !newPassword.contains(user)) {
                    // Hash the new password
                    String salt = "";
                    String saltQuery = "SELECT salt FROM users WHERE name='" + user + "'";
                    try (
                            PreparedStatement statement = connection.prepareStatement(saltQuery)
                    ) {
                        ResultSet resultSet = statement.executeQuery();
                        if (resultSet.next()) {
                            salt = resultSet.getString("salt");
                        }
                    }

                    newPasswordHash = hashPassword((newPassword + salt).toCharArray());
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "Password is not secure or contains your username\nSet a different one",
                            "Invalid Password",
                            JOptionPane.WARNING_MESSAGE
                    );
                }
            } else {
                // User clicked Cancel or closed the dialog
                JOptionPane.showMessageDialog(
                        null,
                        "You MUST change your password to match the requirements",
                        "Password Change Required",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }

        // Update the password in the database
        updatePasswordInDatabase(user, newPasswordHash);

        String formattedTime = getFormattedTime();
        if (isAlsoAdmin) {
            writeLog(
                    user +
                            "'s Password changed successfully at " +
                            formattedTime +
                            " by " +
                            currentUser +
                            " as " +
                            user
            );
            strong = true;
            passwordChange = false;
            openWelcomeWindow(isAdmin);
        } else {
            writeLog(
                    user +
                            "'s Password changed successfully at " +
                            formattedTime +
                            " by " +
                            user
            );
            strong = true;
            passwordChange = false;
            openWelcomeWindow(isAdmin);
        }
    }

    private void changePassword() throws SQLException {
        // LocalTime currentTime = LocalTime.now();
        // int hour = currentTime.getHour();
        // int minute = currentTime.getMinute();
        // int second = currentTime.getSecond();

        // Create a custom JPanel to hold the password field
        JPanel panel = new JPanel();
        JPasswordField passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*'); // Set the echo character to hide the input
        panel.add(new JLabel("Enter new password:"));
        panel.add(passwordField);

        // Show the input dialog with the custom panel
        int option = JOptionPane.showOptionDialog(
                frame,
                panel,
                "Change Password",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null
        );
        boolean strong = false;

        if (option == JOptionPane.OK_OPTION) {
            // Retrieve the new password
            char[] newPasswordChars = passwordField.getPassword();
            String newPassword = new String(newPasswordChars);
            strong = PasswordStrengthTester.isStrongPassword(newPassword);
            List<String> passwords = RetrievePasswords.retrievePasswords(
                    user,
                    connection
            );
            passwords.clear();
            if (!strong || newPassword.contains(user)) {
                JOptionPane.showMessageDialog(
                        null,
                        "Password is not secure\nSet a different one",
                        "Unsecure Password",
                        JOptionPane.WARNING_MESSAGE
                );
                changePassword();
            }
            String salt = "";
            String saltQuery = "SELECT salt FROM users WHERE name='" + user + "'";
            try (
                    PreparedStatement statement = connection.prepareStatement(saltQuery)
            ) {
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    salt = resultSet.getString("salt");
                }
            }

            // Hash the new password
            String newPasswordHash = hashPassword((newPassword + salt).toCharArray());
            if (passwords.contains(newPasswordHash)) {
                if (superAdmin) {
                    Object[] options = { "Ok", "Override" };
                    int choice = JOptionPane.showOptionDialog(
                            null,
                            "You can't use an old password!\nPlease try again",
                            "Error",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.ERROR_MESSAGE,
                            null,
                            options,
                            options[0]
                    );

                    if (choice == JOptionPane.NO_OPTION) {
                        updatePasswordInDatabase(user, newPasswordHash);
                    } else {
                        // Code to handle the "Ok" option (regular display message) goes here
                        changePassword();
                    }
                } else {
                    JOptionPane.showMessageDialog(
                            null,
                            "You can't use an old password!\nPlease try again",
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    changePassword();
                }
            }
            // Clear the password char array for security reasons
            java.util.Arrays.fill(newPasswordChars, '\0');

            // Update the password in the database
            updatePasswordInDatabase(user, newPasswordHash);

            String formattedTime = getFormattedTime();
            if (isAlsoAdmin) {
                writeLog(
                        user +
                                "'s Password changed successfully at " +
                                formattedTime +
                                " by " +
                                currentUser +
                                " as " +
                                user
                );
                openWelcomeWindow(isAdmin);
            } else {
                writeLog(
                        user +
                                "'s Password changed successfully at " +
                                formattedTime +
                                " by " +
                                user
                );
                openWelcomeWindow(isAdmin);
            }
        } else if (
                option == JOptionPane.CANCEL_OPTION || option == JOptionPane.CLOSED_OPTION
        ) {
            openWelcomeWindow(isAdmin);
            return;
        }
    }

    private void updatePasswordInDatabase(String name, String newPasswordHash) {
        String query = "UPDATE users SET password = ? WHERE name = ?";
        String query2 =
                "INSERT INTO passwordHistory (user, password) VALUES (?, ?)";
        String query3 =
                "UPDATE passwordExpiration SET lastChangedDate = ? WHERE user = ?";
        String query4 =
                "SELECT lastChangedDate FROM passwordExpiration WHERE user = ?";

        try (
                PreparedStatement statement = connection.prepareStatement(query);
                PreparedStatement statement2 = connection.prepareStatement(query2);
                PreparedStatement statement3 = connection.prepareStatement(query3);
                PreparedStatement statement4 = connection.prepareStatement(query4)
        ) {
            // Set parameters for query1 (updating password in users table)
            statement.setString(1, newPasswordHash);
            statement.setString(2, name);

            // Set parameters for query2 (inserting into passwordHistory table)
            statement2.setString(1, name);
            statement2.setString(2, newPasswordHash);

            // Set parameters for query3 (updating passwordExpiration table with current
            // date)
            Date currentDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
            statement3.setDate(1, sqlDate);
            statement3.setString(2, name);
            // Execute the updates
            int rowsAffected1 = statement.executeUpdate();
            int rowsAffected2 = statement2.executeUpdate();
            statement4.setString(1, name);
            // Check if the current date is the same as in the database before considering
            int rowsAffected3 = statement3.executeUpdate();
            // it in the success condition
            boolean currentDateEqualsDatabaseDate = false;
            try (ResultSet resultSet = statement4.executeQuery()) {
                if (resultSet.next()) {
                    java.sql.Date dateInDatabase = resultSet.getDate("lastChangedDate");
                    currentDateEqualsDatabaseDate =
                            dateInDatabase.toString().equals(sqlDate.toString());
                } else {
                    System.out.println(sqlDate.toString());
                }
            }

            if (
                    rowsAffected1 > 0 &&
                            rowsAffected2 > 0 &&
                            (currentDateEqualsDatabaseDate || rowsAffected3 > 0)
            ) {
                // Display success message
                JOptionPane.showMessageDialog(
                        frame,
                        "Password updated successfully",
                        "Change Password",
                        JOptionPane.INFORMATION_MESSAGE
                );
                // openWelcomeWindow(isAdmin);
                return;
            } else {
                // Display failure message
                JOptionPane.showMessageDialog(
                        frame,
                        "Failed to update the password.",
                        "Change Password",
                        JOptionPane.ERROR_MESSAGE
                );
                openWelcomeWindow(isAdmin);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    null,
                    "An error occured\nError code " + e.getStackTrace(),
                    "Failed",
                    JOptionPane.ERROR_MESSAGE
            );
            // Handle SQLException based on your application's requirements
        }
    }

    public static String generateRandomSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16]; // 16 bytes = 128 bits
        random.nextBytes(saltBytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : saltBytes) {
            sb.append(String.format("%02x", b));
        }
        System.out.println(sb.toString());
        return sb.toString();
    }

    private String oldhashPassword(char[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(new String(password).getBytes());
            byte[] hashedPassword = md.digest();
            // Convert the hashed password to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    // public static String hashPassword(char[] password, String salt) {
    public static String hashPassword(char[] password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedPassword = md.digest(
                    new String(password).getBytes(StandardCharsets.UTF_8)
            );
            // Convert the hashed password to a hexadecimal string
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedPassword) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private void readLog() {
        JFrame frame = new JFrame("Log File");
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        Dimension panelSize = new Dimension(300, 400);
        panel.setPreferredSize(panelSize);
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(800, 600));

        panel.add(scrollPane, BorderLayout.CENTER);
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            "C:\\Users\\Traee\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\log.txt"
                    )
            );
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException e) {
            textArea.setText("Log file not found.");
        }
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            openWelcomeWindow(isAdmin());
        });
        JButton clearButton = new JButton("Clear");
        clearButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                    null,
                    "Are you sure you want to clear the logs?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION
            );

            // Check user's response
            if (choice == JOptionPane.YES_OPTION) {
                // User clicked Yes, proceed with clearing the logs
                clearingLogs = true;
                frame.dispose();
                promptAdminCredentials();
            } else {
                // User clicked No or closed the dialog, do nothing or handle the cancellation
                // accordingly
            }
        });
        // panel.add(textArea);
        panel.add(clearButton);
        panel.add(exitButton);
        // Set up the frame
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private static void writeLog(String logEntry) {
        try (
                PrintWriter out = new PrintWriter(
                        new FileWriter(
                                "C:\\Users\\Traee\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\log.txt",
                                true
                        )
                )
        ) {
            out.println(logEntry);
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
    }

    private static void writeDatabase(
            int id,
            String user,
            String username,
            String password,
            String userGroup,
            String Status,
            Object lastLogin,
            String salt,
            int expTime
    ) throws SQLException {
        String query =
                "INSERT INTO users (id, name, username, password, `group`, status, last_login, salt, expiration_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, user);
            statement.setString(3, username);
            statement.setString(4, password);
            statement.setString(5, userGroup);
            statement.setString(6, Status);
            statement.setString(7, (String) lastLogin);
            statement.setString(8, salt);
            statement.setInt(9, expTime);
            statement.executeUpdate();
        }
    }

    private static void showFirstRunPopup() throws InterruptedException {
        JFrame popupFrame = new JFrame();
        Object[] options = { "Yes", "No", "Close" };

        int choice = JOptionPane.showOptionDialog(
                popupFrame,
                "Have you logged in before?",
                "First Run",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == JOptionPane.YES_OPTION) {
            // Process the username and password as needed
            loginGUI = new LoginGUI();
        } else if (choice == JOptionPane.NO_OPTION) {
            // Display default username and password
            JOptionPane.showMessageDialog(
                    popupFrame,
                    "Default username: admin\nDefault password: admin",
                    "Default Credentials",
                    JOptionPane.INFORMATION_MESSAGE
            );
            loginGUI = new LoginGUI();
            try (
                    PreparedStatement statement = connection.prepareStatement(
                            "UPDATE users SET status='Enabled' WHERE name=?"
                    )
            ) {
                statement.setString(1, "default");
                statement.executeUpdate();
                // System.out.println("SQL statement executed successfully.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else {
            // Close the program
            System.exit(0);
        }
    }

    public void clearLogs() {
        int confirmOption = JOptionPane.showConfirmDialog(
                null,
                "Are you sure you want to clear the logs?",
                "Confirmation",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmOption == JOptionPane.YES_OPTION) {
            // User clicked Yes, perform the desired action (clear logs in your case)
            int option = JOptionPane.showConfirmDialog(
                    null,
                    "Remember\nLog files serve as a crucial tool for recording and storing valuable information about the\nactivities, events, and errors that occur within a system or application. They capture\nimportant data such as system status, user interactions, error messages, and performance\nmetrics. The purpose of log files is to provide a historical record and a diagnostic resource for\ntroubleshooting, debugging, auditing, and monitoring purposes. By analyzing log files, developers,\nsystem administrators, and support teams can gain insights into the system's\nbehavior, identify issues, track events, and make informed decisions to ensure optimal\nsystem performance, security, and reliability.",
                    "Confirmation",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (option == JOptionPane.OK_OPTION) {
                // User clicked OK, perform the desired action (clear logs in your case)
                String filePath =
                        "C:\\Users\\Traee\\OneDrive - University of North Florida\\Programming\\College Programming\\Programming 2\\Codes\\MyGUI\\log.txt";

                try {
                    // Open the file in write mode
                    PrintWriter writer = new PrintWriter(new FileWriter(filePath));

                    // Clear the contents of the file by closing and reopening it
                    writer.close();
                    writer = new PrintWriter(new FileWriter(filePath));

                    // Close the file
                    writer.close();
                    if (isAlsoAdmin) {
                        String formattedTime = getFormattedTime();
                        writeLog(
                                currentUser +
                                        " has cleared the logs as " +
                                        user +
                                        " at " +
                                        formattedTime
                        );
                    } else {
                        String formattedTime = getFormattedTime();
                        writeLog(user + " has cleared the log files at " + formattedTime);
                    }
                    JOptionPane.showMessageDialog(
                            null,
                            "Log files cleared!",
                            "Cleared Log files",
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (isAlsoAdmin) {
                        String formattedTime = getFormattedTime();
                        writeLog(
                                currentUser +
                                        " accessing the log files as " +
                                        user +
                                        " at " +
                                        formattedTime
                        );
                    } else {
                        String formattedTime = getFormattedTime();
                        writeLog(user + " is accessing the log files at " + formattedTime);
                    }
                    readLog();
                } catch (IOException e) {
                    String formattedTime = getFormattedTime();
                    JOptionPane.showMessageDialog(
                            null,
                            "An error occurred while clearing the logs: " + e.getMessage(),
                            "Error",
                            JOptionPane.ERROR_MESSAGE
                    );
                    writeLog(
                            "An error occurred while clearing the logs at " +
                                    formattedTime +
                                    "Error message: " +
                                    e.getMessage()
                    );
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        showFirstRunPopup();
    }
}
