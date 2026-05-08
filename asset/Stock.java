package asset;
import calculation.ValueCalculationStrategy;

// Stock represents a stock asset and extends the Asset class.
public class Stock extends Asset {

    /**
     * Constructs a Stock asset with the given parameters.
     *
     * @param username       The username of the asset owner
     * @param name           The name of the stock
     * @param quantity       The number of shares
     * @param purchaseDate   The date the stock was purchased
     * @param purchasePrice  The price per share
     * @param strategy       The strategy used to calculate the asset's value
     */
    public Stock(String username, String name, int quantity, String purchaseDate,
                 double purchasePrice, ValueCalculationStrategy strategy) {

        // Ensure these values are passed correctly to the parent constructor
        super(username, name, quantity, purchaseDate, purchasePrice, strategy);
    }

    /**
     * Returns the type of this asset.
     *
     * @return "Stock" as the asset type
     */
    @Override
    public String getAssetType() {
        return "Stock";
    }
}