import java.util.Scanner;

public class EulersTheorem {
    
    // Function to calculate the greatest common divisor
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    // Function to calculate Euler's Totient function
    public static int eulerTotient(int n) {
        int result = n; // Initialize result as n
        for (int p = 2; p * p <= n; p++) {
            // Check if p is a prime factor
            if (n % p == 0) {
                // If yes, then it is a prime factor
                while (n % p == 0) {
                    n /= p;
                }
                result -= result / p;
            }
        }
        // If n is a prime number greater than 1
        if (n > 1) {
            result -= result / n;
        }
        return result;
    }

    // Function to calculate (a^b) mod m using modular exponentiation
    public static long modExp(int a, int b, int m) {
        long result = 1;
        a = a % m; // Update a if it is more than or equal to m
        while (b > 0) {
            // If b is odd, multiply a with the result
            if ((b & 1) == 1) {
                result = (result * a) % m;
            }
            // b must be even now
            b >>= 1; // b = b / 2
            a = (a * a) % m; // Change a to a^2
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: integers a and n
        System.out.print("Enter integer a: ");
        int a = scanner.nextInt();
        System.out.print("Enter integer n: ");
        int n = scanner.nextInt();

        // Check if a and n are coprime
        if (gcd(a, n) != 1) {
            System.out.println("a and n are not coprime. Euler's theorem does not apply.");
        } else {
            // Calculate φ(n)
            int phiN = eulerTotient(n);
            System.out.println("φ(" + n + ") = " + phiN);

            // Calculate a^φ(n) mod n
            long result = modExp(a, phiN, n);
            System.out.println("a^φ(n) mod n = " + result);
        }

        scanner.close();
    }
}
