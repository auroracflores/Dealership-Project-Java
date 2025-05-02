package dealership;
import dealership.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

import static dealership.Login.*;
import static dealership.Login.ddb;

public class Car {
    //Initializes all properties for objects of car
    private String VIN;
    private String date;
    private String make;
    private String model;
    private int year;
    private int mileage;
    private String color;
    private int listprice;
    private int purchaseprice;
    private int sellingprice;
    private String status;

    //Initializes counter variables to keep track of the number of cars added/deleted
    private static int carsAdded = 0;
    private static int carsDeleted = 0;

    //Constructor method to assign properties to a new car object
    public Car(String carData) {
        String[] values = Utils.parseCSV(carData, true);
        this.VIN = values[0];
        this.date = values[1];
        this.make = values[2];
        this.model = values[3];
        this.year = Integer.parseInt(values[4]);
        this.mileage = Integer.parseInt(values[5]);
        this.color = values[6];
        this.purchaseprice = Integer.parseInt(values[7]);
        this.listprice = Integer.parseInt(values[8]);
        this.status = values[9];
    }

    //Returns the current date in the proper string format
    public static String date() {
        return LocalDate.now().toString();
    }

    //Returns the properties of a car in CSV format
    public String toCSV() {
        return String.format("%s, %s, %s, %s, %d, %d, %s, %d, %d, %s",
        this.VIN, this.date, this.make, this.model, this.year, this.mileage, this.color, this.purchaseprice, this.listprice, this.status);
    }

    //Prints the current inventory
    public static void printInventory() {
        System.out.println("Found " + inventory.size() + " cars:");
        System.out.println("VIN, Date Added, Make, Model, Year, Mileage, Color, Purchase Price, List Price, Status");
        for (String car : inventory) {
            System.out.println(car);
        }
        System.out.print("Press the return key to continue.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    //Finds a car based off of a given VIN number
    public static Car findCarByVIN(String VIN) {
        for (Car car : carObjects) {
            if(car.getVIN().equals(VIN)) {
                return car;
            }
        }
        return null;
    }

    //Adds a new car to the inventory
    public static void addCar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        //Gets the data for the car from the user
        System.out.println("Adding a new car.\nPlease provide the following information for the new car.");
        System.out.print("VIN: ");
        String vin = scanner.nextLine();
        //Receives user input
        while(!Validator.validateVIN(vin, carObjects)) {
            System.out.print("Please enter a valid VIN: ");
            vin = scanner.nextLine();
        }
        System.out.print("Make: ");
        String make = scanner.nextLine();
        while(!Validator.validateMake(make)) {
            System.out.print("Please enter a valid make: ");
            make = scanner.nextLine();
        }
        System.out.print("Model: ");
        String model = scanner.nextLine();
        while(!Validator.validateModel(model)) {
            System.out.print("Please enter a valid model: ");
            model = scanner.nextLine();
        }
        System.out.print("Year: ");
        String year = scanner.nextLine();
        while(!Validator.validateYear(year)) {
            System.out.print("Please enter a valid year: ");
            year = scanner.nextLine();
        }
        System.out.print("Mileage: ");
        String mileage = scanner.nextLine();
        while(!Validator.validateMileage(mileage)) {
            System.out.print("Please enter a valid mileage: ");
            mileage = scanner.nextLine();
        }
        System.out.print("Color: ");
        String color = scanner.nextLine();
        while(!Validator.validateColor(color)) {
            System.out.print("Please enter a valid color: ");
            color = scanner.nextLine();
        }
        System.out.print("Purchase Price: ");
        String purchasePrice = scanner.nextLine();
        while(!Validator.validatePrice(purchasePrice)) {
            System.out.print("Please enter a valid list price: ");
            purchasePrice = scanner.nextLine();
        }
        System.out.print("List Price: ");
        String listPrice = scanner.nextLine();
        while(!Validator.validatePrice(listPrice)) {
            System.out.print("Please enter a valid list price: ");
            listPrice = scanner.nextLine();
        }
        //Organizes given car properties in CSV format
        String carData = String.format("%s, %s, %s, %s, %s, %s, %s, %s, %s, %s",
                vin, date(), make, model, year, mileage, color, purchasePrice, listPrice, "Available");
        //Creates a new car object with properties given by the user
        Car c = new Car(carData);
        //Adds the car to carObjects and inventory and increments counter carsAdded
        carObjects.add(c);
        inventory.add(carData);
        carsAdded++;
        //Returns to the manager menu
        System.out.print("The car has been added to the inventory successfully. Press the return key to continue.");
        scanner.nextLine();
    }

    //Deletes a car from the inventory
    public static void deleteCar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        //Finds the car using a VIN given by the user
        System.out.print("Please enter the VIN of the car you want to delete: ");
        String VIN = scanner.nextLine();
        for(Car car : carObjects) {
            if(car.getVIN().equals(VIN)) {
                //Prints out the car that was found
                Menu.deleteCarMenu(car);
                System.out.print("Press D to confirm the deletion or C to cancel: \n");
                //Confirms deletion or terminates deletion
                while(true) {
                    String userConfirmation = scanner.nextLine();
                    if(userConfirmation.equalsIgnoreCase("D")) {
                        //Deletes the car from carObjects
                        int index = carObjects.indexOf(car);
                        carObjects.remove(car);
                        if(index != -1) {
                            inventory.remove(index);
                        }
                        carsDeleted++;
                        System.out.println("Deletion successful!");
                        //Returns to the manager menu
                        System.out.print("Press the return key to continue.");
                        scanner.nextLine();
                        return;
                    }
                    else if(userConfirmation.equalsIgnoreCase("C")) {
                        System.out.println("Deletion process terminated.");
                        //Returns to the manager menu
                        System.out.print("Press the return key to continue.");
                        scanner.nextLine();
                        return;
                    }
                    else {
                        System.out.println("Invalid input. Please enter D to confirm deletion or C to cancel.");
                    }
                }
            }
        }
        System.out.println("VIN " + VIN + " does not match any car in the system.");
        System.out.print("Press the return key to continue.");
        if(scanner.nextLine().isEmpty()) {
            Menu.managerMenu();
        }
    }

