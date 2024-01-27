import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class App extends JFrame {
    private static final String FILE_PATH = "address_book.txt";
    private JTextField nameField;
    private JTextField addressField;
    private JTextArea resultArea;

    public App() {
        setTitle("Address Book");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = createFormPanel();
        JPanel buttonPanel = createButtonPanel();
        resultArea = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        add(formPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JLabel addressLabel = new JLabel("Address:");

        nameField = new JTextField(20);
        addressField = new JTextField(20);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(addressLabel);
        panel.add(addressField);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new FlowLayout());

        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String address = addressField.getText().trim();

                if (!name.isEmpty() && !address.isEmpty()) {
                    String contact = name + ", " + address + "\n";
                    appendContactToFile(contact);
                    clearForm();
                    JOptionPane.showMessageDialog(App.this, "Contact added successfully!");
                } else {
                    JOptionPane.showMessageDialog(App.this, "Please enter both name and address.");
                }
            }
        });

        JButton viewButton = new JButton("View Contacts");
        viewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewContacts();
            }
        });

        JButton resetButton = new JButton("Reset Contacts");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetContacts();
                resultArea.setText("");
                JOptionPane.showMessageDialog(App.this, "Contacts reset successfully!");
            }
        });

        panel.add(submitButton);
        panel.add(viewButton);
        panel.add(resetButton);

        return panel;
    }

    private void appendContactToFile(String contact) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(contact);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(App.this, "Error adding contact: " + e.getMessage());
        }
    }

    private void viewContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            StringBuilder contacts = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                contacts.append(line).append("\n");
            }
            resultArea.setText(contacts.toString());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(App.this, "Error viewing contacts: " + e.getMessage());
        }
    }

    private void resetContacts() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(App.this, "Error resetting contacts: " + e.getMessage());
        }
    }

    private void clearForm() {
        nameField.setText("");
        addressField.setText("");
        nameField.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new App();
            }
        });
    }
}