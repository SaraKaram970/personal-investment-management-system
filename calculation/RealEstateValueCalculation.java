package calculation;
import asset.Asset;
// RealEstateValueCalculation implements the strategy to calculate the value of real estate assets.
public class RealEstateValueCalculation implements ValueCalculationStrategy {

    /**
     * Calculates the value of the real estate asset.
     * Uses the formula: quantity × purchasePrice
     *
     * @param asset The real estate asset whose value is being calculated
     * @return The calculated total value of the asset
     */
    @Override
    public double calculateValue(Asset asset) {
        return asset.getQuantity() * asset.getPurchasePrice();
    }
}