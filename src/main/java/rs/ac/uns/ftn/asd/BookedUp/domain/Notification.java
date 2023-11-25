package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.Date;

public class Notification {
    private Long id;
    private User from;
    private User to;
    private String title;
    private String message;
    private Date timestamp;
    private NotificationType type;

    public Notification() {
    }

    public Notification(Long id, User from, User to, String title, String message, Date timestamp, NotificationType type) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.title = title;
        this.message = message;
        this.timestamp = timestamp;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFrom(){return from;}
    public void setFrom(User from){this.from = from;}
    public User getTo(){return to;}
    public void setTo(User to){this.to = to;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public String getMessage() {return message;}
    public void setMessage(String message) {this.message = message;}
    public Date getTimestamp(){return timestamp;}
    public void setTimestamp(Date timestamp){this.timestamp = timestamp;}
    public NotificationType getType() {return type;}
    public void setType(NotificationType type) {this.type = type;}

    public void copyValues(Notification notification) {
        this.id = notification.getId();
        this.from = notification.getFrom();
        this.to = notification.getTo();
        this.title = notification.getTitle();
        this.message = notification.getMessage();
        this.timestamp = notification.getTimestamp();
        this.type = notification.getType();
    }
}
