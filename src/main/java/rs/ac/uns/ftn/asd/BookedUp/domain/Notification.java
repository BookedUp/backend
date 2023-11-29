package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.enums.NotificationType;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    private Long id;
    private User from;
    private User to;
    private String title;
    private String message;
    private Date timestamp;
    private NotificationType type;
    private boolean active;


    public void copyValues(Notification notification) {
        this.id = notification.getId();
        this.from = notification.getFrom();
        this.to = notification.getTo();
        this.title = notification.getTitle();
        this.message = notification.getMessage();
        this.timestamp = notification.getTimestamp();
        this.type = notification.getType();
        this.active = notification.isActive();
    }
}
