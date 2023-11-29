package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

@Getter
@Setter
@AllArgsConstructor
@Data
@SuperBuilder
@NoArgsConstructor

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
