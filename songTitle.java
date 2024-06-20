import java.util.Scanner;

public class songTitle {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String stop = "stop"; // Add this line to define the stop keyword
        String originalString;

        do {
            originalString = in.nextLine();
            if (originalString.equals(stop)) {
                break; // Exit the loop if the input is "stop"
            }

            String modifiedString = originalString.replaceAll("_", " ");

            StringBuilder result = new StringBuilder();
            String[] words = modifiedString.split(" ");
            for (String word : words) {
                if (!result.toString().isEmpty()) {
                    result.append(" ");
                }
                result.append(word.substring(0, 1).toUpperCase()).append(word.substring(1));
            }

            System.out.println(result.toString());
        } while (true);

        in.close();
    }
}