package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class AccommodationRepository implements IAccommodationRepository{
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Accommodation> accommodations = new ConcurrentHashMap<Long, Accommodation>();
    @Override
    public Collection<Accommodation> getAll() {
        return this.accommodations.values();
    }

    @Override
    public Accommodation getById(Long id) {
        return this.accommodations.get(id);
    }

    @Override
    public Accommodation create(Accommodation accommodation) throws Exception {
        Long id = accommodation.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            accommodation.setId(id);
        }

        this.accommodations.put(id, accommodation);
        return accommodation;
    }

    @Override
    public Accommodation update(Accommodation accommodation) throws Exception {
        Long id = accommodation.getId();

        if (id != null) {
            this.accommodations.put(id, accommodation);
        }

        return accommodation;
    }

    @Override
    public void delete(Long id) {
        this.accommodations.remove(id);
    }
}
