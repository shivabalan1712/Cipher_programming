import java.util.Scanner;

public class EulersTheorem {
    
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int eulerTotient(int n) {
        int result = n; 
        for (int p = 2; p * p <= n; p++) {
           
            if (n % p == 0) {
                while (n % p == 0) {
                    n /= p;
                }
                result -= result / p;
            }
        }
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    public static long modExp(int a, int b, int m) {
        long result = 1;
        a = a % m; 
        while (b > 0) {
            if ((b & 1) == 1) {
                result = (result * a) % m;
            }
            b >>= 1; 
            a = (a * a) % m; 
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter integer a: ");
        int a = scanner.nextInt();
        System.out.print("Enter integer n: ");
        int n = scanner.nextInt();

        if (gcd(a, n) != 1) {
            System.out.println("a and n are not coprime. Euler's theorem does not apply.");
        } else {
            int phiN = eulerTotient(n);
            System.out.println("φ(" + n + ") = " + phiN);

            long result = modExp(a, phiN, n);
            System.out.println("a^φ(n) mod n = " + result);
        }

        scanner.close();
    }
}
