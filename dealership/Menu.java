package dealership;

import java.io.IOException;

import static dealership.Login.*;

public class Menu {
    public static void managerMenu() throws IOException {
        //Prints the manager menu screen with all options
        printHeader("Manager Main Menu", null);
        System.out.println("1. Add a Car");
        System.out.println("2. Delete a Car");
        System.out.println("3. Generate Report");
        System.out.println("4. Log Out");
        System.out.println("--------------------------");
        printFooter();
        //Loops through validation process until user gives proper input
        boolean temp = true;
        while(temp) {
            int choice = Validator.validateChoice(1, 4);
            switch (choice) {
                case (1): {
                    //Calls function to add a car and returns to the manager menu when complete
                    dealership.Car.addCar();
                    managerMenu();
                    break;
                }
                case (2): {
                    //Calls function to delete a car and returns to the manager menu when complete
                    dealership.Car.deleteCar();
                    managerMenu();
                    break;
                }
                case (3): {
                    //Opens the report menu
                    reportMenu();
                    break;
                }
                case (4): {
                    //Saves all changes made by the manager to the inventory and returns to the login menu
                    Car.inventoryChanges();
                    dealership.Login.returnToLogin();
                    temp = false;
                    break;
                }
            }
        }
    }

    public static void salesMenu() throws IOException {
        //Prints the sales menu screen with all options
        printHeader("Salesperson Main Menu", null);
        System.out.println("1. Search Inventory");
        System.out.println("2. Update Status");
        System.out.println("3. Log Out");
        System.out.println("--------------------------");
        printFooter();
        //Loops through validation process until user gives proper input
        boolean temp = true;
        while(temp) {
            int choice = Validator.validateChoice(1, 3);
            switch(choice) {
                case (1): {
                    //Section not implemented yet
                    searchMenu();
                    break;
                }
                case (2): {
                    //Calls function to update a car's status and returns to the sales menu when complete
                    Car.updateStatus();
                    salesMenu();
                    break;
                }
                case (3): {
                    //Saves all changes made by the salesperson to the inventory and returns to the login menu
                    ddb.saveInventory(inventory);
                    System.out.println("All changes have been successfully written to the database.");
                    dealership.Login.returnToLogin();
                    temp = false;
                    break;
                }
            }
        }
    }

    public static void reportMenu() throws IOException {
        //Prints the report menu screen with all options
        printHeader("Report Menu", null);
        System.out.println("1. Inventory");
        System.out.println("2. Sales");
        System.out.println("3. Main Menu");
        System.out.println("--------------------------");
        printFooter();
        //Loops through validation process until user gives proper input
        boolean temp = true;
        while(temp) {
            int choice = Validator.validateChoice(1, 3);
            switch(choice) {
                case (1): {
                    //Calls function to print out inventory data and returns to the report menu
                    dealership.Car.printInventory();
                    reportMenu();
                    break;
                }
                case (2): {
                    //Opens the sales report menu
                    salesReportMenu();
                    break;
                }
                case (3): {
                    //Returns to the manager menu
                    managerMenu();
                    temp = false;
                    break;
                }
            }
        }
    }

    public static void salesReportMenu() throws IOException {
        //Prints the manager menu screen with all options
        printHeader("Sales Report Menu", null);
        System.out.println("1. All Sales");
        System.out.println("2. Sold By");
        System.out.println("3. Monthly Gross Profit");
        System.out.println("4. Main Menu");
        System.out.println("--------------------------");
        printFooter();
        //Loops through validation process until user gives proper input
        boolean temp = true;
        while(temp) {
            int choice = Validator.validateChoice(1, 4);
            switch (choice) {
                case (1): {
                    //Calls function to retrieve all sales and returns to the sales report menu when complete
                    Sale.retrieveSales();
                    salesReportMenu();
                    break;
                }
                case (2): {
                    //Calls function to find sales sold by a certain name and returns to the sales report menu when complete
                    Sale.soldBy();
                    salesReportMenu();
                    break;
                }
                case (3): {
                    //Section not implemented yet
                    System.out.println("We're sorry, but option 3 hasn't been implemented yet. Please pick from the following options: 1, 2, 4.");
                    salesReportMenu();
                    break;
                }
                case (4): {
                    //Returns to the main manager menu
                    managerMenu();
                    temp = false;
                    break;
                }
            }
        }
    }

    public static void searchMenu() throws IOException {
        //Prints the report menu screen with all options
        printHeader("Search Menu", null);
        System.out.println("1. By Status");
        System.out.println("2. By VIN");
        System.out.println("3. By Color");
        System.out.println("4. By Year");
        System.out.println("5. By Make");
        System.out.println("6. By Price");
        System.out.println("7. Main Menu");
        System.out.println("--------------------------");
        printFooter();
        //Loops through validation process until user gives proper input
        boolean temp = true;
        while(temp) {
            int choice = Validator.validateChoice(1, 7);
            switch(choice) {
                case (1): {
                    //Searches car inventory by Status
                    Search.searchByStatus();
                    searchMenu();
                    break;
                }
                case (2): {
                    //Searches car inventory by VIN
                    Search.searchByVin();
                    searchMenu();
                    break;
                }
                case (3): {
                    //Searches car inventory by Color
                    Search.searchByColor();
                    searchMenu();
                    break;
                }
                case (4): {
                    //Searches car inventory by Year
                    Search.searchByYear();
                    searchMenu();
                    break;
                }
                case (5): {
                    //Searches car inventory by Make
                    Search.searchByMake();
                    searchMenu();
                    break;
                }
                case (6): {
                    //Searches car inventory by Price
                    Search.searchByPrice();
                    searchMenu();
                    break;
                }
                case (7): {
                    //Returns to the sales menu
                    salesMenu();
                    temp = false;
                    break;
                }
            }
        }
    }

    //Prints out a car's data to be used in the function to delete cars
    public static void deleteCarMenu(Car car) {
        System.out.println("The following car matches the VIN you entered.");
        System.out.println("VIN: " + car.getVIN());
        System.out.println("Inventory Date: " + car.getDate());
        System.out.println("Make: " + car.getMake());
        System.out.println("Model: " + car.getModel());
        System.out.println("Year: " + car.getYear());
        System.out.println("Mileage: " + car.getMileage());
        System.out.println("Color: " + car.getColor());
        System.out.println("Purchase Price: " + car.getPurchasePrice());
        System.out.println("List Price: " + car.getListPrice());
    }

    //Function to print the header of each menu screen
    public static void printHeader(String header, String title) {
        if(title != null){
            System.out.println(title);
        }
        System.out.println("================================");
        System.out.println(header);
        System.out.println("================================");
    }

    //Prints out the choice footer
    public static void printFooter() {
        System.out.print("Please make a choice: ");
    }
}