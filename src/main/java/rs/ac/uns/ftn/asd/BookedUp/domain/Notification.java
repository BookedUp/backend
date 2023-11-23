package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.Date;

public class Notification {
    private Long id;
    private Long fromId;
    private Long toId;
    private String message;
    private Date date;

    private String title;
    private NotificationType type;

    public Notification() {
    }

    public Notification(Long id, Long fromId, Long toId, String message, Date date, String title, NotificationType type) {
        this.id = id;
        this.fromId = fromId;
        this.toId = toId;
        this.message = message;
        this.date = date;
        this.title = title;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromId() {
        return fromId;
    }

    public void setFromId(Long fromId) {
        this.fromId = fromId;
    }

    public Long getToId() {
        return toId;
    }

    public void setToId(Long toId) {
        this.toId = toId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public void copyValues(Notification notification) {
        this.fromId = notification.getFromId();
        this.toId = notification.getToId();
        this.message = notification.getMessage();
        this.date = notification.getDate();
        this.title = notification.getTitle();
        this.type = notification.getType();
    }
}
