package dealership;

import dealership.utils.DealershipDB;
import dealership.utils.Utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * This is just for demonstration purposes which you can use to see how to load and use
 * the database. This class should not be considered as a design guide or reference.
 */
public class TestApp {
    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.err.println("Usage: dealership.TestApp <database directory path>");
            System.exit(1);
        }
        DealershipDB ddb = new DealershipDB(args[0]);

        System.out.println("Inventory Records:");
        List<String> inventory = ddb.loadInventory();
        inventory.forEach(System.out::println);

        System.out.println("\nSales Records:");
        List<String> sales = ddb.loadSales();
        sales.forEach(System.out::println);

        System.out.println("\nUser Records:");
        List<String> users = ddb.loadUsers();
        users.forEach(System.out::println);

        String[] values = Utils.parseCSV(users.get(0), true);
        System.out.println(Arrays.asList(values));

        // let's make some changes to the inventory and write it back.
        // remove the first car from the inventory - if exists
        if(inventory.size() > 0) {
            inventory.removeFirst();
        }
        // add a new csv String to the inventory
        inventory.add(String.format("%s, %s, %s, %s, %d, %d, %s, %d, %s, %s, %s, %s",
                "GHI789", "2025-01-10", "Chevy", "Volt", 2020, 20000, "Red", 4000, "x", "y", "z", "Available"));
        // save the inventory to the database. You should see the changes in the file
        ddb.saveInventory(inventory);
    }
}
