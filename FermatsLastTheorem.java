import java.util.Scanner;

public class FermatsLastTheorem {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Get user input for n
        System.out.print("Enter an integer n (greater than 2): ");
        int n = scanner.nextInt();
        if (n <= 2) {
            System.out.println("Please enter a value greater than 2.");
            return;
        }

        // Get user input for limit
        System.out.print("Enter an upper limit for a, b, and c: ");
        int limit = scanner.nextInt();

        boolean foundSolution = false;

        for (int a = 1; a <= limit; a++) {
            int aN = power(a, n); // Calculate a^n once
            for (int b = a; b <= limit; b++) {
                int bN = power(b, n); // Calculate b^n once
                for (int c = b; c <= limit; c++) {
                    int cN = power(c, n); // Calculate c^n once
                    if (aN + bN == cN) {
                        foundSolution = true;
                        System.out.printf("Found solution: %d^%d + %d^%d = %d^%d%n", a, n, b, n, c, n);
                    }
                }
            }
        }

        if (!foundSolution) {
            System.out.printf("No solutions found for n = %d and a, b, c up to %d%n", n, limit);
        }

        scanner.close();
    }

    // Helper method to calculate power
    public static int power(int base, int exp) {
        int result = 1;
        for (int i = 0; i < exp; i++) {
            result *= base;
        }
        return result;
    }
}