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
        String name = JOptionPane.showInputDialog("Enter name:");
        String address = JOptionPane.showInputDialog("Enter address:");
        
        String contact = name + ", " + address + "\n";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(contact);
            JOptionPane.showMessageDialog(null, "Contact added successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error adding contact: " + e.getMessage());
        }
    }
    
    private static void displayContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder sb = new StringBuilder();
            String line;
            
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
            
            JOptionPane.showMessageDialog(null, sb.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error displaying contacts: " + e.getMessage());
        }
    }
}
