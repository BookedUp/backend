package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

@Getter
@Setter
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Address address;
    private Integer phone;
    private String email;
    private String password;
    private boolean isBlocked;

    public UserDTO(String firstName, String lastName, Address address, Integer phone, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public void copyValues(UserDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.isBlocked = dto.isBlocked();
    }
}

