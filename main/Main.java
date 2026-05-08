package main;
import auth.*;
import account.*;
import asset.*;
import calculation.*;
import observer.*;
import observer.Observer;
import zakat.*;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        AuthService authService = AuthService.getInstance();

        System.out.println("Welcome to Invest Wise");
        System.out.println("1 - Sign Up");
        System.out.println("2 - Login");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        boolean loggedIn = false;
        String username = "";

        // Handle user sign-up
        if (choice == 1) {
            System.out.println("=== User Sign-Up ===");
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            User newUser = new User(username, password, email);
            boolean success = authService.signUp(newUser);

            if (success) {
                System.out.println("Sign-up successful! You can now log in.");
            } else {
                System.out.println("Username already exists. Try again.");
                scanner.close();
                return;
            }
        }

        // Handle login for sign-up or login option
        if (choice == 2 || choice == 1) {
            System.out.println("\n=== User Login ===");
            System.out.print("Enter username: ");
            username = scanner.nextLine();

            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            loggedIn = authService.login(username, password);

            if (loggedIn) {
                System.out.println("Login successful! Welcome, " + username);
            } else {
                System.out.println("Login failed. Exiting...");
                scanner.close();
                return;
            }
        } else {
            System.out.println("❗ Invalid choice.");
            scanner.close();
            return;
        }

        // Initialize asset-related services
        AssetService assetService = new AssetService();
        AssetManager assetManager = new AssetManager();

        // Register zakat observer
        Observer zakatService = new ZakatService();
        assetManager.addObserver(zakatService);

        // Load user assets
        List<Asset> assets = assetManager.loadUserAssets(username);

        // Set appropriate value strategy for each asset
        for (Asset a : assets) {
            switch (a.getAssetType().toLowerCase()) {
                case "stock" -> a.setStrategy(new StockValueCalculation());
                case "realestate" -> a.setStrategy(new RealEstateValueCalculation());
                case "crypto" -> a.setStrategy(new CryptoValueCalculation());
                case "gold" -> a.setStrategy(new GoldValueCalculation());
            }
        }

        // Display assets and calculated values
        System.out.println("\n📊 Your Assets:");
        for (Asset a : assets) {
            double value = a.getStrategy().calculateValue(a);
            System.out.println("- " + a.getAssetType() + ": " + a.getName() +
                    " | Qty: " + a.getQuantity() +
                    " | Price: " + a.getPurchasePrice() +
                    " | Total Value: " + value);
        }

        // Display total zakat
        double totalZakat = ZakatCompliance.calculateTotalZakat(assets);
        System.out.println("\nTotal Zakat: " + totalZakat);

        // Asset management menu
        while (true) {
            System.out.println("\n=== Asset Management ===");
            System.out.println("1 - Add Asset");
            System.out.println("2 - Edit Asset");
            System.out.println("3 - Remove Asset");
            System.out.println("4 - Connect Bank Account");
            System.out.println("5 - Exit");
            System.out.print("Choose an option: ");
            int assetChoice = Integer.parseInt(scanner.nextLine());

            boolean notifyObserver = false;

            if (assetChoice == 1) {
                // Add Asset
                System.out.print("Enter Asset Type (Stock, RealEstate, Crypto, Gold): ");
                String type = scanner.nextLine();

                System.out.print("Enter Asset Name: ");
                String name = scanner.nextLine();

                System.out.print("Enter Quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter Purchase Date: ");
                String date = scanner.nextLine();

                System.out.print("Enter Purchase Price: ");
                double price = Double.parseDouble(scanner.nextLine());

                Asset asset = switch (type.toLowerCase()) {
                    case "stock" -> new Stock(username, name, qty, date, price, new StockValueCalculation());
                    case "realestate" -> new RealEstate(username, name, qty, date, price, new RealEstateValueCalculation());
                    case "crypto" -> new Crypto(username, name, qty, date, price, new CryptoValueCalculation());
                    case "gold" -> new Gold(username, name, qty, date, price, new GoldValueCalculation());
                    default -> null;
                };

                if (asset != null) {
                    assetService.saveAsset(asset);
                    assets.add(asset);
                    notifyObserver = true;
                    System.out.println("Asset saved successfully.");
                } else {
                    System.out.println("Invalid asset type.");
                }

            } else if (assetChoice == 2) {
                // Edit Asset
                if (assets.isEmpty()) {
                    System.out.println("No assets to edit.");
                    continue;
                }

                for (int i = 0; i < assets.size(); i++) {
                    Asset a = assets.get(i);
                    System.out.println((i + 1) + ". " + a.getAssetType() + " - " + a.getName()+ " (Qty: " + a.getQuantity() + ")");
                }

                System.out.print("Enter asset number to edit: ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;

                if (index >= 0 && index < assets.size()) {
                    Asset a = assets.get(index);

                    System.out.print("Enter new quantity: ");
                    int newQty = Integer.parseInt(scanner.nextLine());

                    System.out.print("Enter new purchase price: ");
                    double newPrice = Double.parseDouble(scanner.nextLine());

                    a.setQuantity(newQty) ;
                    a.setPurchasePrice(newPrice);  ;

                    assetManager.saveAllAssets(username, assets);
                    notifyObserver = true;
                    System.out.println("Asset updated successfully.");
                } else {
                    System.out.println("Invalid index.");
                }

            } else if (assetChoice == 3) {
                // Remove Asset
                if (assets.isEmpty()) {
                    System.out.println("No assets to remove.");
                    continue;
                }

                for (int i = 0; i < assets.size(); i++) {
                    Asset a = assets.get(i);
                    System.out.println((i + 1) + ". " + a.getAssetType() + " - " + a.getName() + " (Qty: " + a.getQuantity() + ")");
                }

                System.out.print("Enter asset number to remove: ");
                int index = Integer.parseInt(scanner.nextLine()) - 1;

                if (index >= 0 && index < assets.size()) {
                    assets.remove(index);
                    assetManager.saveAllAssets(username, assets);
                    notifyObserver = true;
                    System.out.println("Asset removed successfully.");
                } else {
                    System.out.println("Invalid index.");
                }

            } else if (assetChoice == 4) {
                // Connect bank account
                BankAccountConnector.connectBankAccount();
                break;

            } else if (assetChoice == 5) {
                // Exit
                System.out.println("Exiting... Goodbye!");
                break;

            } else {
                System.out.println("Invalid option.");
            }

            // Notify observers after any asset change
            if (notifyObserver) {
                assetManager.notifyObservers(assets);
            }
        }

        scanner.close();
    }
}