package asset;
import calculation.ValueCalculationStrategy;

// Gold represents a gold asset and extends the Asset class.
public class Gold extends Asset {

    /**
     * Constructs a Gold asset with the provided parameters.
     *
     * @param username       The username of the asset owner
     * @param name           The name of the gold asset
     * @param quantity       The quantity of gold
     * @param purchaseDate   The purchase date of the gold
     * @param purchasePrice  The purchase price per unit
     * @param strategy       The strategy used for calculating the asset's value
     */
    public Gold(String username, String name, int quantity, String purchaseDate,
                double purchasePrice, ValueCalculationStrategy strategy) {
        
        // Ensures these values are passed correctly to the parent constructor
        super(username, name, quantity, purchaseDate, purchasePrice, strategy);
    }

    /**
     * Returns the type of this asset.
     *
     * @return "Gold" as the asset type
     */
    @Override
    public String getAssetType() {
        return "Gold";
    }
}