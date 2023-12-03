package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Data
@SuperBuilder
@NoArgsConstructor

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", unique = true)
    private Address address;

    @Column
    private Integer phone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private boolean isBlocked;

    @OneToMany(mappedBy = "to", cascade = CascadeType.ALL)
    private List<Notification> notifications;


    public void copyValues(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.address = user.getAddress();
        this.phone = user.getPhone();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.password = user.getPassword();
        this.isBlocked = user.isBlocked();
        this.notifications = user.getNotifications();
    }

}
