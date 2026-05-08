package calculation;
import asset.Asset;
// StockValueCalculation implements the strategy to calculate the value of stock assets.
public class StockValueCalculation implements ValueCalculationStrategy {

    /**
     * Calculates the value of the stock asset.
     * Uses the formula: quantity × purchasePrice
     *
     * @param asset The stock asset whose value is being calculated
     * @return The calculated total value of the asset
     */
    @Override
    public double calculateValue(Asset asset) {
        return asset.getQuantity() * asset.getPurchasePrice();
    }
}