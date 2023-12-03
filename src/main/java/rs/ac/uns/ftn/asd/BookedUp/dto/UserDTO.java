package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;

import java.util.List;

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
    private List<NotificationDTO> notifications;

    public void copyValues(UserDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.isBlocked = dto.isBlocked();
        this.notifications = dto.getNotifications();
    }
}

