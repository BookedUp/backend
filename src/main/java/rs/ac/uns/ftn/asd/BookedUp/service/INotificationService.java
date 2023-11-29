package rs.ac.uns.ftn.asd.BookedUp.service;

import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.dto.NotificationDTO;

import java.util.Collection;

public interface INotificationService {
    Collection<NotificationDTO> getAll();

    NotificationDTO getById(Long id);

    NotificationDTO create(NotificationDTO notificationDTO) throws Exception;

    NotificationDTO update(NotificationDTO notificationDTO) throws Exception;

    void delete(Long id);
}
