package rs.ac.uns.ftn.asd.BookedUp.domain;

public class Photo {
    private Long id;
    private String url; // URL or file path of the photo
    private String caption; // Caption or description for the photo
    private int width; // Width of the photo in pixels
    private int height; //Height of photo in pixels

    public Photo(){}

    public Photo(long id, String url, String caption, int width, int height){
        this.id = id;
        this.url = url;
        this.caption = caption;
        this.width = width;
        this.height = height;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void copyValues(Photo photo){
        this.id = photo.getId();
        this.url = photo.getUrl();
        this.caption = photo.getCaption();
        this.width = photo.getWidth();
        this.height = photo.getHeight();
    }
}
