package zakat;

import java.util.List;
import asset.Asset;
import observer.Observer;

// ZakatService is an observer that calculates and displays total zakat when assets are updated.
public class ZakatService implements Observer {

    /**
     * Called when the list of assets is updated.
     * Calculates and prints the total zakat owed.
     *
     * @param assets The updated list of user assets
     */
    @Override
    public void update(List<Asset> assets) {
        double totalZakat = ZakatCompliance.calculateTotalZakat(assets);
        System.out.println("[ZakatService] Updated Zakat: " + totalZakat);
    }
}
