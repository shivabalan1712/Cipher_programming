import java.util.Scanner;

public class ChineseRemainderTheorem {

    public static int gcdExtended(int a, int b, int[] x, int[] y) {
        if (a == 0) {
            x[0] = 0;
            y[0] = 1;
            return b;
        }

        int[] x1 = new int[1]; 
        int[] y1 = new int[1]; 
        int gcd = gcdExtended(b % a, a, x1, y1);

        x[0] = y1[0] - (b / a) * x1[0];
        y[0] = x1[0];

        return gcd;
    }

    public static int modInverse(int a, int m) {
        int[] x = new int[1];
        int[] y = new int[1];
        int g = gcdExtended(a, m, x, y);
        if (g != 1) {
            throw new ArithmeticException("Inverse doesn't exist");
        } else {
            return (x[0] % m + m) % m;
        }
    }

    public static int chineseRemainderTheorem(int[] a, int[] m) {
        int prod = 1;
        for (int i : m) {
            prod *= i;
        }

        int result = 0;
        for (int i = 0; i < a.length; i++) {
            int partialProd = prod / m[i];
            result += a[i] * modInverse(partialProd, m[i]) * partialProd;
        }
        return result % prod;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of equations: ");
        int n = scanner.nextInt();
        int[] a = new int[n]; 
        int[] m = new int[n]; 

        for (int i = 0; i < n; i++) {
            System.out.print("Enter remainder a[" + i + "]: ");
            a[i] = scanner.nextInt();
            System.out.print("Enter modulus m[" + i + "]: ");
            m[i] = scanner.nextInt();
        }

        try {
            int result = chineseRemainderTheorem(a, m);
            System.out.println("The solution is x = " + result);
        } catch (ArithmeticException e) {
            System.out.println(e.getMessage());
        }

        scanner.close();
    }
}