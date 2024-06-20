import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class fileHandler {
    private static final String FILE_PATH = "form_data.csv";
    private FileWriter writer;

    public fileHandler() {
        // Check if the file already exists and has content
        if (fileExistsAndNotEmpty(FILE_PATH)) {
            try {
                writer = new FileWriter(FILE_PATH, true);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            try {
                writer = new FileWriter(FILE_PATH, true);
                // Write the form data headers to the CSV file
                writer.write(
                        "DateTime,FirstName,LastName,PhoneNumber,Email,Sex,Water,Meals,Wheat,Sugar,Dairy,Miles,Weight\n");
                writer.flush();
                System.out.println("File created and headers written.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean fileExistsAndNotEmpty(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            return reader.readLine() != null;
        } catch (IOException e) {
            return false;
        }
    }

    public void writeResults(String surveyData) {
        try {
            writer.write(surveyData);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
