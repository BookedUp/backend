package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.repository.IUserRepository;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> ret = userRepository.findByUsername(username);
        if (!ret.isEmpty() ) {
            return org.springframework.security.core.userdetails.User.withUsername(username).password(ret.get().getPassword()).roles(ret.get().getRole().toString()).build();
        }
        throw new UsernameNotFoundException("User not found with this username: " + username);
    }
}
