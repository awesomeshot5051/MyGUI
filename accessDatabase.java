import java.awt.Component;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

public class accessDatabase {
    private static Connection connection;
    private static Connection connection2;
    private static Connection database;

    public static void main(String[] args) throws SQLException, InterruptedException {
        try {
            connectToDatabase();
        } catch (SQLException e) {
            new windowsTerminalOpener(false, true);
            if (windowsTerminalOpener.isSuccess()) {
                connectToDatabase();
            }
        }

        String input = "";
        while (!input.equalsIgnoreCase("shutdown")) {
            String[] options = { "userdatabase", "budget" };
            String selectedOption = (String) JOptionPane.showInputDialog(null,
                    "Which database", "Database Selection", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            if (selectedOption != null) {
                // Process the selected option here
                if (selectedOption.equals("userdatabase")) {
                    database = connection;
                } else {
                    database = connection2;
                }

                // Show all tables available in the selected database
                showAllTables(database);

                input = JOptionPane.showInputDialog(null, "Input the SQL statement: ", "Input",
                        JOptionPane.INFORMATION_MESSAGE);
                input = input.toUpperCase();

                if (input.equalsIgnoreCase("shutdown")) {
                    try (PreparedStatement statement = connection.prepareStatement(input);
                            PreparedStatement statement2 = connection2.prepareStatement(input)) {
                        statement.execute();
                        statement2.execute();
                        JOptionPane.showMessageDialog(null, "Shutdown Complete", "Shutdown",
                                JOptionPane.INFORMATION_MESSAGE);
                        break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                try (PreparedStatement statement = database.prepareStatement(input)) {
                    ResultSet resultSet = statement.executeQuery();
                    DefaultTableModel tableModel = buildTableModel(resultSet);
                    JTable table = new JTable(tableModel);

                    // Set preferred size for the table
                    table.setPreferredScrollableViewportSize(new Dimension(800, 400));

                    // Adjust column widths
                    adjustColumnWidths(table);

                    JScrollPane scrollPane = new JScrollPane(table);

                    if (tableModel.getRowCount() > 0) {
                        JOptionPane.showMessageDialog(null, scrollPane, "Query Result",
                                JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No results found.", "Query Result",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, "An error occured\n" + e.getMessage(), "Error", 0);
                }
            } else {
                // User canceled the selection
                System.out.println("No database selected.");
            }
        }
    }

    public static void connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/userDatabase";
        String username = "root";
        String password = "";
        String url2 = "jdbc:mysql://localhost:3306/Budget";
        connection = DriverManager.getConnection(url, username, password);
        connection2 = DriverManager.getConnection(url2, username, password);
    }

    // Helper method to convert ResultSet to DefaultTableModel
    public static DefaultTableModel buildTableModel(ResultSet resultSet) throws SQLException {
        java.sql.ResultSetMetaData metaData = resultSet.getMetaData();

        // Get column names
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
            columnNames[columnIndex - 1] = metaData.getColumnLabel(columnIndex);
        }

        // Get data rows
        Object[][] data = new Object[0][columnCount];
        int rowCount = 0;
        while (resultSet.next()) {
            Object[] rowData = new Object[columnCount];
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                rowData[columnIndex - 1] = resultSet.getObject(columnIndex);
            }
            data = insertRow(data, rowCount, rowData);
            rowCount++;
        }

        return new DefaultTableModel(data, columnNames);
    }

    // Helper method to insert a row into a 2D array
    public static Object[][] insertRow(Object[][] array, int index, Object[] newRow) {
        Object[][] result = new Object[array.length + 1][];
        System.arraycopy(array, 0, result, 0, index);
        result[index] = newRow;
        System.arraycopy(array, index, result, index + 1, array.length - index);
        return result;
    }

    // Helper method to adjust column widths based on data
    public static void adjustColumnWidths(JTable table) {
        for (int columnIndex = 0; columnIndex < table.getColumnCount(); columnIndex++) {
            TableColumn column = table.getColumnModel().getColumn(columnIndex);
            int preferredWidth = column.getWidth();
            int maxWidth = table.getWidth() / 5; // Limit the column width to 1/5 of the table width
            for (int rowIndex = 0; rowIndex < table.getRowCount(); rowIndex++) {
                TableCellRenderer cellRenderer = table.getCellRenderer(rowIndex, columnIndex);
                Component comp = table.prepareRenderer(cellRenderer, rowIndex, columnIndex);
                int cellWidth = comp.getPreferredSize().width + table.getIntercellSpacing().width;
                preferredWidth = Math.max(preferredWidth, cellWidth);
                if (preferredWidth >= maxWidth) {
                    preferredWidth = maxWidth;
                    break;
                }
            }
            column.setPreferredWidth(preferredWidth);
        }
    }

    // Method to show all tables available in the selected database
    public static void showAllTables(Connection database) throws SQLException {
        try (PreparedStatement statement = database.prepareStatement("SHOW TABLES")) {
            ResultSet resultSet = statement.executeQuery();

            StringBuilder tableList = new StringBuilder();
            while (resultSet.next()) {
                String tableName = resultSet.getString(1);
                tableList.append(tableName).append("\n");
            }

            if (tableList.length() > 0) {
                JOptionPane.showMessageDialog(null,
                        "Tables available in the selected database:\n" + tableList.toString(),
                        "Tables List", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "No tables found in the selected database.", "Tables List",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
