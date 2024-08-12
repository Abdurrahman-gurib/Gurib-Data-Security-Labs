package exercise3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class LetterHashAnalysis {

    public static void main(String[] args) {
        // Define the path to your text file
        String filePath = "C:\\Users\\SD233484\\OneDrive - SD Worx\\Desktop\\Gurib-Data-Security-Labs\\exercise3\\samplee.txt"; // Update
                                                                                                                                // this
                                                                                                                                // path
                                                                                                                                // as
                                                                                                                                // needed

        // Debug: Print the file path to verify it's correct
        System.out.println("File path: " + filePath);

        // Initialize the map to count letters
        Map<Character, Integer> letterCount = new HashMap<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            letterCount.put(ch, 0);
            letterCount.put(Character.toUpperCase(ch), 0);
        }

        int totalLetters = 0;

        // Read the file and process letters
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (letterCount.containsKey(ch)) {
                        letterCount.put(ch, letterCount.get(ch) + 1);
                        totalLetters++;
                        String letterHash = calculateHash(String.valueOf(ch));
                        System.out.printf("Character: %c | Hash: %s%n", ch, letterHash);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Display the total number of each letter and the sum of all letters
        System.out.println("\nLetter Counts:");
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int countLower = letterCount.get(ch);
            int countUpper = letterCount.get(Character.toUpperCase(ch));
            System.out.printf("%c: %d%n", ch, countLower);
            System.out.printf("%c: %d%n", Character.toUpperCase(ch), countUpper);
        }

        System.out.println("\nTotal Sum of Letters: " + totalLetters);
    }

    private static String calculateHash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("An error occurred while calculating the hash: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
