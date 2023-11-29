package rs.ac.uns.ftn.asd.BookedUp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;
import rs.ac.uns.ftn.asd.BookedUp.domain.Review;
import rs.ac.uns.ftn.asd.BookedUp.dto.NotificationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.ReviewDTO;
import rs.ac.uns.ftn.asd.BookedUp.mapper.NotificationMapper;
import rs.ac.uns.ftn.asd.BookedUp.repository.NotificationRepository;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class NotificationService implements INotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private NotificationMapper notificationMapper;
    @Override
    public Collection<NotificationDTO> getAll() {
        Collection<Notification> notificationss = (notificationRepository.getAll());
        Collection<NotificationDTO> notificationDTOS = new ArrayList<>();

        for (Notification notification : notificationss) {
            NotificationDTO notificationDTO = notificationMapper.toDto(notification);
            notificationDTOS.add(notificationDTO);
        }

        return notificationDTOS;
    }

    @Override
    public NotificationDTO getById(Long id) {
        Notification notification =  notificationRepository.getById(id);
        return notificationMapper.toDto(notification);
    }


    @Override
    public NotificationDTO create(NotificationDTO notificationDTO) throws Exception {
        if (notificationDTO.getId() != null) {
            throw new Exception("Id mora biti null prilikom perzistencije novog entiteta.");
        }
        Notification notification = notificationMapper.toEntity(notificationDTO);
        Notification createdNotification=  notificationRepository.create(notification);
        return notificationMapper.toDto(createdNotification);
    }

    @Override
    public NotificationDTO update(NotificationDTO notificationDTO) throws Exception {
        Notification notification = notificationMapper.toEntity(notificationDTO);
        Notification notificationToUpdate= notificationRepository.getById(notification.getId());        if (notificationToUpdate == null) {
            throw new Exception("Trazeni entitet nije pronadjen.");
        }
        notificationToUpdate.setFrom(notification.getFrom());
        notificationToUpdate.setTo(notification.getTo());
        notificationToUpdate.setTitle(notification.getTitle());
        notificationToUpdate.setMessage(notification.getMessage());
        notificationToUpdate.setTimestamp(notification.getTimestamp());
        notificationToUpdate.setType(notification.getType());
        notificationToUpdate.setActive(notification.isActive());


        Notification updatedNotification = notificationRepository.create(notificationToUpdate);
        return notificationMapper.toDto(updatedNotification);
    }

    @Override
    public void delete(Long id) {
        notificationRepository.delete(id);
    }
}
