package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;

import java.util.ArrayList;
import java.util.List;


@Component
public class ReviewMapper implements MapperInterface<Review, ReviewDTO> {
    @Autowired
    AccommodationMapper accommodationMapper;

    @Autowired
    GuestMapper guestMapper;

    @Autowired
    HostMapper hostMapper;


    @Override
    public Review toEntity(ReviewDTO dto) {

        if (dto == null) {
            return null;
        }

        Accommodation accommodation = accommodationMapper.toEntity(dto.getAccommodationDTO());
        Guest guest = guestMapper.toEntity(dto.getGuestDTO());
        Host host = hostMapper.toEntity(dto.getHostDTO());

        Review review = new Review();
        review.setId(dto.getId());
        review.setGuest(guest);
        review.setReview(dto.getReview());
        review.setComment(dto.getComment());
        review.setDate(dto.getDate());
        review.setHost(host);
        review.setAccommodation(accommodation);
        review.setType(dto.getType());
        review.setIsReviewActive(true);

        return review;

    }

    @Override
    public ReviewDTO toDto(Review entity) {
        if (entity == null) {
            return null;
        }

        AccommodationDTO accommodationDTO = accommodationMapper.toDto(entity.getAccommodation());
        GuestDTO guestDTO = guestMapper.toDto(entity.getGuest());
        HostDTO hostDTO = hostMapper.toDto(entity.getHost());

        ReviewDTO reviewDTO = new ReviewDTO();
        reviewDTO.setId(entity.getId());
        reviewDTO.setGuestDTO(guestDTO);
        reviewDTO.setAccommodationDTO(accommodationDTO);
        reviewDTO.setReview(entity.getReview());
        reviewDTO.setComment(entity.getComment());
        reviewDTO.setDate(entity.getDate());
        reviewDTO.setHostDTO(hostDTO);
        reviewDTO.setType(entity.getType());

        return reviewDTO;
    }
}
