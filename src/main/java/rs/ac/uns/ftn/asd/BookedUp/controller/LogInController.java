package rs.ac.uns.ftn.asd.BookedUp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.LogInDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.TokenDTO;
import rs.ac.uns.ftn.asd.BookedUp.security.jwt.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class LogInController {
    private static final Logger logger = LoggerFactory.getLogger(LogInController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping( value ="/login", consumes = "application/json")
    public ResponseEntity<TokenDTO> login(@RequestBody LogInDTO loginDto) {

        UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                loginDto.getPassword());
        Authentication auth = authenticationManager.authenticate(authReq);

        SecurityContext sc = SecurityContextHolder.getContext();
        sc.setAuthentication(auth);

        UserDetails userDetail = userDetailsService.loadUserByUsername(loginDto.getEmail());

        String token = jwtTokenUtil.generateToken(userDetail);
        TokenDTO tokenDto = new TokenDTO();
        tokenDto.setToken(token);

        return new ResponseEntity<>(tokenDto, HttpStatus.OK);
    }
}
