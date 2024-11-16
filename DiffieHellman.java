import java.math.BigInteger;
import java.util.Scanner;

public class DiffieHellman {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a prime number (q): ");
        BigInteger q = new BigInteger(sc.next());
        System.out.print("Enter a primitive root (alpha) of " + q + ": ");
        BigInteger alpha = new BigInteger(sc.next());
        System.out.print("Enter Person A's private key (Xa): ");
        BigInteger privateKeyA = new BigInteger(sc.next());
        System.out.print("Enter Person B's private key (Xb): ");
        BigInteger privateKeyB = new BigInteger(sc.next());
        BigInteger publicKeyA = alpha.modPow(privateKeyA, q);
        System.out.println("Person A's public key (Ya = alpha^Xa mod q): " + publicKeyA);
        BigInteger publicKeyB = alpha.modPow(privateKeyB, q);
        System.out.println("Person B's public key (Yb = alpha^Xb mod q): " + publicKeyB);
        BigInteger sharedSecretA = publicKeyB.modPow(privateKeyA, q);
        System.out.println("Person A's computed shared secret: " + sharedSecretA);
        BigInteger sharedSecretB = publicKeyA.modPow(privateKeyB, q);
        System.out.println("Person B's computed shared secret: " + sharedSecretB);
        if (sharedSecretA.equals(sharedSecretB)) {
            System.out.println("Key exchange successful! Shared secret: " + sharedSecretA);
        } else {
            System.out.println("Error: Shared secrets do not match!");
        }
        sc.close();
    }
}
