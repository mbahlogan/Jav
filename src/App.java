import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class App extends JFrame {
    private static final String FILE_PATH = "address_book.txt";
    private final AddressBook addressBook;
    private final JTextField nameField;
    private final JTextField addressField;
    private final JTextArea resultArea;

    public App() {
        this.addressBook = new AddressBook(FILE_PATH);
        
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        nameField = new JTextField(20);
        addressField = new JTextField(20);
        resultArea = new JTextArea(10, 30);
        
        setupUI();
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupUI() {
        add(createFormPanel(), BorderLayout.NORTH);
        add(createButtonPanel(), BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> submitContact());

        JButton viewButton = new JButton("View Contacts");
        viewButton.addActionListener(e -> viewContacts());

        JButton resetButton = new JButton("Reset Contacts");
        resetButton.addActionListener(e -> resetContacts());

        panel.add(submitButton);
        panel.add(viewButton);
        panel.add(resetButton);

        return panel;
    }

    private void submitContact() {
        String name = nameField.getText().trim();
        String address = addressField.getText().trim();

        if (name.isEmpty() || address.isEmpty()) {
            showError("Please enter both name and address.");
            return;
        }

        try {
            addressBook.addContact(new Contact(name, address));
            clearForm();
            showMessage("Contact added successfully!");
        } catch (IOException e) {
            showError("Error adding contact: " + e.getMessage());
        }
    }

    private void viewContacts() {
        StringBuilder contacts = new StringBuilder();
        addressBook.getContacts().forEach(contact -> 
            contacts.append(contact.toString()).append("\n"));
        resultArea.setText(contacts.toString());
    }

    private void resetContacts() {
        try {
            addressBook.resetContacts();
            resultArea.setText("");
            showMessage("Contacts reset successfully!");
        } catch (IOException e) {
            showError("Error resetting contacts: " + e.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        addressField.setText("");
        nameField.requestFocus();
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::new);
    }
}