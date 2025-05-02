package dealership;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

import dealership.utils.Utils;

import static dealership.Login.carObjects;

public class Search {
    private static List<Car> sortedPrice = sortSalesAscendingByPrice();
    private static List<Car> sortedYear = sortSalesAscendingByYear();

    public static void searchByStatus() {
        Scanner scanner = new Scanner(System.in);
        //Get status(es) from user
        System.out.print("Enter status(es): ");
        //Stores user input as a string
        String input = scanner.nextLine();
        //Parses user input to an array of strings in lower case (ensures case-insensitive)
        String[] status = Utils.parseCSV(input.toLowerCase(), true);
        //Create a HashSet with all strings of user input
        Set<String> statusSearch = new HashSet<>();
        statusSearch.addAll(Arrays.asList(status));
        //Create list to store found cars
        List<Car> matched = new ArrayList<>();
        for(Car c : carObjects) {
            if(statusSearch.contains(c.getStatus().toLowerCase())) {
                matched.add(c);
            }
        }
        //If list 'found' is empty, no cars were found and user is notified
        if(matched.isEmpty()) {
            System.out.println("No cars were found.");
        }
        //Prints all matched cars
        else {
            matched.sort(Comparator.comparing(Car::getStatus));
            System.out.printf("Found %d cars: \n", matched.size());
            for(Car c : matched) {
                System.out.printf("%s\n", c.toCSV());
            }
        }
    }

    public static void searchByVin() {
        Scanner scanner = new Scanner(System.in);
        //Get VIN(s) from user
        System.out.print("Enter VIN(s): ");
        //Stores user input as a string
        String input = scanner.nextLine();
        //Parses user input to an array of strings in lower case (ensures case-insensitive)
        String[] vins = Utils.parseCSV(input.toLowerCase(), true);
        //Create a HashMap with VIN as key and Car as value
        Map<String,Car> vinSearch = new HashMap<>();
        //Put all car objects into the Map with VIN in lower case (ensures case-insensitive)
        for(Car c : carObjects) {
            vinSearch.put(c.getVIN().toLowerCase(), c);
        }
        //Create list to store found cars
        List<Car> matched = new ArrayList<>();
        //Iterates through user input
        for(String s : vins) {
            //If map contains a car with specified VIN, car is added to list 'found'
            if(vinSearch.containsKey(s)) {
                matched.add(vinSearch.get(s));
            }
        }
        //If list 'found' is empty, no cars were found and user is notified
        if(matched.isEmpty()) {
            System.out.println("No cars were found.");
        }
        //Prints all matched cars
        else {
            matched.sort(Comparator.comparing(Car::getVIN));
            System.out.printf("Found %d cars: \n", matched.size());
            for(Car c : matched) {
                System.out.printf("%s\n", c.toCSV());
            }
        }
    }

    public static void searchByColor() {
        Scanner scanner = new Scanner(System.in);
        //Get color(s) from user
        System.out.print("Enter color(s): ");
        //Stores user input as a string
        String input = scanner.nextLine();
        //Parses user input to an array of strings in lower case (ensures case-insensitive)
        String[] color = Utils.parseCSV(input.toLowerCase(), true);
        //Create a HashSet with all strings of user input
        Set<String> colorSearch = new HashSet<>();
        colorSearch.addAll(Arrays.asList(color));
        //Create list to store found cars
        List<Car> matched = new ArrayList<>();
        for(Car c : carObjects) {
            if(colorSearch.contains(c.getColor().toLowerCase())) {
                matched.add(c);
            }
        }
        //If list 'found' is empty, no cars were found and user is notified
        if(matched.isEmpty()) {
            System.out.println("No cars were found.");
        }
        //Prints all matched cars
        else {
            matched.sort(Comparator.comparing(Car::getColor));
            System.out.printf("Found %d cars: \n", matched.size());
            for(Car c : matched) {
                System.out.printf("%s\n", c.toCSV());
            }
        }
    }

    public static void searchByYear() {
        Scanner scanner = new Scanner(System.in);
        //Get year range from user
        System.out.print("Year range: ");
        //Stores user input as a string
        String input = scanner.nextLine();
        //Parses user input to an array of strings in lower case (ensures case-insensitive)
        String[] years = Utils.parseCSV(input.toLowerCase(), true);
        //Returns entire list of cars sorted by year if user inputs no bounds
        if(years.length == 0) {
            System.out.printf("Found %d cars: \n", sortedYear.size());
            for(Car c : sortedYear) {
                System.out.printf("%s\n", c.toCSV());
            }
            return;
        }
        //Parses user input in start and end year integer variables
        int startYear = 0;
        int endYear = 0;
        try {
            if (!years[0].isEmpty()) {
                startYear = Integer.parseInt(years[0]);
            }
            if (years.length > 1 && !years[1].isEmpty()) {
                endYear = Integer.parseInt(years[1]);
            }
        }
        //If input can't be parsed, user is notified and returned to search menu
        catch (NumberFormatException e) {
            System.out.print("Invalid input. Years must be a whole numeric value. ");
        }
        //Create list to store found cars
        List<Car> matched = new ArrayList<>();
        //Has start year, but no end year
        if(startYear != 0 && endYear == 0) {
            for(Car c : sortedYear) {
                if(c.getYear() >= startYear) {
                    matched.add(c);
                }
            }
        }
        //No start year, but has end year
        else if(startYear == 0 && endYear != 0) {
            for(Car c : sortedYear) {
                if(c.getYear() <= endYear) {
                    matched.add(c);
                }
            }
        }
        //Has both end year and start year
        else {
            for(Car c : sortedYear) {
                if(c.getYear() <= endYear && c.getYear() >= startYear) {
                    matched.add(c);
                }
            }
        }
        //If list 'found' is empty, no cars were found and user is notified
        if(matched.isEmpty()) {
            System.out.println("No cars were found.");
        }
        //Prints all matched cars
        else {
            System.out.printf("Found %d cars: \n", matched.size());
            for(Car c : matched) {
                System.out.printf("%s\n", c.toCSV());
            }
        }
    }

