package observer;
import asset.Asset;
import java.util.List;

// Observer interface for receiving updates when asset data changes.
public interface Observer {

    /**
     * Called to update the observer with the latest list of assets.
     *
     * @param assets The updated list of Asset objects
     */
    void update(List<Asset> assets);
}