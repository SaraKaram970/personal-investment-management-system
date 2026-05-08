
package asset;
import calculation.ValueCalculationStrategy;

// Crypto represents a cryptocurrency asset and extends the Asset class.
public class Crypto extends Asset {

    /**
     * Constructs a new Crypto asset.
     *
     * @param username       The username of the asset owner
     * @param name           The name of the cryptocurrency
     * @param quantity       The quantity purchased
     * @param purchaseDate   The date the asset was purchased
     * @param purchasePrice  The price at which the asset was purchased
     * @param strategy       The strategy used to calculate value
     */
    public Crypto(String username, String name, int quantity, String purchaseDate,
                  double purchasePrice, ValueCalculationStrategy strategy) {

        // Call to superclass constructor with all required parameters
        super(username, name, quantity, purchaseDate, purchasePrice, strategy);
    }

    /**
     * Returns the type of this asset.
     *
     * @return A string indicating the asset type: "Crypto"
     */
    @Override
    public String getAssetType() {
        return "Crypto";
    }
}