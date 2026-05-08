package account;
import java.util.Scanner;

// BankAccountConnector handles the process of linking a bank account via user input.
public class BankAccountConnector {

    /**
     * Connects a bank account by prompting the user for details and verifying input.
     */
    public static void connectBankAccount() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Connect Bank Account ===");

        // Prompt user to select account type
        System.out.println("Select Account Type:");
        System.out.println("1. Savings");
        System.out.println("2. Current");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Determine account type based on user input
        AccountType type = (choice == 1) ? AccountType.SAVINGS : AccountType.CURRENT;
        BankAccount account = BankAccountFactory.createAccount(type);

        // Display selected account type
        System.out.println("You selected: " + account.getAccountType());

        // Prompt for card details
        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter Expiration Date (MM/YY): ");
        String expiry = scanner.nextLine();

        System.out.print("Enter CVV: ");
        String cvv = scanner.nextLine();

        // Simulate verification
        System.out.println("Verifying card...");
        System.out.println("Card verified!");

        // Simulate OTP verification
        System.out.println("Sending OTP... (Your OTP is 123456)");
        System.out.print("Enter OTP: ");
        String inputOtp = scanner.nextLine();

        // Check OTP input
        if (inputOtp.equals("123456")) {
            System.out.println(" Bank account linked successfully!");
        } else {
            System.out.println(" Invalid OTP. Please try again.");
        }

        // Close the scanner resource
        scanner.close();
    }
}