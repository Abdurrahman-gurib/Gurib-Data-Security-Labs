package exercise5;

import java.math.BigInteger;
import java.util.Scanner;

public class RSAEncryption {

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        if (num <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    // Method to calculate the greatest common divisor
    public static int gcd(int a, int b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }

    // Method to find the modular inverse
    public static int modInverse(int e, int phi_n) {
        for (int d = 1; d < phi_n; d++) {
            if ((e * d) % phi_n == 1) {
                return d;
            }
        }
        return -1; // No valid d found
    }

    // Method to encrypt a message
    public static BigInteger encrypt(int message, BigInteger e, BigInteger n) {
        BigInteger m = BigInteger.valueOf(message);
        return m.modPow(e, n);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input two prime numbers
        System.out.print("Enter the first prime number (p): ");
        int p = scanner.nextInt();
        while (!isPrime(p)) {
            System.out.print("Invalid input. Enter a prime number (p): ");
            p = scanner.nextInt();
        }

        System.out.print("Enter the second prime number (q): ");
        int q = scanner.nextInt();
        while (!isPrime(q)) {
            System.out.print("Invalid input. Enter a prime number (q): ");
            q = scanner.nextInt();
        }

        // Calculate n and phi_n
        int n = p * q;
        int phi_n = (p - 1) * (q - 1);
        System.out.println("n = " + n);
        System.out.println("phi_n = " + phi_n);

        // Input e such that gcd(e, phi_n) = 1
        System.out.print("Enter a value for e such that 1 < e < phi_n and gcd(e, phi_n) = 1: ");
        int e = scanner.nextInt();
        while (e <= 1 || e >= phi_n || gcd(e, phi_n) != 1) {
            System.out.print("Invalid input. Enter a value for e such that gcd(e, phi_n) = 1: ");
            e = scanner.nextInt();
        }

        // Calculate d such that (e * d) % phi_n = 1
        int d = modInverse(e, phi_n);
        if (d == -1) {
            System.out.println("Could not find a valid d for the given e.");
            scanner.close();
            return;
        }
        System.out.println("d = " + d);

        // Input the string to be encrypted
        System.out.print("Enter the string to encrypt: ");
        scanner.nextLine(); // Consume the newline character
        String message = scanner.nextLine();

        // Encrypt the message
        BigInteger bigE = BigInteger.valueOf(e);
        BigInteger bigN = BigInteger.valueOf(n);
        System.out.print("Encrypted message: ");
        for (char ch : message.toCharArray()) {
            BigInteger cipher = encrypt((int) ch, bigE, bigN);
            System.out.print(cipher + " ");
        }
        System.out.println();

        scanner.close();
    }
}
