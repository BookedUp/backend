package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.repository.IUserRepository;

import java.util.Collection;

@Service
public class UserService implements ServiceInterface<User>{
    @Autowired
    private IUserRepository repository;

    @Override
    public Collection<User> getAll() {
        Collection<User> users = repository.findAll();
        return users;
    }

    @Override
    public User getById(Long id) {
        User user = repository.findById(id).orElse(null);
        return user;
    }

    @Override
    public User create(User user) throws Exception {
        if (user.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        System.out.println(user.getAddress().getId());
        return repository.save(user);
    }

//    @Override
//    public User update(User user) throws Exception {
//        User userToUpdate = repository.findById(user.getId()).orElse(null);
//        if (userToUpdate == null) {
//            throw new Exception("Trazeni entitet nije pronadjen.");
//        }
//        userToUpdate.setFirstName(user.getFirstName());
//        userToUpdate.setLastName(user.getLastName());
//        userToUpdate.setAddress(user.getAddress());
//        userToUpdate.setEmail(user.getEmail());
//        userToUpdate.setPassword(user.getPassword());
//        userToUpdate.setPhone(user.getPhone());
//        //userToUpdate.setRole(user.getRole());
//        userToUpdate.setVerified(user.isVerified());
//        userToUpdate.setProfilePicture(user.getProfilePicture());
//        userToUpdate.setLastPasswordResetDate(user.getLastPasswordResetDate());
//        userToUpdate.setBlocked(user.isBlocked());
//
//        userToUpdate.setAuthority(user.getAuthority());
//        userToUpdate.setProfilePicture(user.getProfilePicture());
//        userToUpdate.setVerified(user.isVerified());
//        userToUpdate.setLastPasswordResetDate(user.getLastPasswordResetDate());
//
//        System.out.println(userToUpdate.getLastPasswordResetDate());
//        for (Authority authority : userToUpdate.getAuthority()){
//            System.out.println(authority.getRole());
//        }
//
//        return repository.save(userToUpdate);
//
//    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public void blockUser(Long id) throws Exception{
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }

        user.setBlocked(true);

        repository.save(user);
    }

    public void unblockUser(Long id) throws Exception {
        User user = repository.findById(id).orElse(null);
        if (user == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }

        user.setBlocked(false);

        repository.save(user);

    }

    public boolean authenticateUser(String email, String password) {
        return true;
    }

    public boolean registerGuest(UserDTO userDTO) {
        //make sure to put user.role to guest
        return true;
    }

    public boolean registerHost(UserDTO userDTO) {
        //make sure to put user.role to host
        return false;
    }
}
