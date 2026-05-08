package calculation;
import asset.Asset;
// ValueCalculationStrategy defines a strategy interface for calculating asset value.
public interface ValueCalculationStrategy {

    /**
     * Calculates the value of a given asset.
     *
     * @param asset The asset to calculate the value for
     * @return The calculated value of the asset
     */
    double calculateValue(Asset asset);
}