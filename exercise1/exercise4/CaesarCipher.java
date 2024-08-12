package exercise4;

import java.util.Scanner;

public class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                char encryptedChar = (char) ((ch - base + shift) % 26 + base);
                result.append(encryptedChar);
            } else {
                result.append(ch); // Keep non-alphabetic characters unchanged
            }
        }
        return result.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift); // Decrypt by using the reverse shift
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the text to encrypt:");
        String text = scanner.nextLine();

        System.out.println("Enter the shift value:");
        int shift = scanner.nextInt();

        String encryptedText = encrypt(text, shift);
        System.out.println("Encrypted text: " + encryptedText);

        String decryptedText = decrypt(encryptedText, shift);
        System.out.println("Decrypted text: " + decryptedText);
    }
}
