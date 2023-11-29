package rs.ac.uns.ftn.asd.BookedUp.mapper;

import org.springframework.stereotype.Component;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.dto.*;
import rs.ac.uns.ftn.asd.BookedUp.enums.Role;

import java.util.ArrayList;
import java.util.List;

@Component
public class HostMapper implements MapperInterface<Host, HostDTO>{

    AccommodationMapper accommodationMapper = new AccommodationMapper();

    ReservationMapper reservationMapper = new ReservationMapper();

    NotificationMapper notificationMapper = new NotificationMapper();

    StatisticsMapper statisticsMapper = new StatisticsMapper();

    AccommodationStatisticsMapper accommodationStatisticsMapper = new AccommodationStatisticsMapper();
    @Override
    public Host toEntity(HostDTO dto) {
        if (dto == null) {
            return null;
        }
        List<Accommodation> accommodations = new ArrayList<Accommodation>();
        if(dto.getProperties() != null) {
            for(AccommodationDTO accommodationDTO : dto.getProperties())
                accommodations.add(accommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> reservations = new ArrayList<Reservation>();
        if(dto.getRequests() != null) {
            for(ReservationDTO reservationDTO : dto.getRequests())
                reservations.add(reservationMapper.toEntity(reservationDTO));
        }

        List<Notification> notifications = new ArrayList<Notification>();
        if(dto.getNotifications() != null) {
            for(NotificationDTO notificationDTO : dto.getNotifications())
                notifications.add(notificationMapper.toEntity(notificationDTO));
        }

        List<Statistics> statistics = new ArrayList<Statistics>();
        if(dto.getStatistics() != null) {
            for(StatisticsDTO statisticsDTO : dto.getStatistics())
                statistics.add(statisticsMapper.toEntity(statisticsDTO));
        }

        List<AccommodationStatistics> accommodationStatistics = new ArrayList<AccommodationStatistics>();
        if(dto.getAccommodationStatistics() != null) {
            for(AccommodationStatisticsDTO accommodationStatisticsDTO : dto.getAccommodationStatistics())
                accommodationStatistics.add(accommodationStatisticsMapper.toEntity(accommodationStatisticsDTO));
        }

        Host host = new Host();
        host.setId(dto.getId());
        host.setFirstName(dto.getFirstName());
        host.setLastName(dto.getLastName());
        host.setAddress(dto.getAddress());
        host.setPhone(dto.getPhone());
        host.setPassword(dto.getPassword());
        host.setEmail(dto.getEmail());
        host.setRole(Role.HOST);
        host.setBlocked(dto.isBlocked());
        host.setProperties(accommodations);
        host.setRequests(reservations);
        host.setNotifications(notifications);
        host.setAverageRating(dto.getAverageRating());
        host.setReservationCreatedNotificationEnabled(dto.isReservationCreatedNotificationEnabled());
        host.setCancellationNotificationEnabled(dto.isCancellationNotificationEnabled());
        host.setHostRatingNotificationEnabled(dto.isHostRatingNotificationEnabled());
        host.setAccommodationRatingNotificationEnabled(dto.isAccommodationRatingNotificationEnabled());
        host.setStatistics(statistics);
        host.setAccommodationStatistics(accommodationStatistics);

        return host;

    }

    @Override
    public HostDTO toDto(Host entity) {
        if (entity == null) {
            return null;
        }
        List<AccommodationDTO> accommodationDTOS = new ArrayList<AccommodationDTO>();
        if(entity.getProperties() != null) {
            for(Accommodation accommodation : entity.getProperties())
                accommodationDTOS.add(accommodationMapper.toDto(accommodation));
        }

        List<ReservationDTO> reservationDTOS = new ArrayList<ReservationDTO>();
        if(entity.getRequests() != null) {
            for(Reservation reservation : entity.getRequests())
                reservationDTOS.add(reservationMapper.toDto(reservation));
        }

        List<NotificationDTO> notificationDTOS = new ArrayList<NotificationDTO>();
        if(entity.getNotifications() != null) {
            for(Notification notification : entity.getNotifications())
                notificationDTOS.add(notificationMapper.toDto(notification));
        }

        List<StatisticsDTO> statisticsDTOS = new ArrayList<StatisticsDTO>();
        if(entity.getStatistics() != null) {
            for(Statistics statistics : entity.getStatistics())
                statisticsDTOS.add(statisticsMapper.toDto(statistics));
        }

        List<AccommodationStatisticsDTO> accommodationStatisticsDTOS = new ArrayList<AccommodationStatisticsDTO>();
        if(entity.getAccommodationStatistics() != null) {
            for(AccommodationStatistics accommodationStatistics : entity.getAccommodationStatistics())
                accommodationStatisticsDTOS.add(accommodationStatisticsMapper.toDto(accommodationStatistics));
        }

        HostDTO hostDto = new HostDTO();
        hostDto.setId(entity.getId());
        hostDto.setFirstName(entity.getFirstName());
        hostDto.setLastName(entity.getLastName());
        hostDto.setAddress(entity.getAddress());
        hostDto.setPhone(entity.getPhone());
        hostDto.setPassword(entity.getPassword());
        hostDto.setEmail(entity.getEmail());
        hostDto.setBlocked(entity.isBlocked());
        hostDto.setProperties(accommodationDTOS);
        hostDto.setRequests(reservationDTOS);
        hostDto.setNotifications(notificationDTOS);
        hostDto.setAverageRating(entity.getAverageRating());
        hostDto.setReservationCreatedNotificationEnabled(entity.isReservationCreatedNotificationEnabled());
        hostDto.setCancellationNotificationEnabled(entity.isCancellationNotificationEnabled());
        hostDto.setHostRatingNotificationEnabled(entity.isHostRatingNotificationEnabled());
        hostDto.setAccommodationRatingNotificationEnabled(entity.isAccommodationRatingNotificationEnabled());
        hostDto.setStatistics(statisticsDTOS);
        hostDto.setAccommodationStatistics(accommodationStatisticsDTOS);

        return hostDto;

    }
}
