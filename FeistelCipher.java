import java.util.Scanner;

public class FeistelCipher {

    public static int feistelFunction(int halfBlock, int key) {
        return halfBlock ^ key;  
    }

    public static String encrypt(String plainText, int key) {
        int L = plainText.charAt(0);  
        int R = plainText.charAt(1);  

        int newL = R;
        int newR = L ^ feistelFunction(R, key);

        return "" + (char)newL + (char)newR;
    }

    public static String decrypt(String cipherText, int key) {
        int L = cipherText.charAt(0);  
        int R = cipherText.charAt(1);  

        int newR = L;
        int newL = R ^ feistelFunction(L, key);

        return "" + (char)newL + (char)newR;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a two-character plain text: ");
        String plainText = scanner.nextLine();

        int key = 5;

        String cipherText = encrypt(plainText, key);
        System.out.println("Encrypted Text: " + cipherText);

        String decryptedText = decrypt(cipherText, key);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
