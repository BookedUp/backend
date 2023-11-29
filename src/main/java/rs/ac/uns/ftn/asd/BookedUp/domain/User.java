package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Integer phone;

    private String email;
    private String password;

    private Role role;
    private boolean isBlocked;


    public void copyValues(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.password = user.getPassword();
        this.isBlocked = user.isBlocked();
    }

    // Method to register a new user
    public void register(String email, String password, String firstName, String lastName, int phone, Role role, Address address) {
        // TODO: Implement method to register a new admin
    }

    // Method to authenticate the user
    public boolean login(String enteredUsername, String enteredPassword) {
        // TODO: Implement method to authenticate the admin
        // Return true if authentication is successful, false otherwise
        return false;
    }

    // Method to update user account information
    public void updateAccount(String newData) {
        // TODO: Implement method to update admin account information
    }

    // Method to search for accommodations based on some criteria
    public List<Accommodation> searchAccommodations(String searchCriteria) {
        // TODO: Implement method to search for accommodations
        // Return a list of accommodations based on the search criteria
        return null;
    }

    public void copyValuesFromDTO(UserDTO userDTO) {
        if (userDTO != null) {
            this.firstName = userDTO.getFirstName();
            this.lastName = userDTO.getLastName();
            this.address = userDTO.getAddress();
            this.phone = userDTO.getPhone();
            this.email = userDTO.getEmail();
            this.password = userDTO.getPassword();
        }
    }
}
