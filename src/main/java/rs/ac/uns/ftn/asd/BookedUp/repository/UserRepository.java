package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository implements IUserRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, User> users = new ConcurrentHashMap<Long, User>();
    @Override
    public Collection<User> getAll() {
        return this.users.values();
    }

    @Override
    public User create(User user) {
        Long id = user.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            user.setId(id);
        }

        this.users.put(id, user);
        return user;
    }

    @Override
    public User getById(Long id) {
        return this.users.get(id);
    }

    @Override
    public User update(User user) {
        Long id = user.getId();

        if (id != null) {
            this.users.put(id, user);
        }

        return user;
    }

    @Override
    public void delete(Long id) {
        this.users.remove(id);
    }
}
