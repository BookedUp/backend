package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.repository.NotificationRepository;

import java.util.Collection;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    @Override
    public Collection<Notification> getAll() {
        return notificationRepository.getAll();
    }

    @Override
    public Notification getById(Long id) {
        return notificationRepository.getById(id);
    }

    @Override
    public Notification create(Notification notification) throws Exception {
        if (notification.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        return notificationRepository.create(notification);
    }

    @Override
    public Notification update(Notification notification) throws Exception {
        Notification notificationToUpdate = getById(notification.getId());
        if (notificationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        notificationToUpdate.setFromId(notification.getFromId());
        notificationToUpdate.setToId(notification.getToId());
        notificationToUpdate.setMessage(notificationToUpdate.getMessage());
        notificationToUpdate.setDate(notification.getDate());
        notificationToUpdate.setTitle(notificationToUpdate.getTitle());
        notificationToUpdate.setType(notification.getType());

        return notificationRepository.create(notificationToUpdate);
    }

    @Override
    public void delete(Long id) {
        notificationRepository.delete(id);
    }
}
