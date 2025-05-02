package dealership;

import dealership.utils.DealershipDB;
import dealership.utils.Utils;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login {
    private static final ArrayList<User> userObjects = new ArrayList<>();
    public static List<String> inventory = new ArrayList<>();
    public static ArrayList<Car> carObjects = new ArrayList<>();
    public static List<String> sales = new ArrayList<>();
    public static ArrayList<Sale> salesObjects = new ArrayList<>();

    public static DealershipDB ddb;
    public static User user;

    public static void returnToLogin() throws IOException {
        loginMenu();
    }

    public static void loginMenu() throws IOException {
        ///Prints the login menu screen with all options
        Menu.printHeader("Login", "Welcome to Best Cars Dealership!");
        System.out.println("1. Manager");
        System.out.println("2. Salesperson");
        System.out.println("3. Exit");
        System.out.println("--------------------------");
        Menu.printFooter();
        int choice = dealership.Validator.validateChoice(1, 3);
        switch(choice) {
            case (1): {
                //Get username and password from user
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter username: ");
                String username = scanner.next();
                String password = Utils.readPasswordFromConsole("Enter password: ");
                String role = "manager";
                //Checks if there exists a user with given username, password, and role (manager)
                if(!userValidator(userObjects, username, password, role)) {
                    //If not, returns to the login menu
                    loginMenu();
                }
                //If there is a matching user, opens the manager menu
                dealership.Menu.managerMenu();
                return;
            }
            case (2): {
                //Get username and password from user
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter username: ");
                String username = scanner.next();
                String password = Utils.readPasswordFromConsole("Enter password: ");
                String role = "salesperson";
                //Checks if there exists a user with given username, password, and role (salesperson)
                if(!userValidator(userObjects, username, password, role)) {
                    //If not, returns to the login menu
                    loginMenu();
                }
                //If there is a matching user, opens the sales menu
                dealership.Menu.salesMenu();
                return;
            }
            case (3): {
                //Exits the program
                System.exit(0);
                break;
            }
        }
    }
    public static boolean userValidator(ArrayList<User> users, String username, String password, String role) {
        //Checks for username in stored user data
        for(User u : users) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password) && u.getRole().equals(role)) {
                //Sets public static User object to the matching user in the database
                user = u;
                System.out.printf("Successfully logged in as a %s.\n", role);
                return true;
            }
        }
        System.out.println("Login Failed. No matching username and password were found.\n");
        return false;
    }

    public static void main(String[] args) throws IOException {
        //WHERE THE PROGRAM STARTS
        //Validates database directory as given in the arguments
        if (args.length < 1) {
            System.err.println("No database directory detected. Please provide a valid path to the database directory.");
            System.exit(1);
        }
        //Sets public static object ddb to a DealershipDB object
        ddb = new DealershipDB(args[0]);
        //Sets public static lists
        List<String> users = ddb.loadUsers();
        inventory = ddb.loadInventory();
        sales = ddb.loadSales();
        //Store all user objects in an ArrayList
        for (String user : users) {
            User u = new User(user);
            userObjects.add(u);
        }
        //Store all car objects in an ArrayList
        for (String car : inventory) {
            Car c = new Car(car);
            carObjects.add(c);
        }
        //Store all sales objects in an ArrayList
        for (String sales : sales) {
            Sale s = new Sale(sales);
            salesObjects.add(s);
        }
        //Opens the login menu screen
        loginMenu();
    }
}