package zakat;
import asset.Asset;
import java.util.List;

// ZakatCompliance provides utility methods to calculate zakat on assets.
public class ZakatCompliance {

    /**
     * Calculates zakat (2.5%) for a single asset.
     * The asset's value is computed using its assigned value calculation strategy.
     *
     * @param asset The asset to calculate zakat for
     * @return The zakat amount for the asset
     */
    public static double calculateZakat(Asset asset) {
        double value = asset.calculateValue(); // Uses the assigned strategy
        return value * 0.025; // 2.5% zakat rate
    }

    /**
     * Calculates the total zakat owed for a list of assets.
     *
     * @param assets A list of assets
     * @return The total zakat for all assets
     */
    public static double calculateTotalZakat(List<Asset> assets) {
        double totalZakat = 0.0;

        for (Asset asset : assets) {
            totalZakat += calculateZakat(asset);
        }

        return totalZakat;
    }
}