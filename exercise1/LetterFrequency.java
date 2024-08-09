import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LetterFrequency {

    public static void main(String[] args) {
        String filePath = "C:\\Users\\SD233484\\OneDrive - SD Worx\\Desktop\\sample.txt"; // Path to your .txt file
        Map<Character, Integer> letterCount = new HashMap<>();
        int totalLetters = 0;

        // Initialize map with alphabets
        for (char ch = 'a'; ch <= 'z'; ch++) {
            letterCount.put(ch, 0);
        }

        // Read file and count letters
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Reading line: " + line); // Debugging output
                line = line.toLowerCase(); // Convert to lowercase for uniformity
                for (char ch : line.toCharArray()) {
                    if (Character.isLetter(ch)) {
                        if (letterCount.containsKey(ch)) {
                            letterCount.put(ch, letterCount.get(ch) + 1);
                        } else {
                            System.out.println("Unexpected character: " + ch); // Debugging unexpected characters
                        }
                        totalLetters++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Ensure that letters were actually counted
        if (totalLetters == 0) {
            System.out.println("No letters were found in the file.");
            return;
        }

        // Calculate and display the statistical distribution
        System.out.println("Letter Frequency Analysis:");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int count = letterCount.get(ch);
            double percentage = (count * 100.0) / totalLetters;
            System.out.printf("%c: %d (%.2f%%)%n", ch, count, percentage);
        }

        System.out.println("Total Letters Counted: " + totalLetters);
    }
}
