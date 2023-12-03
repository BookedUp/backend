package rs.ac.uns.ftn.asd.BookedUp.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import rs.ac.uns.ftn.asd.BookedUp.domain.Accommodation;
import rs.ac.uns.ftn.asd.BookedUp.domain.Address;
import rs.ac.uns.ftn.asd.BookedUp.domain.ReviewReport;
import rs.ac.uns.ftn.asd.BookedUp.domain.UserReport;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AdminDTO extends UserDTO{
    private List<UserReportDTO> userReports;
    private List<ReviewReportDTO> reviewReports;
    private List<AccommodationDTO> requests;

    public AdminDTO(Long id, String firstName, String lastName, Address address, Integer phone, String email, String password, boolean isBlocked, List<NotificationDTO> notifications, List<UserReportDTO> userReports, List<ReviewReportDTO> reviewReports, List<AccommodationDTO> requests) {
        super(id, firstName, lastName, address, phone, email, password, isBlocked, notifications);
        this.userReports = userReports;
        this.reviewReports = reviewReports;
        this.requests = requests;
    }

    public void copyValues(AdminDTO dto) {
        super.copyValues(dto);
        this.userReports = dto.getUserReports();
        this.reviewReports = dto.getReviewReports();
        this.requests = dto.getRequests();
    }
}