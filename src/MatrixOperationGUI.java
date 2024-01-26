import javax.swing.JOptionPane;

public class MatrixOperationGUI {
    public static void main(String[] args) {
        int[][] matrix1 = { {1, 2}, {3, 4} };
        int[][] matrix2 = { {5, 6}, {7, 8} };
        
        int[][] sum = matrixAddition(matrix1, matrix2);
        int[][] difference = matrixSubtraction(matrix1, matrix2);
        int[][] product = matrixMultiplication(matrix1, matrix2);
        
        displayMatrix("Matrix 1:", matrix1);
        displayMatrix("Matrix 2:", matrix2);
        displayMatrix("Sum:", sum);
        displayMatrix("Difference:", difference);
        displayMatrix("Product:", product);
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
    
    public static void displayMatrix(String title, int[][] matrix) {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append('\n');
        
        for (int[] row : matrix) {
            for (int element : row) {
                sb.append(element).append(' ');
            }
            sb.append('\n');
        }
        
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}