    public static void updateStatus() throws IOException {
        Scanner scanner = new Scanner(System.in);
        //Gets car VIN from user
        System.out.print("Please enter the VIN of the car you want to update: ");
        String VIN = scanner.nextLine();
        String status;
        boolean temp = true;
        //Finds the car using a VIN given by the user and prints out all fields
        while(true) {
            for(Car car : carObjects) {
                if (car.getVIN().equals(VIN)) {
                    System.out.println(car.toCSV());
                    status = car.getStatus();
                    switch(status) {
                        case("In Transit"): {
                            System.out.print("Enter 1 for In Service, or 0 for no update: ");
                            int choice = Validator.validateChoice(0, 1);
                            if(choice == 1) {
                                car.setStatus("In Service");
                                car.updateInventorySlot();
                                ddb.saveInventory(inventory);
                                System.out.println("The car record has been updated successfully.");
                                return;
                            }
                            else {
                                System.out.println("Status update canceled.");
                                return;
                            }
                        }
                        case("In Service"): {
                            System.out.print("Enter 1 for Available, 2 for Sold, or 0 for no update: ");
                            int choice = Validator.validateChoice(0, 2);
                            if(choice == 1) {
                                car.setStatus("Available");
                                car.updateInventorySlot();
                                ddb.saveInventory(inventory);
                                System.out.println("The car record has been updated successfully.");
                                return;
                            }
                            else if(choice == 2) {
                                createSalesRecord(car);
                                car.setStatus("Sold");
                                car.setDate(date());
                                car.updateInventorySlot();
                                ddb.saveInventory(inventory);
                                System.out.println("The car record has been updated successfully.");
                                return;
                            }
                            else {
                                System.out.println("Status update canceled.");
                                return;
                            }
                        }
                        case("Available"): {
                            System.out.print("Enter 1 for In Service, 2 for Sold, or 0 for no update: ");
                            int choice = Validator.validateChoice(0, 2);
                            if(choice == 1) {
                                car.setStatus("In Service");
                                car.updateInventorySlot();
                                ddb.saveInventory(inventory);
                                System.out.println("The car record has been updated successfully.");
                                return;
                            }
                            else if(choice == 2) {
                                car.setStatus("Sold");
                                car.setDate(date());
                                createSalesRecord(car);
                                car.updateInventorySlot();
                                ddb.saveInventory(inventory);
                                System.out.println("The car record has been updated successfully.");
                                return;
                            }
                            else {
                                System.out.println("Status update canceled.");
                                return;
                            }
                        }
                        default: {
                            System.out.println("No status changes are available for this car.");
                            return;
                        }
                    }
                }
            }
            System.out.println("VIN " + VIN + " does not match any car in the system.");
            System.out.print("Press the return key to continue.");
            if(scanner.nextLine().isEmpty()) {
                Menu.salesMenu();
            }
        }
    }

    public static void createSalesRecord(Car car) throws IOException {
        Scanner scanner = new Scanner(System.in);
        //Get selling price from user
        System.out.print("Enter selling price: ");
        int sellingPrice = scanner.nextInt();
        //Set car's purchase price property to sellingPrice
        car.setSellingPrice(sellingPrice);
        //Get buyer name from user
        System.out.print("Enter buyer's name: ");
        String buyerFirstName = scanner.next();
        String buyerLastName = scanner.next();
        //Create a CSV string with necessary data for the sale record
        String salesRecord = String.format("%s, %s, %d, %s %s, %s %s",
                car.getVIN(), date(), sellingPrice, user.getFirstName(), user.getLastName(), buyerFirstName, buyerLastName);
        //Create Sales object of sold car
        Sale s = new Sale(salesRecord);
        salesObjects.add(s);
        //Add CSV string to List sales and save the changes to the sales.csv file
        sales.add(salesRecord);
        ddb.saveSales(sales);
    }
    //Updates the inventory record of the car by updating its old CSV line
    public void updateInventorySlot() {
        //Gets the index of the car
        int index = carObjects.indexOf(this);
        //Removes the old CSV value from its place in the inventory records
        if(index != -1) {
            inventory.remove(index);
        }
        //Adds the updated car object's CSV value to its place in the inventory records
        inventory.add(index, this.toCSV());
    }
    //Saves any changes made to the CSV file and reports the changes
    public static void inventoryChanges() throws IOException {
        ddb.saveInventory(inventory);
        System.out.println("All changes have been successfully written to the database.");
        System.out.println("Cars Added: " + carsAdded);
        System.out.println("Cars Deleted: " + carsDeleted);
    }

    //All getter methods
    public String getVIN() {
        return VIN;
    }

    public String getDate() {
        return date;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getMileage() {
        return mileage;
    }

    public String getColor() {
        return color;
    }

    public int getListPrice() {
        return listprice;
    }

    public int getPurchasePrice() {
        return purchaseprice;
    }

    public String getStatus() {
        return status;
    }

    //All setter methods
    private void setStatus(String status) {
        this.status = status;
    }

    private void setSellingPrice(int sellingPrice) {
        this.sellingprice = sellingPrice;
    }

    private void setDate (String date) {
        this.date = date;
    }
}
