package dealership;

import dealership.utils.Utils;

import java.util.List;

public class User {
    //Initializes the properties of a user
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private String username;
    private String password;

    //Constructor that parses a CSV line into properties of a new user object
    public User(String userData) {
        String[] values = Utils.parseCSV(userData, true);
        this.id = values[0];
        this.firstName = values[1];
        this.lastName = values[2];
        this.email = values[3];
        this.phone = values[4];
        this.role = values[5];
        this.username = values[6];
        this.password = values[7];
    }

    //All getter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}