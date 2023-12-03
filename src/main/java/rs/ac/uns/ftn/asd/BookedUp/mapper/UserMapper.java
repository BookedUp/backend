package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;
import rs.ac.uns.ftn.asd.BookedUp.dto.NotificationDTO;
import rs.ac.uns.ftn.asd.BookedUp.dto.UserDTO;
import rs.ac.uns.ftn.asd.BookedUp.domain.Notification;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper implements MapperInterface<User, UserDTO> {

    @Autowired
    NotificationMapper notificationMapper;
    @Override
    public User toEntity(UserDTO dto) {
        if (dto == null) {
            return null;
        }

        List<Notification> notifications = new ArrayList<Notification>();
        if(dto.getNotifications() != null) {
            for(NotificationDTO notificationDTO : dto.getNotifications())
                notifications.add(notificationMapper.toEntity(notificationDTO));
        }




        return new User(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getPhone(), dto.getEmail(), dto.getPassword(), null, dto.isBlocked(), notifications);
    }

    @Override
    public UserDTO toDto(User entity) {
        if (entity == null) {
            return null;
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<NotificationDTO>();
        if(entity.getNotifications() != null) {
            for(Notification notification : entity.getNotifications())
                notificationDTOS.add(notificationMapper.toDto(notification));
        }

        return new UserDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getPassword(), entity.isBlocked(), notificationDTOS);
    }
}
