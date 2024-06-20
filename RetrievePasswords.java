import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RetrievePasswords {
    private static List<String> passwords = new ArrayList<>();

    public static List<String> retrievePasswords(String name, Connection connection) {
        String password = "";
        try (PreparedStatement statement = connection
                .prepareStatement("SELECT password FROM passwordHistory WHERE user='" + name + "'")) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                password = resultSet.getString("password");
                passwords.add(password);
            }

        } catch (SQLException e) {
            System.out.println("Connection failed. Please make sure you've established the connection");
        }
        return passwords;
    }

    public static void setNewPassword(String name, Connection connection, String newPassword) {

    }

}
