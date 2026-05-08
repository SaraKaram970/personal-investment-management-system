package auth;
// User class represents a user with login credentials and email information.
public class User {

    // Instance variables for user credentials and contact info
    private String username;
    private String password;
    private String email;

    /**
     * Constructs a User object with the specified username, password, and email.
     *
     * @param username The username of the user
     * @param password The user's password
     * @param email    The user's email address
     */
    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    /**
     * Gets the username.
     *
     * @return The user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password.
     *
     * @return The user's password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Gets the email address.
     *
     * @return The user's email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Converts the user data to a file-friendly string format.
     *
     * @return A comma-separated string of username, password, and email
     */
    public String toFileString() {
        return username + "," + password + "," + email;
    }
}