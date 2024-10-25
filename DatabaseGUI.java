import com.mysql.cj.log.Log;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.List;
import java.util.Stack;
import java.util.*;

public class DatabaseGUI {
    private static String serverPassword;
    private static String passwordFilePath;
    private static Connection connection;
    private static Connection connection2;
    private static String databaseName;
    private static JFrame welcomeWindow;
    public static void main(String[] args) {
        // Load file paths from "file_paths.txt"
        try {
            loadFilePaths();

            // Read the password from the password file
            serverPassword = new String(Files.readAllBytes(Paths.get(passwordFilePath)));
            System.out.println("Password retrieved successfully.");

            // Connect to databases
            connectToDatabases(serverPassword);
            chooseDatabase();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
public static void setWelcomeWindow(JFrame frame){
        welcomeWindow=frame;
}

    private static void useDatabase(String dbName) {
        String useCommand = "USE " + dbName;
        try (Statement statement = connection.createStatement()) {
            statement.execute(useCommand);
            System.out.println("Database changed to: " + dbName);
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors here
        }
    }

    public static void chooseDatabase() {
        try {
            // Query the server for available databases
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW DATABASES");

            // Create a frame to display the database buttons
            JFrame frame = new JFrame("Choose Database");
            frame.setLocationRelativeTo(null);
            frame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    frame.dispose();
                    welcomeWindow.setVisible(true);
                }
            });
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLayout(new FlowLayout()); // Use FlowLayout for button alignment

            // Iterate through the result set and create a button for each database
            while (resultSet.next()) {
                String dbName = resultSet.getString(1);
                JButton dbButton = getjButton(dbName, frame);
                frame.add(dbButton); // Add the button to the frame
            }
            JButton exitButton = new JButton("Exit");
            exitButton.addActionListener(e->{
                frame.dispose();
                welcomeWindow.setVisible(true);
//                System.exit(0);
            });
            frame.add(exitButton);
            // Set the frame size and make it visible
            frame.setSize(400, 200);
            frame.setVisible(true);

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any SQL errors here
        }
    }

    private static JButton getjButton(String dbName, JFrame frame) {
        JButton dbButton = new JButton(dbName);
        dbButton.addActionListener(e -> {
            databaseName = dbName; // Set the global variable to the selected database name
            useDatabase(dbName);
            // Show GUI
            frame.dispose();
            try {
                databaseInteraction();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        return dbButton;
    }

    private static void loadFilePaths() throws IOException {
        Properties properties = new Properties();
        try (BufferedReader reader = new BufferedReader(new FileReader("file_paths.txt"))) {
            properties.load(reader);
            passwordFilePath = properties.getProperty("Password").replace("file: ", "");
        }
    }

    public static void connectToDatabases(String serverPassword) throws IOException {
        try {
            String url = "jdbc:mysql://localhost:3306/userDatabase";
            String url2 = "jdbc:mysql://localhost:3306/Budget";
            connection = DriverManager.getConnection(url, "root", serverPassword);
            connection2 = DriverManager.getConnection(url2, "root", serverPassword);
        } catch (SQLException e) {
            errorLog.writeErrorLog(e);
        }
    }

    private static void databaseInteraction() throws SQLException {
        // Create frame
        JFrame frame = new JFrame("Command Executor for Database " + databaseName);
        frame.setLocationRelativeTo(null);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.dispose();
                welcomeWindow.setVisible(true);
            }
        });
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 200);

        // Create text field
        JTextField commandField = new JTextField();

        // Stack to store command history
        Stack<String> commandHistory = new Stack<>();

        // Create buttons
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            frame.dispose();
            chooseDatabase();
        });

        JButton executeButton = new JButton("Execute Command");
        executeButton.addActionListener(e -> {
            String command = commandField.getText();
            executeCommand(command, commandHistory, commandField);
            commandField.setText(""); // Clear the text field
        });
        commandField.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent ke) {
                if (ke.getKeyCode() == KeyEvent.VK_UP) {
                    String previousCommand = commandHistoryManager.getPreviousCommand();
                    if (previousCommand != null) {
                        commandField.setText(previousCommand);
                    }
                } else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
                    String nextCommand = commandHistoryManager.getNextCommand();
                    commandField.setText(Objects.requireNonNullElse(nextCommand, ""));
                }
            }
        });
        InputMap inputMap = commandField.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = commandField.getActionMap();

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK), "executeCommand");
        actionMap.put("executeCommand", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String command = commandField.getText();
                if(getCommandType(command).equalsIgnoreCase("use")){
                    String[] words = command.split("\\s+"); // Split the command by spaces
                    databaseName = words[1]; // Compare the first word with the keyword
                    databaseName=databaseName.replace(";","");
                    frame.setTitle("Command Executor for Database " + databaseName); //
                }
                executeCommand(command, commandHistory, commandField);
                commandField.setText(""); // Clear the text field
            }
        });

        // Create a panel to hold buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(executeButton);
        buttonPanel.add(exitButton);

        // Add components to frame
        frame.getContentPane().add(commandField, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Display the window
        frame.setVisible(true);
    }



    private static void executeCommand(String command, Stack<String> commandHistory, JTextField commandField) {
        commandHistoryManager.addCommand(command); // Store the command in history
        String commandType = getCommandType(command);
        System.out.println("Executing " + commandType + " command now");
        execute(command, commandType);
        commandField.setText(""); // Clear the text field
    }

    private static String getCommandType(String command) {
        if (beginsWith(command, "use")) {
            String[] words = command.split("\\s+"); // Split the command by spaces
            databaseName = words[1]; // Compare the first word with the keyword
            return "use";
        } else if (beginsWith(command, "update")) {
            return "update";
        } else if (beginsWith(command, "insert")) {
            return "insert";
        } else if (beginsWith(command, "drop")) {
            return "drop";
        } else if (beginsWith(command, "select")) {
            return "select";
        } else if(beginsWith(command,"show")) {
            return "show";
        }else {
            return "unknown";
        }
    }

    private static boolean beginsWith(String command, String keyword) {
        String[] words = command.split("\\s+"); // Split the command by spaces
        return words[0].equalsIgnoreCase(keyword); // Compare the first word with the keyword
    }

    private static void execute(String command, String commandType) {
        try (Statement statement = connection.createStatement()) {
            boolean isResultSet = statement.execute(command);
            if (isResultSet) {
                ResultSet resultSet = statement.getResultSet();
                // Use the ResultSet to populate a JTable
                JTable table = new JTable(buildTableModel(resultSet));
                // Show it in a scrollpane, inside a dialog
                JOptionPane.showMessageDialog(null, new JScrollPane(table));
            } else {
                int updateCount = statement.executeUpdate(command);
                System.out.println("Update count: " + updateCount);
            }
        } catch (SQLException throwables) {
            JOptionPane.showInternalMessageDialog(null, "Error Code: " + throwables.getCause(), "Error", JOptionPane.ERROR_MESSAGE);
//            throwables.printStackTrace();
        }
    }

    public static DefaultTableModel buildTableModel(ResultSet rs) throws SQLException {
        ResultSetMetaData metaData = rs.getMetaData();

        // Names of columns
        Vector<String> columnNames = new Vector<String>();
        int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; column++) {
            columnNames.add(metaData.getColumnName(column));
        }

        // Data of the table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        while (rs.next()) {
            Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                if (metaData.getColumnName(columnIndex).equalsIgnoreCase("password") ||
                        metaData.getColumnName(columnIndex).equalsIgnoreCase("salt")) {
                    vector.add("***"); // Replace with asterisks
                } else {
                    vector.add(rs.getObject(columnIndex));
                }
            }
            data.add(vector);
        }

        return new DefaultTableModel(data, columnNames);
    }

    class commandHistoryManager {
        private static List<String> commandHistory = new ArrayList<>();
        private static int currentCommandIndex = -1;

        public static void addCommand(String command) {
            commandHistory.add(command);
            currentCommandIndex = commandHistory.size(); // Reset the index to the end
        }

        public static String getPreviousCommand() {
            if (currentCommandIndex > 0) {
                currentCommandIndex--;
                return commandHistory.get(currentCommandIndex);
            }
            return null; // No previous command
        }

        public static String getNextCommand() {
            if (currentCommandIndex < commandHistory.size() - 1) {
                currentCommandIndex++;
                return commandHistory.get(currentCommandIndex);
            }
            return null; // No next command
        }
    }
}

