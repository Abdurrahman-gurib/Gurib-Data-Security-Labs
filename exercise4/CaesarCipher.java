package exercise4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CaesarCipher {

    public static void main(String[] args) {
        // Define the file path
        String filePath = "C:\\Users\\SD233484\\OneDrive - SD Worx\\Desktop\\Gurib-Data-Security-Labs\\exercise4\\sampletext.txt";
        int key = 3; // Caesar Cipher key
        StringBuilder shiftedText = new StringBuilder();

        // Read the file and apply the Caesar Cipher shift
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            int ch;
            while ((ch = reader.read()) != -1) {
                if (Character.isLetter(ch)) {
                    char shiftedChar = shiftLetter((char) ch, key);
                    shiftedText.append(shiftedChar);
                } else {
                    shiftedText.append((char) ch);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        // Write the shifted text back to the same file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(shiftedText.toString());
        } catch (IOException e) {
            System.out.println("Error writing to the file: " + e.getMessage());
        }

        System.out.println("File has been successfully encrypted with a Caesar Cipher shift of 3.");
    }

    // Method to shift a letter by the key
    private static char shiftLetter(char ch, int key) {
        if (Character.isUpperCase(ch)) {
            return (char) (((ch - 'A' + key) % 26) + 'A');
        } else {
            return (char) (((ch - 'a' + key) % 26) + 'a');
        }
    }
}
