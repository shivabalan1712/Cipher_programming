import java.util.Scanner;

class RowTranspositionCipher {
    private int columns;

    RowTranspositionCipher(int keyLength) {
        this.columns = keyLength;
    }

    
    public String encrypt(String plaintext, String key) {
        int rows = (int) Math.ceil((double) plaintext.length() / columns);

        
        char[][] grid = new char[rows][columns];
        int k = 0;

       
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (k < plaintext.length()) {
                    grid[i][j] = plaintext.charAt(k++);
                } else {
                    grid[i][j] = 'X';
                }
            }
        }

       
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < columns; i++) {
            int col = Character.getNumericValue(key.charAt(i)) - 1;
            
            if (col < 0 || col >= columns) {
                throw new IllegalArgumentException("Invalid key. The key contains out-of-bound indices.");
            }
            for (int j = 0; j < rows; j++) {
                ciphertext.append(grid[j][col]);
            }
        }

        return ciphertext.toString();
    }

   
    public String decrypt(String ciphertext, String key) {
        int rows = (int) Math.ceil((double) ciphertext.length() / columns);

        
        char[][] grid = new char[rows][columns];
        int k = 0;

        
        for (int i = 0; i < columns; i++) {
            int col = Character.getNumericValue(key.charAt(i)) - 1;
           
            if (col < 0 || col >= columns) {
                throw new IllegalArgumentException("Invalid key. The key contains out-of-bound indices.");
            }
            for (int j = 0; j < rows; j++) {
                grid[j][col] = ciphertext.charAt(k++);
            }
        }

       
        StringBuilder plaintext = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                plaintext.append(grid[i][j]);
            }
        }

        
        return plaintext.toString().replaceAll("X+$", "");
    }
}

public class rowtransposition {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

        
        System.out.println("Select an option:");
        System.out.println("1. Encryption");
        System.out.println("2. Decryption");
        int choice = sc.nextInt();
        sc.nextLine(); 

        
        if (choice == 1) {
            System.out.print("Enter the plaintext: ");
            String plaintext = sc.nextLine().replaceAll("\\s", "").toUpperCase();
            
            
            System.out.print("Enter the key: ");
            String key = sc.nextLine();

            
            if (key.length() != key.chars().distinct().count()) {
                System.out.println("The key should have unique digits.");
                return;
            }

            RowTranspositionCipher cipher = new RowTranspositionCipher(key.length());

            
            String encryptedMessage = cipher.encrypt(plaintext, key);
            System.out.println("Encrypted message: " + encryptedMessage);
        } else if (choice == 2) {
            System.out.print("Enter the ciphertext: ");
            String ciphertext = sc.nextLine().replaceAll("\\s", "").toUpperCase();
            
            
            System.out.print("Enter the key: ");
            String key = sc.nextLine();

            
            if (key.length() != key.chars().distinct().count()) {
                System.out.println("The key should have unique digits.");
                return;
            }

            RowTranspositionCipher cipher = new RowTranspositionCipher(key.length());

           
            String decryptedMessage = cipher.decrypt(ciphertext, key);
            System.out.println("Decrypted message: " + decryptedMessage);
        } else {
            System.out.println("Invalid choice. Please select 1 for encryption or 2 for decryption.");
        }
    }
}