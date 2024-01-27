import java.io.*;
import javax.swing.*;

public class App {
    private static final String FILE_PATH = "address_book.txt";

    public static void main(String[] args) {
        while (true) {
            String input = JOptionPane.showInputDialog("Choose an option:\n1. Add contact\n2. Display contacts\n3. Exit");

            if (input == null) {
                break;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    addContact();
                    break;
                case 2:
                    displayContacts();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice!");
            }
        }
    }

    private static void addContact() {
        JPanel panel = new JPanel();
        JTextField nameField = new JTextField(10);
        JTextField addressField = new JTextField(10);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Contact", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            String address = addressField.getText();

            String contact = name + ", " + address + "\n";

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(contact);
                JOptionPane.showMessageDialog(null, "Contact added successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error adding contact: " + e.getMessage());
            }
        }
    }

    private static void displayContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }

            JTextArea textArea = new JTextArea(sb.toString());
            JScrollPane scrollPane = new JScrollPane(textArea);
            textArea.setEditable(false);

            JOptionPane.showMessageDialog(null, scrollPane);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error displaying contacts: " + e.getMessage());
        }
    }
}