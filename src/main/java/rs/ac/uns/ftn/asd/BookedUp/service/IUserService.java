package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;

import java.util.Collection;

public interface IUserService {

    Collection<UserDTO> getAll();
    UserDTO getById(Long id);
    UserDTO create(UserDTO userDto) throws Exception;
    UserDTO update(UserDTO userDto) throws Exception;
    void delete(Long id);


    void blockUser(Long id) throws Exception;

    void unblockUser(Long id)throws Exception;
}
