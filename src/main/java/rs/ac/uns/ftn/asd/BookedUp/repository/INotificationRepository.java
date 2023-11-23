package rs.ac.uns.ftn.asd.BookedUp.repository;

import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;

import java.util.Collection;

public interface INotificationRepository {
    Collection<Notification> getAll();

    Notification create(Notification notification);

    Notification getById(Long id);

    Notification update(Notification notification);

    void delete(Long id);
}
