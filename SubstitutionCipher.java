import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SubstitutionCipher {
    
    private static final char[] substitutionArray = {'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'A', 'B', 'C'};
    private static final Map<Character, Character> cipherMap = new HashMap<>();
    
    static {
        initializeMap();
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter a string for encryption: ");
        String message = sc.nextLine();
        System.out.println("Encrypted message: " + processMessage(message, true));
        
        System.out.println("Enter a string to decrypt: ");
        String encryptedMessage = sc.nextLine();
        System.out.println("Decrypted message: " + processMessage(encryptedMessage, false));
        
        sc.close();
    }

    public static String processMessage(String message, boolean encrypt) {
        StringBuilder result = new StringBuilder();
        message = message.toUpperCase();

        for (char ch : message.toCharArray()) {
            if (cipherMap.containsKey(ch)) {
                result.append(encrypt ? cipherMap.get(ch) : getKeyByValue(cipherMap, ch));
            } else {
                result.append(ch);  
            }
        }

        return result.toString();
    }

    private static void initializeMap() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < alphabet.length(); i++) {
            cipherMap.put(alphabet.charAt(i), substitutionArray[i]);
        }
    }

    private static char getKeyByValue(Map<Character, Character> map, char value) {
        for (Map.Entry<Character, Character> entry : map.entrySet()) {
            if (entry.getValue() == value) {
                return entry.getKey();
            }
        }
        return value;  
    }
}
