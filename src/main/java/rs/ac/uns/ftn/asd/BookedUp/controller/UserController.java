package rs.ac.uns.ftn.asd.BookedUp.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.AccommodationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.LogInDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.service.UserService;

import java.util.Collection;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    /** url: /api/users GET*/
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<UserDTO>> getUsers() {
        Collection<UserDTO> userDTOS = userService.getAll();
        if (userDTOS.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<UserDTO>>(userDTOS, HttpStatus.OK);
    }

    /** url: /api/users/1 GET*/
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> getUser(@PathVariable("id") Long id) {
        UserDTO userDto = userService.getById(id);

        if (userDto == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
    }

    /** url: /api/users POST*/
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDto) throws Exception {
        UserDTO createdUserDTO = null;

        try {
            createdUserDTO = userService.create(userDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new UserDTO(),HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }
    /** url: /api/users/1 PUT*/
    @PreAuthorize("hasRole('GUEST')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDto, @PathVariable Long id)
            throws Exception {
        UserDTO userForUpdate = userService.getById(id);
        if (userForUpdate == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userForUpdate.copyValues(userDto);
        UserDTO updatedUser = userService.update(userForUpdate);

        if (updatedUser == null) {
            return new ResponseEntity<UserDTO>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<UserDTO>(updatedUser, HttpStatus.OK);
    }


    /** url: /api/users/1 DELETE*/
    @PreAuthorize("hasAnyRole('HOST', 'GUEST')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        try {
            userService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    private boolean validateUserDTO(UserDTO userDto) {
        return true;
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
    public ResponseEntity<String> registerGuest(@RequestBody UserDTO userDTO) {
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
    public ResponseEntity<String> registerHost(@RequestBody UserDTO userDTO) {
        // Your registration logic for hosts here
        boolean registrationSuccessful = userService.registerHost(userDTO);

        if (registrationSuccessful) {
            return ResponseEntity.ok("Host registration successful");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Host registration failed");
        }
    }

}
