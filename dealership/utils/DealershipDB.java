package dealership.utils;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

public class DealershipDB {
    public static final boolean DROP_COMMENT_LINES = true;
    public static final boolean DROP_EMPTY_LINES = true;

    public static final String INVENTORY_FILE_NAME = "inventory.csv";
    public static final String USERS_FILE_NAME = "users.csv";
    public static final String SALES_FILE_NAME = "sales.csv";

    private final String dataDirPath;

    private final IOHelper ioHelper;
    public DealershipDB(String dataDirPath) {
        if(dataDirPath == null || dataDirPath.trim().isEmpty()) {
            throw new IllegalArgumentException("Data directory cannot be null or an a empty string");
        }
        this.dataDirPath = dataDirPath;
        this.ioHelper = new IOHelper(dataDirPath);
    }

    /**
     * Read the content of the inventory from the database and return each line as a String.
     * @return a list of String containing the lines from the inventory file.
     * @throws IOException if the inventory file cannot be opened for reading.
     */
    public List<String> loadInventory() throws IOException {
        return this.ioHelper.readFileContent(INVENTORY_FILE_NAME, DROP_COMMENT_LINES, DROP_EMPTY_LINES);
    }

    /**
     * Save the content in the inventory database. The current content is replaced
     * with the new content.
     *
     * @param csvLines csv lines that are to be saved as the inventory.
     * @throws IOException if the inventory file cannot be found or opened for writing
     */
    public void saveInventory(List<String> csvLines) throws IOException {
        this.ioHelper.writeLines(INVENTORY_FILE_NAME, csvLines);
    }

    public List<String> loadUsers() throws IOException {
        return this.ioHelper.readFileContent(USERS_FILE_NAME, DROP_COMMENT_LINES, DROP_EMPTY_LINES);
    }

    public void saveUsers(List<String> users) { }

    public List<String> loadSales() throws IOException {
        return this.ioHelper.readFileContent(SALES_FILE_NAME, DROP_COMMENT_LINES, DROP_EMPTY_LINES);
    }

    public void saveSales(List<String> csvLines) throws IOException {
        this.ioHelper.writeLines(SALES_FILE_NAME, csvLines);
    }

    public void writeSalesGrades(List<Integer> sales) { }
}
