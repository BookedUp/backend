package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.LogInDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.UserService;

import java.util.Collection;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    /** url: /api/users GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> getUsers() {
        Collection<User> users = userService.getAll();
        return new ResponseEntity<Collection<User>>(users, HttpStatus.OK);
    }

    /** url: /api/users/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userService.getById(id);

        if (user == null) {
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    /** url: /api/users POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody User user) throws Exception {
        User savedUser = userService.create(user);
        return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
    }

    /** url: /api/users/login POST*/
    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody LogInDTO loginDTO) {
        boolean loginSuccessful = userService.authenticateUser(loginDTO.getEmail(), loginDTO.getPassword());

        if (loginSuccessful) {
            // You might return a token or user details upon successful login
            // For simplicity, this example returns a success message
            return ResponseEntity.ok("Login successful");
        } else {
            // Return unauthorized status for failed login
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    /** url: /api/users/register-guest POST*/
    @PostMapping("/register-guest")
    public ResponseEntity<String> registerGuest(@RequestBody UserDetailedInDTO userDTO) {
        // Your registration logic for guests here
        boolean registrationSuccessful = userService.registerGuest(userDTO);

        if (registrationSuccessful) {
            return ResponseEntity.ok("Guest registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Guest registration failed");
        }
    }

    /** url: /api/users/register-host POST*/
    @PostMapping("/register-host")
    public ResponseEntity<String> registerHost(@RequestBody UserDetailedInDTO userDTO) {
        // Your registration logic for hosts here
        boolean registrationSuccessful = userService.registerHost(userDTO);

        if (registrationSuccessful) {
            return ResponseEntity.ok("Host registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Host registration failed");
        }
    }

    /** url: /api/users/1 DELETE*/
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
