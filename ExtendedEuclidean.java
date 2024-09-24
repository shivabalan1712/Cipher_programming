import java.util.Scanner;
public class ExtendedEuclidean {   
    public static int[] extendedGCD(int a, int b) {      
        if (b == 0) {
            return new int[] { a, 1, 0 }; 
        } else {           
            int[] result = extendedGCD(b, a % b);
            int gcd = result[0];
            int x1 = result[1];
            int y1 = result[2];         
            int x = y1;
            int y = x1 - (a / b) * y1;            
            return new int[] { gcd, x, y }; 
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);       
        System.out.print("Enter the first integer (a): ");
        int a = scanner.nextInt();
        System.out.print("Enter the second integer (b): ");
        int b = scanner.nextInt();       
        int[] result = extendedGCD(a, b);
        int gcd = result[0];
        int x = result[1];
        int y = result[2];        
        System.out.println("The GCD of " + a + " and " + b + " is: " + gcd);
        System.out.println("Coefficients x and y such that a * x + b * y = GCD(a, b) are: ");
        System.out.println("x = " + x);
        System.out.println("y = " + y);       
        scanner.close();
    }
}
