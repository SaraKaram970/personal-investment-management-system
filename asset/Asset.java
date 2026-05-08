package asset;
import calculation.ValueCalculationStrategy;

public abstract class Asset {

    // === Fields ===
    protected String name;
    protected int quantity;
    protected String purchaseDate;
    protected double purchasePrice;
    protected String username; // Link the asset to a specific user
    protected ValueCalculationStrategy strategy;

    // === Constructor ===
    public Asset(String username, String name, int quantity, String purchaseDate, double purchasePrice, ValueCalculationStrategy strategy) {
        this.username = username;
        this.name = name;
        this.quantity = quantity;
        this.purchaseDate = purchaseDate;
        this.purchasePrice = purchasePrice;
        this.strategy = strategy;
    }

    // === Getters ===
    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public ValueCalculationStrategy getStrategy() {
        return strategy;
    }

    // === Setters ===
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStrategy(ValueCalculationStrategy strategy) {
        this.strategy = strategy;
    }

    // === Business Logic ===

    /**
     * Calculate the asset's value using the current strategy.
     */
    public double calculateValue() {
        return strategy.calculateValue(this);
    }

    /**
     * Return the asset type as a string (e.g., "Stock", "Crypto").
     */
    public abstract String getAssetType();
    /**
     * Convert asset data to a comma-separated string for file storage.
     */
    public String toFileString() {
        return username + "," + getAssetType() + "," + name + "," + quantity + "," + purchaseDate + "," + purchasePrice;
    }
    
    

    
}
