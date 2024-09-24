import java.util.Scanner;
import java.util.Random;

public class HillCipher {

    public static int[] matrixMultiply(int[][] matrix, int[] vector, int n) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = 0;
            for (int j = 0; j < n; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
            result[i] %= 26;
        }
        return result;
    }

    public static int[][] generateKeyMatrix(int n) {
        Random rand = new Random();
        int[][] keyMatrix;
        int determinant;
        
        do {
            keyMatrix = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    keyMatrix[i][j] = rand.nextInt(26);        }
            }
            determinant = determinant(keyMatrix, n);
        } while (determinant == 0 || gcd(determinant, 26) != 1); 
        return keyMatrix;
    }

    public static int[][] inverseMatrix(int[][] matrix, int n) {
        int[][] inverseMatrix = new int[n][n];
        int determinant = determinant(matrix, n);
        int inverseDeterminant = modInverse(determinant, 26);

        int[][] adjugateMatrix = adjugate(matrix, n);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                inverseMatrix[i][j] = (adjugateMatrix[i][j] * inverseDeterminant) % 26;
                if (inverseMatrix[i][j] < 0) {
                    inverseMatrix[i][j] += 26;
                }
            }
        }

        return inverseMatrix;
    }

    public static int determinant(int[][] matrix, int n) {
        if (n == 1) {
            return matrix[0][0];
        }

        int determinant = 0;
        int sign = 1;

        for (int i = 0; i < n; i++) {
            int[][] subMatrix = new int[n - 1][n - 1];
            for (int j = 1; j < n; j++) {
                int subMatrixColumn = 0;
                for (int k = 0; k < n; k++) {
                    if (k != i) {
                        subMatrix[j - 1][subMatrixColumn++] = matrix[j][k];
                    }
                }
            }
            determinant += sign * matrix[0][i] * determinant(subMatrix, n - 1);
            sign = -sign;
        }

        return determinant % 26;
    }

    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1;
    }

    public static int[][] adjugate(int[][] matrix, int n) {
        int[][] adjugateMatrix = new int[n][n];
        int sign = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int[][] subMatrix = new int[n - 1][n - 1];
                int rowIndex = 0, colIndex = 0;

                for (int k = 0; k < n; k++) {
                    if (k != i) {
                        colIndex = 0;
                        for (int l = 0; l < n; l++) {
                            if (l != j) {
                                subMatrix[rowIndex][colIndex++] = matrix[k][l];
                            }
                        }
                        rowIndex++;
                    }
                }

                adjugateMatrix[j][i] = (sign * determinant(subMatrix, n - 1)) % 26;
                if (adjugateMatrix[j][i] < 0) {
                    adjugateMatrix[j][i] += 26;
                }
                sign = -sign;
            }
        }

        return adjugateMatrix;
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static String encrypt(String text, int[][] keyMatrix, int n) {
        int textLen = text.length();
        int[] textVector = new int[n];
        StringBuilder cipherText = new StringBuilder();

        for (int i = 0; i < textLen; i += n) {
            for (int j = 0; j < n; j++) {
                textVector[j] = text.charAt(i + j) - 'A';
            }

            int[] resultVector = matrixMultiply(keyMatrix, textVector, n);

            for (int j = 0; j < n; j++) {
                cipherText.append((char) (resultVector[j] + 'A'));
            }
        }

        return cipherText.toString();
    }

    public static String decrypt(String cipherText, int[][] keyMatrix, int n) {
        int[][] inverseKeyMatrix = inverseMatrix(keyMatrix, n);
        int textLen = cipherText.length();
        int[] cipherVector = new int[n];
        StringBuilder plainText = new StringBuilder();

        for (int i = 0; i < textLen; i += n) {
            for (int j = 0; j < n; j++) {
                cipherVector[j] = cipherText.charAt(i + j) - 'A';
            }

            int[] resultVector = matrixMultiply(inverseKeyMatrix, cipherVector, n);

            for (int j = 0; j < n; j++) {
                plainText.append((char) (resultVector[j] + 'A'));
            }
        }

        return plainText.toString();
    }

    public static void printMatrix(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the value of n (matrix size): ");
        int n = scanner.nextInt();
        scanner.nextLine(); 

        String text = "HELLOWORLD";
        text = text.replaceAll(" ", "").toUpperCase();

        while (text.length() % n != 0) {
            text += 'X';
        }
        int[][] keyMatrix = generateKeyMatrix(n);
        System.out.println("Key Matrix:");
        printMatrix(keyMatrix, n);
        String cipherText = encrypt(text, keyMatrix, n);
        System.out.println("Cipher Text: " + cipherText);
        int[][] inverseKeyMatrix = inverseMatrix(keyMatrix, n);
        System.out.println("Decryption Key Matrix:");
        printMatrix(inverseKeyMatrix, n);
        String decryptedText = decrypt(cipherText, keyMatrix, n);
        System.out.println("Decrypted Text: " + decryptedText);
        scanner.close();
    }
}