    public static void searchByMake() {
        Scanner scanner = new Scanner(System.in);
        //Get make(s) from user
        System.out.print("Enter make(s): ");
        //Stores user input as a string
        String input = scanner.nextLine();
        //Parses user input to an array of strings in lower case (ensures case-insensitive)
        String[] make = Utils.parseCSV(input.toLowerCase(), true);
        //Create a HashSet with all strings of user input
        Set<String> makeSearch = new HashSet<>();
        makeSearch.addAll(Arrays.asList(make));
        //Create list to store found cars
        List<Car> matched = new ArrayList<>();
        for(Car c : carObjects) {
            if(makeSearch.contains(c.getMake().toLowerCase())) {
                matched.add(c);
            }
        }
        //If list 'found' is empty, no cars were found and user is notified
        if(matched.isEmpty()) {
            System.out.println("No cars were found.");
        }
        //Prints all matched cars
        else {
            matched.sort(Comparator.comparing(Car::getMake));
            System.out.printf("Found %d cars: \n", matched.size());
            for(Car c : matched) {
                System.out.printf("%s\n", c.toCSV());
            }
        }
    }

    public static void searchByPrice() {
        Scanner scanner = new Scanner(System.in);
        //Get price range from user
        System.out.print("Price range: ");
        //Stores user input as a string
        String input = scanner.nextLine();
        //Parses user input to an array of strings in lower case (ensures case-insensitive)
        String[] prices = Utils.parseCSV(input.toLowerCase(), true);
        //Returns entire list of cars sorted by price if user inputs no bounds
        if(prices.length == 0) {
            System.out.printf("Found %d cars: \n", sortedPrice.size());
            for(Car c : sortedPrice) {
                System.out.printf("%s\n", c.toCSV());
            }
            return;
        }
        //Parses user input in low and high prices integer variables
        int lowPrice = 0;
        int highPrice = 0;
        try {
            if (!prices[0].isEmpty()) {
                lowPrice = Integer.parseInt(prices[0]);
            }
            if (prices.length > 1 && !prices[1].isEmpty()) {
                highPrice = Integer.parseInt(prices[1]);
            }
        }
        //If input can't be parsed, user is notified and returned to search menu
        catch (NumberFormatException e) {
            System.out.print("Invalid input. Prices must be a whole numeric value. ");
        }
        //Create list to store found cars
        List<Car> matched = new ArrayList<>();
        //Has low price, but no high price
        if(lowPrice != 0 && highPrice == 0) {
            for(Car c : sortedPrice) {
                if(c.getListPrice() >= lowPrice) {
                    matched.add(c);
                }
            }
        }
        //No low price, but has high price
        else if(lowPrice == 0 && highPrice != 0) {
            for(Car c : sortedPrice) {
                if(c.getListPrice() <= highPrice) {
                    matched.add(c);
                }
            }
        }
        //Has both low and high price
        else {
            for(Car c : sortedPrice) {
                if(c.getListPrice() <= highPrice && c.getListPrice() >= lowPrice) {
                    matched.add(c);
                }
            }
        }
        //If list 'found' is empty, no cars were found and user is notified
        if(matched.isEmpty()) {
            System.out.println("No cars were found.");
        }
        //Prints all matched cars
        else {
            System.out.printf("Found %d cars: \n", matched.size());
            for(Car c : matched) {
                System.out.printf("%s\n", c.toCSV());
            }
        }
    }

    public static List<Car> sortSalesAscendingByYear() {
        //Creates a copy of list carObjects
        List<Car> cars = new ArrayList<>(carObjects);
        //Sorts the list of cars in range by their year ascendingly
        cars.sort(Comparator.comparing(Car::getYear));
        return cars;
    }

    public static List<Car> sortSalesAscendingByPrice() {
        //Creates a copy of list carObjects
        List<Car> cars = new ArrayList<>(carObjects);
        //Sorts the list of cars in range by their list price ascendingly
        cars.sort(Comparator.comparing(Car::getListPrice));
        return cars;
    }
}
