import java.util.Scanner;

public class FermatsTheorem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input for n
        System.out.print("Enter an integer n (greater than or equal to 2): ");
        int n = scanner.nextInt();
        if (n < 2) {
            System.out.println("Please enter a value of n greater than or equal to 2.");
            return;
        }

        // Input for the range limit
        System.out.print("Enter an upper limit for a, b, and c: ");
        int limit = scanner.nextInt();

        boolean foundSolution = false;

        // Checking Fermat's Theorem
        for (int a = 1; a <= limit; a++) {
            int aN = power(a, n);
            for (int b = a; b <= limit; b++) {
                int bN = power(b, n);
                for (int c = b; c <= limit; c++) {
                    int cN = power(c, n);
                    if (aN + bN == cN) {
                        foundSolution = true;
                        System.out.printf("Found solution: %d^%d + %d^%d = %d^%d%n", a, n, b, n, c, n);
                    }
                }
            }
        }

        if (foundSolution) {
            System.out.printf("Solutions found for n = %d within the limit %d.%n", n, limit);
        } else {
            if (n > 2) {
                System.out.printf("Fermat's Last Theorem holds: No solutions found for n = %d and limit = %d.%n", n, limit);
            } else {
                System.out.printf("No Pythagorean triples found up to the limit %d.%n", limit);
            }
        }

        scanner.close();
    }

    // Utility to calculate a number raised to a power
    public static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }
}
