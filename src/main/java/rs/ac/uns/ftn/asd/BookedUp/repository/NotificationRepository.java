package rs.ac.uns.ftn.asd.BookedUp.repository;

import org.springframework.stereotype.Repository;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.domain.Reservation;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NotificationRepository implements INotificationRepository {
    private static AtomicLong counter = new AtomicLong();
    private final ConcurrentMap<Long, Notification> notifications = new ConcurrentHashMap<Long, Notification>();
    @Override
    public Collection<Notification> getAll() {
        return this.notifications.values();
    }

    @Override
    public Notification create(Notification notification) {
        Long id = notification.getId();

        if (id == null) {
            id = counter.incrementAndGet();
            notification.setId(id);
        }

        this.notifications.put(id, notification);
        return notification;
    }

    @Override
    public Notification getById(Long id) {
        return this.notifications.get(id);
    }

    @Override
    public Notification update(Notification notification) {
        Long id = notification.getId();

        if (id != null) {
            this.notifications.put(id, notification);
        }

        return notification;
    }

    @Override
    public void delete(Long id) {
        this.notifications.remove(id);
    }

}
