package dealership;

import java.util.ArrayList;
import java.util.Scanner;

public class Validator {
    //Takes user input until a valid integer is given as a choice and returns said integer
    public static int validateChoice(int lower, int upper) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while(true) {
            //Checks if user inputs an integer between given lower and upper limits
            if(scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if(choice >= lower && choice <= upper) {
                    break;
                }
                //Any invalid input allows the user to give input again
                else {
                    Menu.printFooter();
                }
            }
            //If user did not input an integer, gives user the change to give input again
            else {
                Menu.printFooter();
                scanner.next();
            }
        }
        //Returns any valid integer between lower and upper limits
        return choice;
    }

    public static boolean validateVIN(String vin, ArrayList<Car> carObjects) {
        //Checks if the VIN already exists in the system
        for(Car c : carObjects) {
            if(c.getVIN().equals(vin)) {
                System.out.print("Invalid input. VIN matches an existing car in the system. ");
                return false;
            }
        }
        //Checks if the VIN input is empty
        if(vin.trim().isEmpty()) {
            System.out.print("Invalid input. VIN input is empty. ");
            return false;
        }
        //Checks if VIN length is valid
        if(vin.length() < 6 || vin.length() > 17) {
            System.out.print("Invalid input. VIN must be between 6 and 17 characters. ");
            return false;
        }
        //Checks if VIN is only alphanumeric
        if (!vin.matches("[a-zA-Z0-9]+")) {
            System.out.print("Invalid input. VIN must contain only alphanumeric characters. ");
            return false;
        }
        return true;
    }

    public static boolean validateMake(String make) {
        //Checks if make input is empty
        if(make.trim().isEmpty()) {
            System.out.print("Invalid input. Make input is empty. ");
            return false;
        }
        //Checks if make length is valid
        if(make.length() > 20) {
            System.out.print("Invalid input. Make must be less than 20 characters. ");
            return false;
        }
        return true;
    }

    public static boolean validateModel(String model) {
        //Checks if model input is empty
        if(model.trim().isEmpty()) {
            System.out.print("Invalid input. Model input is empty. ");
            return false;
        }
        //Checks if model length is valid
        if(model.length() > 20) {
            System.out.print("Invalid input. Model must be less than 20 characters. ");
            return false;
        }
        return true;
    }

    public static boolean validateYear(String year) {
        //Checks if year is an integer value
        int numYear;
        try {
            numYear = Integer.parseInt(year);
        }
        catch (NumberFormatException e) {
            System.out.print("Invalid input. Year must be a whole numeric value. ");
            return false;
        }
        //Checks if year is within the valid range
        if(numYear > 2025 || numYear < 1900) {
            System.out.print("Invalid input. Year must be between 1900 and 2025. ");
            return false;
        }
        return true;
    }

    public static boolean validateMileage(String mileage) {
        //Checks if mileage is an integer value
        int numMileage;
        try {
            numMileage = Integer.parseInt(mileage);
        }
        catch (NumberFormatException e) {
            System.out.print("Invalid input. Mileage must be a whole numeric value. ");
            return false;
        }
        //Checks if mileage is within the valid range
        if (numMileage < 1 || numMileage > 1000000) {
            System.out.print("Invalid input. Mileage must be between 1 and 1,000,000. ");
            return false;
        }
        return true;
    }

    public static boolean validateColor(String color) {
        //Checks if color input is empty
        if(color.trim().isEmpty()) {
            System.out.print("Invalid input. Color input is empty. ");
            return false;
        }
        //Checks if color length is valid
        if(color.length() > 20) {
            System.out.print("Invalid input. Color must be less than 20 characters. ");
            return false;
        }
        return true;
    }

    public static boolean validatePrice(String price) {
        //Checks if list price is an integer value
        int numPrice;
        try {
            numPrice = Integer.parseInt(price);
        }
        catch (NumberFormatException e) {
            System.out.print("Invalid input. List price must be a whole numeric value. ");
            return false;
        }
        //Checks if list price is within the valid range
        if (numPrice < 1 || numPrice > 1000000) {
            System.out.print("Invalid input. List price must be between 1 and 1,000,000. ");
            return false;
        }
        return true;
    }

    public static boolean validateYearRange(String[] years) {
        //Checks if year is an integer value
        int startYear;
        int endYear;
        try {
            startYear = Integer.parseInt(years[0]);
            endYear = Integer.parseInt(years[1]);
        }
        catch (NumberFormatException e) {
            System.out.print("Invalid input. Years must be a whole numeric value. ");
            return false;
        }
        //Checks if year is within the valid range
        if((startYear > 2025 || startYear < 1900) && (endYear > 2025 || endYear < 1900)) {
            System.out.print("Invalid input. Years must be between 1900 and 2025. ");
            return false;
        }
        //Checks if start year is less than end year
        else if(startYear > endYear) {
            System.out.print("Invalid input. Start year must be less than end year. ");
            return false;
        }
        //Checks that there are only two inputs
        else if(years.length > 2) {
            System.out.print("Invalid input. Please input range as: start year, end year. ");
            return false;
        }
        return true;
    }
}
