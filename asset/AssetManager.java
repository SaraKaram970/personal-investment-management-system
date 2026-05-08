package asset;
import calculation.StockValueCalculation;
import calculation.CryptoValueCalculation;
import calculation.RealEstateValueCalculation;
import calculation.GoldValueCalculation;

import observer.Observer;
import observer.Subject;

import java.io.*;
import java.util.*;

/**
 * Manages user assets and applies the Observer pattern to notify changes.
 */
public class AssetManager implements Subject {

    private final String filePath = "assets.txt";
    private List<Observer> observers = new ArrayList<>();

    /**
     * Load all assets for the specified user from the file.
     *
     * @param username the user whose assets to load
     * @return list of assets owned by the user
     */
    public List<Asset> loadUserAssets(String username) throws IOException {
        List<Asset> assets = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return assets;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length == 6 && parts[0].equals(username)) {
                String type = parts[1];
                String name = parts[2];
                int quantity = Integer.parseInt(parts[3]);
                String date = parts[4];
                double price = Double.parseDouble(parts[5]);

                Asset asset = switch (type.toLowerCase()) {
                    case "stock" -> new Stock(username, name, quantity, date, price, new StockValueCalculation());
                    case "crypto" -> new Crypto(username, name, quantity, date, price, new CryptoValueCalculation());
                    case "realestate" -> new RealEstate(username, name, quantity, date, price, new RealEstateValueCalculation());
                    default -> null;
                };

                if (asset != null) {
                    assets.add(asset);
                }
            }
        }

        reader.close();
        return assets;
    }

    /**
     * Overwrite all user assets in the file with updated assets.
     *
     * @param username the username to update assets for
     * @param updatedAssets list of new or edited assets
     */
    public void saveAllAssets(String username, List<Asset> updatedAssets) throws IOException {
        File file = new File(filePath);

        if (!file.exists()) {
            return;
        }

        List<Asset> allAssets = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            if (parts.length == 6) {
                String user = parts[0];
                String type = parts[1];
                String name = parts[2];
                int qty = Integer.parseInt(parts[3]);
                String date = parts[4];
                double price = Double.parseDouble(parts[5]);

                Asset asset = switch (type.toLowerCase()) {
                    case "stock" -> new Stock(user, name, qty, date, price, new StockValueCalculation());
                    case "realestate" -> new RealEstate(user, name, qty, date, price, new RealEstateValueCalculation());
                    case "crypto" -> new Crypto(user, name, qty, date, price, new CryptoValueCalculation());
                    case "gold" -> new Gold(user, name, qty, date, price, new GoldValueCalculation());
                    default -> null;
                };

                if (asset != null) {
                    allAssets.add(asset);
                }
            }
        }

        reader.close();

        // Remove old user assets
        allAssets.removeIf(a -> a.getUsername().equals(username));

        // Add updated assets
        allAssets.addAll(updatedAssets);

        // Write all assets back to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        for (Asset a : allAssets) {
            writer.write(a.toFileString());
            writer.newLine();
        }

        writer.close();

        // Notify observers of changes
        notifyObservers(updatedAssets);
    }

    // ===== Observer Pattern Methods =====

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers(List<Asset> userAssets) {
        for (Observer o : observers) {
            o.update(userAssets);
        }
    }
}
