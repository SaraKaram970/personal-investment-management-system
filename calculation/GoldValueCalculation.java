package calculation;
import asset.Asset;
// GoldValueCalculation implements the strategy to calculate the value of gold assets.

public class GoldValueCalculation implements ValueCalculationStrategy {



    // Constant representing the current market price per gram of gold

    private static final double CURRENT_PRICE_PER_GRAM = 2500.0;



    /**

     * Calculates the value of the gold asset using the formula:

     * quantity × CURRENT_PRICE_PER_GRAM × purchasePrice

     *

     * @param asset The gold asset whose value is being calculated

     * @return The calculated value of the asset

     */

    @Override

    public double calculateValue(Asset asset) {

        return asset.getQuantity() * CURRENT_PRICE_PER_GRAM * asset.getPurchasePrice();

    }

}