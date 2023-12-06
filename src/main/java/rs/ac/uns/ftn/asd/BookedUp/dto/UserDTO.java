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
    private AddressDTO address;
    private Integer phone;
    private String email;
    private String password;
    private boolean isBlocked;
    private boolean verified;
    private boolean active;

    private PhotoDTO profilePicture;
    private List<NotificationDTO> notifications;


    public UserDTO(Long id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public UserDTO(String firstName, String lastName, String email, String password, PhotoDTO profilePicture) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
    }

    public UserDTO(Long id, String firstName, String lastName, AddressDTO address, Integer phone, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.active = true;
        this.verified = true;
    }

    public UserDTO(Long id, String firstName, String lastName, AddressDTO address, Integer phone, String email, String password, boolean isBlocked, boolean verified, PhotoDTO profilePicture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.isBlocked = isBlocked;
        this.verified = verified;
        this.profilePicture = profilePicture;
    }

    public void copyValues(UserDTO dto) {
        this.firstName = dto.getFirstName();
        this.lastName = dto.getLastName();
        this.address = dto.getAddress();
        this.phone = dto.getPhone();
        this.email = dto.getEmail();
        this.password = dto.getPassword();
        this.isBlocked = dto.isBlocked();
        this.notifications = dto.getNotifications();
        this.active = dto.isActive();
    }
}

