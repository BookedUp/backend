package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDetailedInDTO;
import rs.ac.uns.ftn.asd.BookedUp.repository.UserRepository;

import java.util.Collection;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public Collection<User> getAll() {
        Collection<User> users = userRepository.getAll();
        return users;
    }

    @Override
    public User getById(Long id) {
        User user = userRepository.getById(id);
        return user;
    }

    @Override
    public User create(User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        User savedUser = userRepository.create(user);
        return savedUser;
    }

    @Override
    public User update(User user) throws Exception {
        User userToUpdate = getById(user.getId());
        if (userToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        userToUpdate.setFirstName(user.getFirstName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setAddress(user.getAddress());
        userToUpdate.setEmail(user.getEmail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setPhone(user.getPhone());
        userToUpdate.setRole(user.getRole());

        User updatedUser = userRepository.create(userToUpdate);
        return updatedUser;
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    public boolean authenticateUser(String email, String password) {
        return true;
    }

    public boolean registerGuest(UserDetailedInDTO userDTO) {
        //make sure to put user.role to guest
        return true;
    }

    public boolean registerHost(UserDetailedInDTO userDTO) {
        //make sure to put user.role to host
        return false;
    }
}
