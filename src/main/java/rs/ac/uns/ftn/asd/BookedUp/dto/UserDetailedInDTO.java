package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailedInDTO {
    private String firstName;
    private String lastName;
    private Address address; // Using the Address class here
    private Integer phone;
    private String email;
    private String password;
    private Role role;

    // Additional fields as needed

    public UserDetailedInDTO(String firstName, String lastName, Address address, Integer phone, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.role = role;
        // Initialize additional fields as needed
    }
}

