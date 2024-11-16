import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input prime number (p) and primitive root (g) from user
        System.out.print("Enter a prime number (q): ");
        BigInteger q = new BigInteger(sc.next());

        System.out.print("Enter a primitive root (alpha) of " + q + ": ");
        BigInteger alpha = new BigInteger(sc.next());

        // Input private key for Person A
        System.out.print("Enter Person A's private key (Xa): ");
        BigInteger privateKeyA = new BigInteger(sc.next());

        // Input private key for Person B
        System.out.print("Enter Person B's private key (Xb): ");
        BigInteger privateKeyB = new BigInteger(sc.next());

        // Calculate Person A's public key: Ya = alpha^Xa mod q
        BigInteger publicKeyA = alpha.modPow(privateKeyA, q);
        System.out.println("Person A's public key (Ya = alpha^Xa mod q): " + publicKeyA);

        // Calculate Person B's public key: Yb = alpha^Xb mod q
        BigInteger publicKeyB = alpha.modPow(privateKeyB, q);
        System.out.println("Person B's public key (Yb = alpha^Xb mod q): " + publicKeyB);

        // Person A computes the shared secret: s = Yb^Xa mod q
        BigInteger sharedSecretA = publicKeyB.modPow(privateKeyA, q);
        System.out.println("Person A's computed shared secret: " + sharedSecretA);

        // Person B computes the shared secret: s = Ya^Xb mod q
        BigInteger sharedSecretB = publicKeyA.modPow(privateKeyB, q);
        System.out.println("Person B's computed shared secret: " + sharedSecretB);

        // Both shared secrets should be the same
        if (sharedSecretA.equals(sharedSecretB)) {
            System.out.println("Key exchange successful! Shared secret: " + sharedSecretA);
        } else {
            System.out.println("Error: Shared secrets do not match!");
        }

        sc.close();
    }
}
