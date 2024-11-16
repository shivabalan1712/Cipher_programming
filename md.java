import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class md {
    public static void main(String[] args) {
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter a string
        System.out.print("Enter a string to hash: ");
        String input = scanner.nextLine();
        
        try {
            // Get an instance of the MD5 MessageDigest
            MessageDigest digest = MessageDigest.getInstance("MD5");
            
            // Perform the hashing operation
            byte[] hashBytes = digest.digest(input.getBytes());
            
            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                hexString.append(String.format("%02x", b));
            }
            
            // Output the result
            System.out.println("MD5 Hash: " + hexString.toString());
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: MD5 algorithm not found.");
            e.printStackTrace();
        } finally {
            // Close the scanner to prevent resource leak
            scanner.close();
        }
    }
}
