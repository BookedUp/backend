package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.User;

import java.util.Collection;

public interface IUserRepository {

    Collection<User> getAll();
    User create(User user);
    User getById(Long id);
    User update(User user);
    void delete(Long id);


}
