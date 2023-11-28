package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReservationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.UserMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class UserService implements IUserService{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;
    @Override
    public Collection<UserDTO> getAll() {
        Collection<User> users = userRepository.getAll();
        Collection<UserDTO> userDTOS = new ArrayList<>();

        for (User user : users) {
            UserDTO userDTO = userMapper.toDto(user);
            userDTOS.add(userDTO);
        }

        return userDTOS;
    }

    @Override
    public UserDTO getById(Long id) {
        User user = userRepository.getById(id);
        return userMapper.toDto(user);
    }

    @Override
    public UserDTO create(UserDTO userDto) throws Exception {
        if (userDto.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        User user = userMapper.toEntity(userDto);
        User savedUser = userRepository.create(user);
        return userMapper.toDto(savedUser);
    }

    @Override
    public UserDTO update(UserDTO userDto) throws Exception {
        User user = userMapper.toEntity(userDto);
        User userToUpdate = userRepository.getById(user.getId());
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
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
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
