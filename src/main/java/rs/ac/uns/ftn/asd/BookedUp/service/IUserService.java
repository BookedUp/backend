package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.User;

import java.util.Collection;

public interface IUserService {

    Collection<User> getAll();
    User getById(Long id);
    User create(User user) throws Exception;
    User update(User user) throws Exception;
    void delete(Long id);


}
