import java.util.Scanner;
public class Euclidean{   
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);        
        System.out.print("Enter the first integer: ");
        int num1 = scanner.nextInt();
        System.out.print("Enter the second integer: ");
        int num2 = scanner.nextInt();       
        int result = gcd(num1, num2);       
        System.out.println("The GCD of " + num1 + " and " + num2 + " is: " + result);
        scanner.close();
    }
}