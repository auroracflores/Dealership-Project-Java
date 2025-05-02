package dealership;

import dealership.utils.Utils;

import java.time.LocalDate;
import java.util.*;

import static dealership.Login.salesObjects;

public class Sale {
    private LocalDate date;
    private String VIN;
    private int sellingPrice;
    private String buyerName;
    private String salesName;

    public Sale(String salesData) {
        String[] values = Utils.parseCSV(salesData, true);
        this.VIN = values[0];
        this.date = LocalDate.parse(values[1]);
        this.sellingPrice = Integer.parseInt(values[2]);
        this.buyerName = values[3];
        this.salesName = values[4];
    }

    public String toCSV() {
        return String.format("%s, %s, %d, %s, %s",
                this.VIN, this.date, this.sellingPrice, this.buyerName, this.salesName);
    }

    public static void retrieveSales() {
        //Initializes start and end LocalDate variables
        LocalDate start;
        LocalDate end;
        Scanner scanner = new Scanner(System.in);
        //Gets the start date from the user
        System.out.print("Start Date: ");
        String startDate = scanner.nextLine().trim();
        //Gets the end date from the user
        System.out.print("End Date: ");
        String endDate = scanner.nextLine().trim();
        //Assigns value to start variable based off user input
        if(startDate.isEmpty()) {start = null;}
        else {start = LocalDate.parse(startDate);}
        //Assigns value to end variable based off user input
        if(endDate.isEmpty()) {end = null;}
        else {end = LocalDate.parse(endDate);}
        List<Sale> sorted = sortSalesAscendingByDate(start, end);
        //Prints out the list of sales in range in CSV format
        System.out.printf("Sales between %s and %s\n", startDate, endDate);
        System.out.println("Date, VIN, Purchase Price, Selling Price, Buyer Name, Salesperson");
        for(Sale sale : sorted) {
            System.out.println(String.format("%s, %s, %d, %s, %s",
                    sale.date, sale.VIN, sale.sellingPrice, sale.buyerName, sale.salesName));
            //Car.findCarByVIN(sales.VIN).getPurchasePrice()
        }
    }

    public static List<Sale> sortSalesAscendingByDate(LocalDate start, LocalDate end) {
        //Creates a list to save sales objects within specified date range
        List<Sale> salesInRange = new ArrayList<>();
        //Adds sales objects that are in range based off user input in parameters
        if(start == null && end == null) {
            salesInRange.addAll(salesObjects);
        }
        else if(start == null) {
            for(Sale sale : salesObjects) {
                if(sale.getDate().compareTo(end) <= 0) {
                    salesInRange.add(sale);
                }
            }
        }
        else if(end == null) {
            for(Sale sale : salesObjects) {
                if(sale.getDate().compareTo(start) >= 0 && sale.getDate().compareTo(LocalDate.now()) <= 0) {
                    salesInRange.add(sale);
                }
            }
        }
        else {
            for(Sale sale : salesObjects) {
                if(sale.getDate().compareTo(start) >= 0 && sale.getDate().compareTo(end) <= 0) {
                    salesInRange.add(sale);
                }
            }
        }
        //Sorts the list of sales in range by their date
        salesInRange.sort(Comparator.comparing(Sale::getDate));
        return salesInRange;
    }

    public static void soldBy() {
        Scanner scanner = new Scanner(System.in);
        //Gets the salesperson's name from the user
        System.out.print("Enter a name: ");
        //Saves the user input as all lowercase to compare as case-insensitive
        String userInput = scanner.nextLine().toLowerCase();
        //Create a list for the properly sorted sales
        List<Sale> sorted = new ArrayList<>();
        //Create a list for all matching salespersons names
        List<String> salespersons = new ArrayList<>();
        //Finds all sales with matching salespersons names and adds them to sorted
        for(Sale sale : salesObjects) {
            if(sale.getSalesName().toLowerCase().contains(userInput)) {
                sorted.add(sale);
            }
        }
        //If sorted is empty, no matches were found and the user is notified
        if(sorted.isEmpty()) {
            System.out.println("No matches to the given name were found in the sales record.");
            return;
        }
        //Sorts the list first by sales date descendingly, then by sales name ascendingly
        sorted.sort(Comparator.comparing(Sale::getDate).reversed());
        sorted.sort(Comparator.comparing(Sale::getSalesName));
        //Finds all unique salespersons names and adds them to list salespersons
        for(Sale sale : sorted) {
            if (!salespersons.contains(sale.getSalesName())) {
                salespersons.add(sale.getSalesName());
            }
        }
        //Prints out header with the list of all matching salespersons names
        System.out.print("All sales made by ");
        System.out.println(String.join(", ", salespersons) + ":");
        //Prints out the sales records in sorted order
        for(Sale sale : sorted) {
            System.out.println(String.format("%s, %s, %d, %s, %s",
                    sale.getDate(), sale.getVIN(), sale.getSellingPrice(), sale.getBuyerName(), sale.getSalesName()));
        }
    }


    //All getter methods
    public LocalDate getDate() {
        return date;
    }
    public String getVIN() {
        return VIN;
    }
    public int getSellingPrice() {
        return sellingPrice;
    }
    public String getBuyerName() {
        return buyerName;
    }
    public String getSalesName() {
        return salesName;
    }

    //All setter methods
    public void setDate(LocalDate date) {
        this.date = date;
    }
    public void setVIN(String VIN) {
        this.VIN = VIN;
    }
    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }
    public void setSalesName(String salesName) {
        this.salesName = salesName;
    }
}
