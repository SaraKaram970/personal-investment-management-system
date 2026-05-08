package asset;
import calculation.ValueCalculationStrategy;

// RealEstate represents a real estate asset and extends the Asset class.
public class RealEstate extends Asset {

    /**
     * Constructs a RealEstate asset with the given parameters.
     *
     * @param username       The username of the asset owner
     * @param name           The name of the property
     * @param quantity       The number of properties
     * @param purchaseDate   The date the asset was purchased
     * @param purchasePrice  The price at which the property was purchased
     * @param strategy       The strategy used to calculate the asset's value
     */
    public RealEstate(String username, String name, int quantity, String purchaseDate,
                      double purchasePrice, ValueCalculationStrategy strategy) {
        super(username, name, quantity, purchaseDate, purchasePrice, strategy);
    }

    /**
     * Returns the type of this asset.
     *
     * @return "RealEstate" as the asset type
     */
    @Override
    public String getAssetType() {
        return "RealEstate";
    }
}