import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class AverageGUI {
    private static List<Integer> numbers;
    private static JTextField inputField;
    private static JButton nextButton;
    private static JButton endButton;
    private static JButton resetButton;

    public static void main(String[] args) {
        numbers = new ArrayList<>();

        JFrame frame = new JFrame("Average Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);
        frame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        nextButton = new JButton("Next");
        endButton = new JButton("End");
        resetButton = new JButton("Reset");

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNumber();
            }
        });

        endButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateAverage();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });

        inputPanel.add(new JLabel("Enter an integer: "));
        inputPanel.add(inputField);
        inputPanel.add(nextButton);
        inputPanel.add(endButton);
        inputPanel.add(resetButton);

        frame.add(inputPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    private static void addNumber() {
        String input = inputField.getText();

        try {
            int number = Integer.parseInt(input);
            numbers.add(number);

            inputField.setText("");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid input! Please enter an integer.");
        }
    }

    private static void calculateAverage() {
        if (numbers.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No numbers entered!");
        } else {
            int sum = 0;

            for (int number : numbers) {
                sum += number;
            }

            double average = (double) sum / numbers.size();

            JOptionPane.showMessageDialog(null, "Average: " + average);
        }
    }

    private static void reset() {
        numbers.clear();
        inputField.setText("");
    }
}