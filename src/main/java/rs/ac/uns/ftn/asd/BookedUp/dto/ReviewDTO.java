package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Host;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewType;
import rs.ac.uns.ftn.asd.BookedUp.domain.User;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
    private Long id;
    private UserDTO userDTO;
    private int review;
    private String comment;
    private LocalDateTime date;
    private HostDTO hostDTO;
    private AccommodationDTO accommodationDTO;
    private ReviewType type;

    public void copyValues(ReviewDTO dto) {
        this.userDTO=dto.getUserDTO();
        this.review=dto.getReview();
        this.comment = dto.getComment();
        this.date = dto.getDate();
        this.hostDTO = dto.getHostDTO();
        this.accommodationDTO = dto.getAccommodationDTO();
        this.type = dto.getType();
    }
}
