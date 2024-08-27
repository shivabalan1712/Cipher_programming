import java.util.Scanner;

public class CaesarCipher {
    
    public static StringBuffer cipher(String text, int s, boolean encrypt) {
        StringBuffer result = new StringBuffer();
        s = encrypt ? s : 26 - s; // Adjust the shift for decryption

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            if (Character.isUpperCase(ch)) {
                ch = (char) ((ch + s - 65) % 26 + 65);
            } else if (Character.isLowerCase(ch)) {
                ch = (char) ((ch + s - 97) % 26 + 97);
            }
            result.append(ch);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter a String: ");
        String string = sc.nextLine();

        System.out.println("Enter key (Shift): ");
        int shift = sc.nextInt();

        System.out.println("Do you want to (E)ncrypt or (D)ecrypt? ");
        char choice = sc.next().toUpperCase().charAt(0);
        sc.close();
        
        boolean encrypt = (choice == 'E');
        System.out.println((encrypt ? "Encrypted" : "Decrypted") + " message: " + cipher(string, shift, encrypt));
    }
}
