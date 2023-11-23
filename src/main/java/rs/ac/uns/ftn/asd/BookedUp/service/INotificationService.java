package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;

import java.util.Collection;

public interface INotificationService {
    Collection<Notification> getAll();

    Notification getById(Long id);

    Notification create(Notification notification) throws Exception;

    Notification update(Notification notification) throws Exception;

    void delete(Long id);
}
