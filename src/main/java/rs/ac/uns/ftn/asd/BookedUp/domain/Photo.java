package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Photo {
    private Long id;
    private String url;
    private String caption;
    private int width;
    private int height;

    public void copyValues(Photo photo){
        this.id = photo.getId();
        this.url = photo.getUrl();
        this.caption = photo.getCaption();
        this.width = photo.getWidth();
        this.height = photo.getHeight();
    }
}
