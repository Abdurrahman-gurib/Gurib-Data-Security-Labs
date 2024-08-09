package exercise2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class FileAnalysis {

    public static void main(String[] args) {
        // Define the path to your text file
        String filePath = "C:\\Users\\SD233484\\OneDrive - SD Worx\\Desktop\\sample.txt";

        // Initialize the map to count letters
        Map<Character, Integer> letterCount = new HashMap<>();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            letterCount.put(ch, 0);
            letterCount.put(Character.toUpperCase(ch), 0);
        }

        // Read the file and count letters
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (char ch : line.toCharArray()) {
                    if (letterCount.containsKey(ch)) {
                        letterCount.put(ch, letterCount.get(ch) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        // Calculate hash of the file
        String fileHash = calculateFileHash(filePath);
        System.out.println("File Hash: " + fileHash);

        // Generate the letter count string
        StringBuilder letterCountString = new StringBuilder();
        for (char ch = 'a'; ch <= 'z'; ch++) {
            letterCountString.append(Character.toUpperCase(ch))
                    .append('-')
                    .append(letterCount.get(ch))
                    .append('-')
                    .append(Character.toUpperCase(ch))
                    .append('-')
                    .append(letterCount.get(Character.toUpperCase(ch)))
                    .append('-');
        }
        // Remove the trailing hyphen
        if (letterCountString.length() > 0) {
            letterCountString.setLength(letterCountString.length() - 1);
        }

        System.out.println("Letter Count String: " + letterCountString.toString());
    }

    private static String calculateFileHash(String filePath) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(fileBytes);
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            System.out.println("An error occurred while calculating the file hash: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }
}
