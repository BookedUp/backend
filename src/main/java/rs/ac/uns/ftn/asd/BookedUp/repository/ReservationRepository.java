package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ReservationRepository implements IReservationRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Reservation> reservations = new ConcurrentHashMap<Long, Reservation>();
    @Override
    public Collection<Reservation> getAll() {
        return this.reservations.values();
    }

    @Override
    public Reservation create(Reservation reservation) {
        Long id = reservation.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            reservation.setId(id);
        }

        this.reservations.put(id, reservation);
        return reservation;
    }

    @Override
    public Reservation getById(Long id) {
        return this.reservations.get(id);
    }

    @Override
    public Reservation update(Reservation reservation) {
        Long id = reservation.getId();

        if (id != null) {
            this.reservations.put(id, reservation);
        }

        return reservation;
    }

    @Override
    public void delete(Long id) {
        this.reservations.remove(id);
    }
}
