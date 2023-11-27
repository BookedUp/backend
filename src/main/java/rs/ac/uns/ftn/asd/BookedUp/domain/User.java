package rs.ac.uns.ftn.asd.BookedUp.domain;

import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;

import java.util.List;

public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Integer phone;

    private String email;
    private String password;

    private Role role;


    public User() {
    }

    public User(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void copyValues(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.password = user.getPassword();
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

    public void copyValuesFromDTO(UserDetailedInDTO userDTO) {
        if (userDTO != null) {
            this.firstName = userDTO.getFirstName();
            this.lastName = userDTO.getLastName();
            this.address = userDTO.getAddress();
            this.phone = userDTO.getPhone();
            this.email = userDTO.getEmail();
            this.password = userDTO.getPassword();
            this.role = userDTO.getRole();
        }
    }
}
