import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connectToDatabases {
    public static boolean connectToDatabase() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/userDatabase";
        String username = "root";
        String password = "";
        String url2 = "jdbc:mysql://localhost:3306/Budget";
        Connection connection = DriverManager.getConnection(url, username, password);
        Connection connection2 = DriverManager.getConnection(url2, username, password);
        return true;
    }

    public static void main(String[] args) {
        try {
            if (connectToDatabase()) {
                System.out.println("It worked!");
            } else {
                System.out.println("It didn't work");
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}