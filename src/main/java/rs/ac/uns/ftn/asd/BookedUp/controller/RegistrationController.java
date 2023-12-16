package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.domain.enums.Role;
import rs.ac.uns.ftn.asd.BookedUp.dto.GuestDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.AddressMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.GuestMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.HostMapper;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.service.HostService;
import rs.ac.uns.ftn.asd.BookedUp.service.RegistrationService;

import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService registrationService;


//    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public String register(@RequestBody HostDTO userDTO) {
//        Host host  = HostMapper.toEntity(userDTO);  //moras dodati castovanje
//        return registrationService.register(host);
//    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody UserDTO userDTO) {
        if (userDTO.getRole() == Role.HOST) {
            Host host = new Host();
            //host.setId(userDTO.getId());
//            Host guest = HostMapper.toEntity((HostDTO) userDTO);
            host.setFirstName(userDTO.getFirstName());
            host.setLastName(userDTO.getLastName());
            host.setAddress(AddressMapper.toEntity(userDTO.getAddress()));
            host.setPhone(userDTO.getPhone());
            host.setEmail(userDTO.getEmail());
            host.setPassword(userDTO.getPassword());
            host.setRole(userDTO.getRole());


            return registrationService.register(host);
        } else if (userDTO.getRole() == Role.GUEST) {
            Guest guest = new Guest();
            //host.setId(userDTO.getId());
//            Host guest = HostMapper.toEntity((HostDTO) userDTO);
            guest.setFirstName(userDTO.getFirstName());
            guest.setLastName(userDTO.getLastName());
            guest.setAddress(AddressMapper.toEntity(userDTO.getAddress()));
            guest.setPhone(userDTO.getPhone());
            guest.setEmail(userDTO.getEmail());
            guest.setPassword(userDTO.getPassword());
            guest.setRole(userDTO.getRole());

            return registrationService.register(guest);
        } else {
            throw new IllegalArgumentException("Nepodržana uloga: " + userDTO.getRole());
        }
    }

    @GetMapping()
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
