package observer;
import asset.Asset;
import java.util.List;

// Subject interface for managing observers and notifying them of asset updates.
public interface Subject {

    /**
     * Adds an observer to the subject.
     *
     * @param o The observer to be added
     */
    void addObserver(Observer o);

    /**
     * Removes an observer from the subject.
     *
     * @param o The observer to be removed
     */
    void removeObserver(Observer o);

    /**
     * Notifies all registered observers with the provided list of assets.
     *
     * @param userAssets The list of assets to be sent to observers
     */
    void notifyObservers(List<Asset> userAssets);
}