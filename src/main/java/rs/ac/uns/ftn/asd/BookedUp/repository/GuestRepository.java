package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Guest;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class GuestRepository implements IGuestRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Guest> guests = new ConcurrentHashMap<Long, Guest>();
    @Override
    public Collection<Guest> getAll() {
        return this.guests.values();
    }

    @Override
    public Guest getById(Long id) {
        return this.guests.get(id);
    }

    @Override
    public Guest create(Guest guest) throws Exception {
        Long id = guest.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            guest.setId(id);
        }

        this.guests.put(id, guest);
        return guest;
    }

    @Override
    public Guest update(Guest guest) throws Exception {
        Long id = guest.getId();

        if (id != null) {
            this.guests.put(id, guest);
        }

        return guest;
    }

    @Override
    public void delete(Long id) {
        this.guests.remove(id);
    }
}
