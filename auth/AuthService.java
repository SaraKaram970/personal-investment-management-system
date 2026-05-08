package auth;
import java.io.*;

// AuthService handles user sign-up and login functionality using a Singleton pattern.
public class AuthService {

    // Singleton instance of AuthService
    private static AuthService instance;

    // Constant for the user file path
    private static final String FILE_PATH = "users.txt";

    // Private constructor to prevent instantiation from other classes
    private AuthService() {}

    /**
     * Returns the singleton instance of AuthService.
     * Creates the instance if it doesn't exist.
     *
     * @return AuthService singleton instance
     */
    public static AuthService getInstance() {
        if (instance == null) {
            instance = new AuthService();
        }
        return instance;
    }

    /**
     * Registers a new user if the username does not already exist.
     *
     * @param user The user to be registered
     * @return true if registration is successful, false if the user already exists
     * @throws IOException If an I/O error occurs
     */
    public boolean signUp(User user) throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            file.createNewFile();
        }

        // Check if username already exists
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 1 && parts[0].equals(user.getUsername())) {
                    return false; // Username already exists
                }
            }
        }

        // Append new user data to the file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(user.toFileString());
            writer.newLine();
        }

        return true;
    }

    /**
     * Validates a user's login credentials.
     *
     * @param username The username to check
     * @param password The corresponding password
     * @return true if credentials are correct, false otherwise
     * @throws IOException If an I/O error occurs
     */
    public boolean login(String username, String password) throws IOException {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 2 &&
                    parts[0].equals(username) &&
                    parts[1].equals(password)) {
                    return true; // Credentials are valid
                }
            }
        }

        return false; // No match found
    }
}