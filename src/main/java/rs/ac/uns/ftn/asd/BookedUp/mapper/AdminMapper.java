package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminMapper implements MapperInterface<Admin, AdminDTO> {

    ReviewReportMapper reviewReportMapper = new ReviewReportMapper();

    UserReportMapper userReportMapper = new UserReportMapper();

    AccommodationMapper accommodationMapper = new AccommodationMapper();

//    NotificationMapper notificationMapper = new NotificationMapper();

    @Override
    public Admin toEntity(AdminDTO dto) {
        if (dto == null){
            return null;
        }

        List<ReviewReport> reviewReports = new ArrayList<ReviewReport>();
        if(dto.getReviewReports() != null) {
            for(ReviewReportDTO reviewReportDTO : dto.getReviewReports())
                reviewReports.add(reviewReportMapper.toEntity(reviewReportDTO));
        }

        List<UserReport> userReports = new ArrayList<UserReport>();
        if(dto.getUserReports() != null) {
            for(UserReportDTO userReportDTO : dto.getUserReports())
                userReports.add(userReportMapper.toEntity(userReportDTO));
        }

        List<Accommodation> requests = new ArrayList<Accommodation>();
        if(dto.getRequests() != null) {
            for(AccommodationDTO accommodationDTO : dto.getRequests())
                requests.add(accommodationMapper.toEntity(accommodationDTO));
        }

        List<Notification> notifications = new ArrayList<Notification>();
//        if(dto.getNotifications() != null) {
//            for(NotificationDTO notificationDTO : dto.getNotifications())
//                notifications.add(notificationMapper.toEntity(notificationDTO));
//        }

        return new Admin(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getPhone(), dto.getEmail(), dto.getPassword(), Role.ADMIN, dto.isBlocked(), notifications, userReports, reviewReports, requests);
    }

    @Override
    public AdminDTO toDto(Admin entity) {
        if (entity == null){
            return null;
        }

        List<ReviewReportDTO> reviewReportDTOS = new ArrayList<ReviewReportDTO>();
        if(entity.getReviewReports() != null) {
            for(ReviewReport reviewReport : entity.getReviewReports())
                reviewReportDTOS.add(reviewReportMapper.toDto(reviewReport));
        }

        List<UserReportDTO> userReportDTOS = new ArrayList<UserReportDTO>();
        if(entity.getUserReports() != null) {
            for(UserReport userReport : entity.getUserReports())
                userReportDTOS.add(userReportMapper.toDto(userReport));
        }

        List<AccommodationDTO> requestDTOS = new ArrayList<AccommodationDTO>();
        if(entity.getRequests() != null) {
            for(Accommodation accommodation : entity.getRequests())
                requestDTOS.add(accommodationMapper.toDto(accommodation));
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<NotificationDTO>();
//        if(entity.getNotifications() != null) {
//            for(Notification notification : entity.getNotifications())
//                notificationDTOS.add(notificationMapper.toDto(notification));
//        }


        return new AdminDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getPassword(), entity.isBlocked(), notificationDTOS, userReportDTOS, reviewReportDTOS, requestDTOS);

    }
}