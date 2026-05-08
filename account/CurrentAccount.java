package account;
// CurrentAccount represents a bank account of type "Current".
// It implements the BankAccount interface.
public class CurrentAccount implements BankAccount {

    /**
     * Returns the type of the bank account.
     *
     * @return A string indicating this is a "Current Account"
     */
    @Override
    public String getAccountType() {
        return "Current Account";
    }
}