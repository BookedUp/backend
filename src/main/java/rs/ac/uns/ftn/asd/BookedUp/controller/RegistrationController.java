package rs.ac.uns.ftn.asd.BookedUp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.HostDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
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

    @Autowired
    private HostService hostService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody HostDTO userDTO) {
        Host host  = HostMapper.toEntity(userDTO);
        return registrationService.register(host);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
