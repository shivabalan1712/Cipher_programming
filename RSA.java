import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return a;
        }
        return gcd(b, a.mod(b));
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the first prime number (p): ");
        BigInteger p = new BigInteger(sc.next());
        System.out.print("Enter the second prime number (q): ");
        BigInteger q = new BigInteger(sc.next());
        // Calculate n = p * q
        BigInteger n = p.multiply(q);
        // Calculate phi = (p-1) * (q-1)
        BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        // Input the value of e from the user
        BigInteger e;
        while (true) {
            System.out.print("Enter the value of e (1 < e < " + phi + " and gcd(e, phi) = 1): ");
            e = new BigInteger(sc.next());
            // Check if e is valid
            if (e.compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0 && gcd(e, phi).equals(BigInteger.ONE)) {
                break;
            } else {
                System.out.println("Invalid value for e. Please try again.");
            }
        }
        // Calculate d, the modular inverse of e mod phi
        BigInteger d = e.modInverse(phi);
        System.out.println("Public Key: (e, n) = (" + e + ", " + n + ")");
        System.out.println("Private Key: (d, n) = (" + d + ", " + n + ")");
        System.out.print("Enter the message (as a number) to be encrypted: ");
        BigInteger message = new BigInteger(sc.next());
        // Encrypt the message: cipher = message^e mod n
        BigInteger cipher = message.modPow(e, n);
        System.out.println("Encrypted Message: " + cipher);
        // Decrypt the message: decrypted = cipher^d mod n
        BigInteger decrypted = cipher.modPow(d, n);
        System.out.println("Decrypted Message: " + decrypted);
        sc.close();
    }
}