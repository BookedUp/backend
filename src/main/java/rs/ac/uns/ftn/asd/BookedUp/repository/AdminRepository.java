package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Admin;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class AdminRepository implements IAdminRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Admin> admins = new ConcurrentHashMap<Long, Admin>();
    @Override
    public Collection<Admin> getAll() {
        return this.admins.values();
    }

    @Override
    public Admin getById(Long id) {
        return this.admins.get(id);
    }

    @Override
    public Admin create(Admin guest) throws Exception {
        Long id = guest.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            guest.setId(id);
        }

        this.admins.put(id, guest);
        return guest;
    }

    @Override
    public Admin update(Admin guest) throws Exception {
        Long id = guest.getId();

        if (id != null) {
            this.admins.put(id, guest);
        }

        return guest;
    }

    @Override
    public void delete(Long id) {this.admins.remove(id);
    }
}
