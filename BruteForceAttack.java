import java.util.*;

public class BruteForceAttack{
    public static StringBuffer decrypt(String text, int s){
        StringBuffer result = new StringBuffer();
        for(int i=0;i<text.length();i++){
            if(Character.isUpperCase(text.charAt(i))){
                char ch = (char)(((((int)text.charAt(i) - 65) - s + 26) % 26) + 65);
                result.append(ch);
            }
            else{
                char ch = (char)(((((int)text.charAt(i) - 97) - s + 26) % 26) + 97);
                result.append(ch);
            }
        }
        return result;
    }

    public static void BruteForceAttack(String encryptedtext){
        for(int s=1;s<26;s++){
            StringBuffer decryptedtext = decrypt(encryptedtext,s);
            System.out.println("Shift: "+s+" -> "+decryptedtext);
        }
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a Encrypted text: ");
        String encryptedtext = sc.nextLine();
        BruteForceAttack(encryptedtext);
        sc.close();
    
    }
}