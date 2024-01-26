import javax.swing.*;

public class AverageGUI {
    public static void main(String[] args) {
        int sum = 0;
        int count = 0;
        
        while (true) {
            String input = JOptionPane.showInputDialog("Enter an integer (or 'stop' to exit):");
            
            if (input.equalsIgnoreCase("stop")) {
                break;
            }
            
            int number = Integer.parseInt(input);
            sum += number;
            count++;
        }
        
        double average = (double) sum / count;
        
        JOptionPane.showMessageDialog(null, "Average: " + average);
    }
}