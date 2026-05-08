package asset;
import java.io.*;

// The AssetService class handles saving Asset objects to a file.
public class AssetService {
    
    // Constant for the asset file path. Named in all caps with underscores per style guide.
    private static final String ASSET_FILE_PATH = "assets.txt";

    /**
     * Saves the given Asset object to a file.
     * This method appends the asset data to the specified asset file.
     *
     * @param asset The Asset object to be saved.
     * @throws IOException If an I/O error occurs.
     */
    public void saveAsset(Asset asset) throws IOException {
        // Create a File object for the asset file
        File file = new File(ASSET_FILE_PATH);

        // Check if the file does not exist and create it
        if (!file.exists()) {
            file.createNewFile();
        }

        // Use try-with-resources to ensure BufferedWriter is closed properly
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            // Write the asset's string representation to the file
            writer.write(asset.toFileString());
            writer.newLine(); // Add a new line after the asset entry
        }
    }
}