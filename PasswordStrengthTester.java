public class PasswordStrengthTester {
    private static int requriredLength = 8;

    // Method to test the strength of a password
    public static boolean setRequiredLength(int length) {
        if (length < requriredLength) {
            return false;
        }
        requriredLength = length;
        if (requriredLength == length) {
            return true;
        }
        return false;
    }

    public static boolean isStrongPassword(String passwordHash) {
        // Minimum length requirement
        if (passwordHash.length() < requriredLength) {
            return false;
        }

        // Check for uppercase characters
        boolean hasUppercase = !passwordHash.equals(passwordHash.toLowerCase());

        // Check for lowercase characters
        boolean hasLowercase = !passwordHash.equals(passwordHash.toUpperCase());

        // Check for digits
        boolean hasDigit = passwordHash.matches(".*\\d.*");

        // Check for special characters
        boolean hasSpecialCharacter = passwordHash.matches(".*[^a-zA-Z0-9].*");

        // Password is strong if it meets all the criteria
        return hasUppercase && hasLowercase && hasDigit && hasSpecialCharacter;
    }

    public static void main(String[] args) {
        // Example usage!
        String passwordHash = "S@rdine85501!"; // Replace this with the actual password hash
        boolean isStrong = isStrongPassword(passwordHash);
        System.out.println("Is the password strong? " + isStrong);
    }
}
