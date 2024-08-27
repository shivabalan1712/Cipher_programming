import java.util.Scanner;

public class BruteForceAttack {
    
    public static void bruteForceAttack(String encryptedText) {
        StringBuilder result = new StringBuilder();
        
        for (int s = 1; s < 26; s++) {
            result.setLength(0); // Reset the StringBuilder for the next shift
            
            for (int i = 0; i < encryptedText.length(); i++) {
                char ch = encryptedText.charAt(i);
                
                if (Character.isUpperCase(ch)) {
                    ch = (char) ((ch - 65 - s + 26) % 26 + 65);
                } else if (Character.isLowerCase(ch)) {
                    ch = (char) ((ch - 97 - s + 26) % 26 + 97);
                }
                
                result.append(ch);
            }
            
            System.out.println("Shift: " + s + " -> " + result.toString());
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter an Encrypted text: ");
        String encryptedText = sc.nextLine();
        
        bruteForceAttack(encryptedText);
        
        sc.close();
    }
}
