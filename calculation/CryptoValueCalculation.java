package calculation;
import asset.Asset;
// CryptoValueCalculation implements the strategy to calculate the value of a crypto asset.
public class CryptoValueCalculation implements ValueCalculationStrategy {

    /**
     * Calculates the value of the given asset by multiplying quantity and purchase price.
     *
     * @param asset The asset whose value is being calculated
     * @return The calculated value of the asset
     */
    @Override
    public double calculateValue(Asset asset) {
        return asset.getQuantity() * asset.getPurchasePrice();
    }
}