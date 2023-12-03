package rs.ac.uns.ftn.asd.BookedUp.mapper;

import jdk.jfr.Frequency;
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
        if(dto.getAccommodations() != null) {
            for(AccommodationDTO accommodationDTO : dto.getAccommodations())
                accommodations.add(accommodationMapper.toEntity(accommodationDTO));
        }

        List<Reservation> requests = new ArrayList<Reservation>();
        if(dto.getRequests() != null) {
            for(ReservationDTO reservationDTO : dto.getRequests())
                requests.add(reservationMapper.toEntity(reservationDTO));
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

        return new Host(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getAddress(), dto.getPhone(), dto.getEmail(), dto.getPassword(), Role.HOST, dto.isBlocked(), notifications, dto.getAverageRating(), accommodations, requests, statistics, accommodationStatistics, dto.isReservationCreatedNotificationEnabled(), dto.isCancellationNotificationEnabled(), dto.isHostRatingNotificationEnabled(), dto.isAccommodationRatingNotificationEnabled());

    }

    @Override
    public HostDTO toDto(Host entity) {
        if (entity == null) {
            return null;
        }
        List<AccommodationDTO> accommodationDTOS = new ArrayList<AccommodationDTO>();
        if(entity.getAccommodations() != null) {
            for(Accommodation accommodation : entity.getAccommodations())
                accommodationDTOS.add(accommodationMapper.toDto(accommodation));
        }

        List<ReservationDTO> requestsDTOS = new ArrayList<ReservationDTO>();
        if(entity.getRequests() != null) {
            for(Reservation reservation : entity.getRequests())
                requestsDTOS.add(reservationMapper.toDto(reservation));
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

        return new HostDTO(entity.getId(), entity.getFirstName(), entity.getLastName(), entity.getAddress(), entity.getPhone(), entity.getEmail(), entity.getPassword(), entity.isBlocked(), notificationDTOS, entity.getAverageRating(), accommodationDTOS, requestsDTOS, statisticsDTOS, accommodationStatisticsDTOS, entity.isReservationCreatedNotificationEnabled(), entity.isCancellationNotificationEnabled(), entity.isHostRatingNotificationEnabled(), entity.isAccommodationRatingNotificationEnabled());

    }
}
