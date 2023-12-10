package rs.ac.uns.ftn.asd.BookedUp.domain;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.Role;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column
    private Integer phone;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isBlocked;

    @Column(nullable = false)
    private boolean active = true;

    @Column(nullable = false)
    private boolean verified;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id", nullable = true, unique = false)
    private Photo profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", insertable = false, updatable = false)
    private Role role;

    @Column(name = "last_password_reset_date",nullable = true)
    private Timestamp lastPasswordResetDate;


    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        Timestamp now = new Timestamp(new Date().getTime());
        this.setLastPasswordResetDate(now);
        this.password = password;
    }


//    public void copyValues(User user) {
//        this.firstName = user.getFirstName();
//        this.lastName = user.getLastName();
//        this.address = user.getAddress();
//        this.phone = user.getPhone();
//        this.email = user.getEmail();
//        //this.role = user.getRole();
//        this.active = user.isActive();
//        this.password = user.getPassword();
//        this.isBlocked = user.isBlocked();
////        this.notifications = user.getNotifications();
//        this.authority = user.getAuthority();
//        this.verified = user.isVerified();
//        this.profilePicture = user.getProfilePicture();
//        this.lastPasswordResetDate = user.getLastPasswordResetDate();
//    }


}
