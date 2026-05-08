package account;
// It implements the BankAccount interface.
public class SavingsAccount implements BankAccount {

    /**
     * Returns the type of the bank account.
     *
     * @return A string indicating this is a "Savings Account"
     */
    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}