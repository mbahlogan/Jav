import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBook {
    private final String filePath;
    private final List<Contact> contacts;

    public AddressBook(String filePath) {
        this.filePath = filePath;
        this.contacts = new ArrayList<>();
        loadContacts();
    }

    public void addContact(Contact contact) throws IOException {
        contacts.add(contact);
        saveContacts();
    }

    public List<Contact> getContacts() {
        return new ArrayList<>(contacts);
    }

    public void resetContacts() throws IOException {
        contacts.clear();
        saveContacts();
    }

    private void loadContacts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    contacts.add(new Contact(parts[0].trim(), parts[1].trim()));
                }
            }
        } catch (IOException e) {
            // File might not exist yet, which is fine
        }
    }

    private void saveContacts() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + ", " + contact.getAddress() + "\n");
            }
        }
    }
} 