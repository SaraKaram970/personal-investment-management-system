package account;
// BankAccountFactory is responsible for creating instances of BankAccount based on account type.
public class BankAccountFactory {

    /**
     * Creates and returns a BankAccount instance based on the provided AccountType.
     *
     * @param type The type of account to create (SAVINGS or CURRENT)
     * @return A BankAccount object corresponding to the specified type
     * @throws IllegalArgumentException if the account type is unknown
     */
    public static BankAccount createAccount(AccountType type) {
        switch (type) {
            case SAVINGS:
                return new SavingsAccount();
            case CURRENT:
                return new CurrentAccount();
            default:
                throw new IllegalArgumentException("Unknown account type");
        }
    }
}