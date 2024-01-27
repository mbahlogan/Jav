import javax.swing.JOptionPane;

public class MatrixOperationGUI {
    public static void main(String[] args) {
        String input1 = JOptionPane.showInputDialog(null, "Enter values for Matrix 1 (separated by spaces):\n" +
                "Enter the number of rows and columns followed by the matrix elements:");

        String[] input1Parts = input1.split(" ");
        int rows1 = Integer.parseInt(input1Parts[0]);
        int columns1 = Integer.parseInt(input1Parts[1]);

        int[][] matrix1 = new int[rows1][columns1];
        int index = 2;

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns1; j++) {
                matrix1[i][j] = Integer.parseInt(input1Parts[index]);
                index++;
            }
        }

        String input2 = JOptionPane.showInputDialog(null, "Enter values for Matrix 2 (separated by spaces):\n" +
                "Enter the number of rows and columns followed by the matrix elements:");

        String[] input2Parts = input2.split(" ");
        int rows2 = Integer.parseInt(input2Parts[0]);
        int columns2 = Integer.parseInt(input2Parts[1]);

        int[][] matrix2 = new int[rows2][columns2];
        index = 2;

        for (int i = 0; i < rows2; i++) {
            for (int j = 0; j < columns2; j++) {
                matrix2[i][j] = Integer.parseInt(input2Parts[index]);
                index++;
            }
        }

        int[][] sum = matrixAddition(matrix1, matrix2);
        int[][] difference = matrixSubtraction(matrix1, matrix2);
        int[][] product = matrixMultiplication(matrix1, matrix2);

        StringBuilder sb = new StringBuilder();
        sb.append("Matrix 1:\n");
        sb.append(matrixToString(matrix1)).append("\n");
        sb.append("Matrix 2:\n");
        sb.append(matrixToString(matrix2)).append("\n");
        sb.append("Sum:\n");
        sb.append(matrixToString(sum)).append("\n");
        sb.append("Difference:\n");
        sb.append(matrixToString(difference)).append("\n");
        sb.append("Product:\n");
        sb.append(matrixToString(product)).append("\n");

        JOptionPane.showMessageDialog(null, sb.toString(), "Matrix Operations", JOptionPane.PLAIN_MESSAGE);
    }

    public static int[][] matrixAddition(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int columns = matrix1[0].length;

        int[][] result = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }

        return result;
    }

    public static int[][] matrixSubtraction(int[][] matrix1, int[][] matrix2) {
        int rows = matrix1.length;
        int columns = matrix1[0].length;

        int[][] result = new int[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                result[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }

        return result;
    }

    public static int[][] matrixMultiplication(int[][] matrix1, int[][] matrix2) {
        int rows1 = matrix1.length;
        int columns1 = matrix1[0].length;
        int columns2 = matrix2[0].length;

        int[][] result = new int[rows1][columns2];

        for (int i = 0; i < rows1; i++) {
            for (int j = 0; j < columns2; j++) {
                for (int k = 0; k < columns1; k++) {
                    result[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }

        return result;
    }

    public static String matrixToString(int[][] matrix) {
        StringBuilder sb = new StringBuilder();

        for (int[] row : matrix) {
            for (int element : row) {
                sb.append(element).append(' ');
            }
            sb.append('\n');
        }

        return sb.toString();
    }
}