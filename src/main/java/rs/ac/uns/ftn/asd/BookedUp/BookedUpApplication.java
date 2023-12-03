package rs.ac.uns.ftn.asd.BookedUp;


import org.modelmapper.ModelMapper;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import rs.ac.uns.ftn.asd.BookedUp.domain.*;
import rs.ac.uns.ftn.asd.BookedUp.repository.*;
import rs.ac.uns.ftn.asd.BookedUp.service.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SpringBootApplication
public class BookedUpApplication {

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	public static void main(String[] args) {

		SpringApplication.run(BookedUpApplication.class, args);
	}

//	@Bean\
//	public CommandLineRunner demo(AdminRepository adminRepository, HostRepository hostRepository,
//								  GuestRepository guestRepository, UserReportRepository userReportRepository,
//								  ReviewReportRepository reviewReportRepository, ReservationRepository reservationRepository,
//								  AccommodationRepository accommodationRepository, ReviewRepository reviewRepository,
//								  NotificationRepository notificationRepository) {
//		return (args) -> {
//			// Create example data
//			Admin admin1 = createAdminExample();
//			Host host1 = createHostExample();
//			Guest guest1 = createGuestExample();
//			Guest guest2 = createGuestExample();
//
//			UserReport userReport1 = createUserReport1(guest1);
//			UserReport userReport2 = createUserReport2(host1);
//
//			Review review1 = createReview1();
//			Review review2 = createReview2();
//
//			//add reviews to guest
//			List<Review> reviews = new ArrayList<>();
//			reviews.add(review1);
//			reviews.add(review2);
//			guest2.setReviews(reviews);
//
//			List<UserReport> reports = new ArrayList<>();
//			reports.add(userReport1);
//			reports.add(userReport2);
//			admin1.setUserReports(reports);
//
//			ReviewReport reviewReport1 = createReviewReport1(review1);
//			ReviewReport reviewReport2 = createReviewReport2(review2);
//
//			List<ReviewReport> reviewReports = new ArrayList<>();
//			reviewReports.add(reviewReport1);
//			reviewReports.add(reviewReport2);
//			admin1.setReviewReports(reviewReports);
//
//			Accommodation accommodation1 = createAccommodation1();
//			Accommodation accommodation2 = createAccommodation2();
//
//			List<Accommodation> accommodations = new ArrayList<>();
//			accommodations.add(accommodation1);
//
//			admin1.setRequests(accommodations);
//
//			Reservation reservation1 = createReservation1();
//			Reservation reservation2 = createReservation2();
//
//			List<Reservation> reservations = new ArrayList<>();
//			reservations.add(reservation2);
//			guest2.setReservations(reservations);
//
//
//			accommodations.add(accommodation2);
//			guest2.setFavourites(accommodations);
//
//			host1.setProperties(accommodations);
//			reservations.add(reservation1);
//			host1.setRequests(reservations);
//
//			reservations.remove(reservation2);
//			guest2.setRequests(reservations);
//
//			Notification notification1 = createNotificationToGuest(host1, guest2);
//			List<Notification> notifications = new ArrayList<>();
//			notifications.add(notification1);
//			guest2.setNotifications(notifications);
//
//			notifications.remove(notification1);
//
//			Notification notification2 = createNotificationToHost(guest2, host1);
//			notifications.add(notification2);
//			host1.setNotifications(notifications);
//
//
//			// Save examples to the database
//			adminRepository.create(admin1);
//			hostRepository.create(host1);
//			guestRepository.create(guest1);
//			guestRepository.create(guest2);
//			reviewRepository.create(review1);
//			reviewRepository.create(review2);
//			userReportRepository.create(userReport1);
//			userReportRepository.create(userReport2);
//			reviewReportRepository.create(reviewReport1);
//			reviewReportRepository.create(reviewReport2);
//			accommodationRepository.create(accommodation1);
//			accommodationRepository.create(accommodation2);
//			reservationRepository.create(reservation1);
//			reservationRepository.create(reservation2);
//			notificationRepository.create(notification1);
//			notificationRepository.create(notification2);
//		};
//	}
//
//	private Admin createAdminExample() {
//		Admin admin = new Admin();
//		admin.setFirstName("Admin");
//		admin.setLastName("User");
//		admin.setEmail("admin@example.com");
//		admin.setPassword("adminpassword");
//		admin.setRole(Role.ADMIN);
//		return admin;
//	}
//
//	private Host createHostExample() {
//		Host host = new Host();
//		host.setFirstName("Host");
//		host.setLastName("User");
//		host.setEmail("host@example.com");
//		host.setPassword("hostpassword");
//		host.setRole(Role.HOST);
//		return host;
//	}
//
//	private Guest createGuestExample() {
//		Guest guest = new Guest();
//		guest.setFirstName("Guest");
//		guest.setLastName("User");
//		guest.setEmail("guest@example.com");
//		guest.setPassword("guestpassword");
//		guest.setRole(Role.GUEST);
//		return guest;
//	}
//
//	public static UserReport createUserReport1(User user) {
//		UserReport userReport = new UserReport();
//		userReport.setId(1L);
//		userReport.setReason("Inappropriate behavior");
//		userReport.setReportedUser(user);
//		userReport.setStatus(false);
//		return userReport;
//	}
//
//	public static UserReport createUserReport2(User user) {
//		UserReport userReport = new UserReport();
//		userReport.setId(2L);
//		userReport.setReason("Spamming");
//		userReport.setReportedUser(user);
//		userReport.setStatus(true);
//		return userReport;
//	}
//
//	public static ReviewReport createReviewReport1(Review review) {
//		ReviewReport reviewReport = new ReviewReport();
//		reviewReport.setId(1L);
//		reviewReport.setReason("Inappropriate content");
//		reviewReport.setReportedReview(review);
//		reviewReport.setStatus(false);
//		return reviewReport;
//	}
//
//	public static ReviewReport createReviewReport2(Review review) {
//		ReviewReport reviewReport = new ReviewReport();
//		reviewReport.setId(2L);
//		reviewReport.setReason("Fake review");
//		reviewReport.setReportedReview(review);
//		reviewReport.setStatus(true);
//		return reviewReport;
//	}
//
//	public static Review createReview1(){
//		Review review = new Review(
//				201L,
//				new User(/* user data */),
//				4,
//				"Great experience!",
//				LocalDateTime.now(),
//				new Host(/* host data */),
//				new Accommodation(/* accommodation data */),
//				ReviewType.ACCOMMODATION,
//				true
//		);
//		return review;
//	}
//
//	public static Review createReview2(){
//		Review review = new Review(
//				202L,
//				new User(/* user data */),
//				1,
//				"Terrible service!",
//				LocalDateTime.now(),
//				new Host(/* host data */),
//				new Accommodation(/* accommodation data */),
//				ReviewType.ACCOMMODATION,
//				true
//		);
//		return review;
//	}
//
//	public static Reservation createReservation1() {
//		Reservation reservation = new Reservation();
//		reservation.setId(1L);
//		reservation.setCreatedTime(LocalDateTime.now());
//		reservation.setStartDate(new Date());
//		reservation.setEndDate(new Date());
//		reservation.setTotalPrice(150.0);
//		reservation.setGuestsNumber(2);
//		reservation.setAccommodation(new Accommodation(/* accommodation data */));
//		reservation.setGuest(new Guest(/* guest data */));
//		reservation.setStatus(ReservationStatus.ON_HOLD);
//		return reservation;
//	}
//
//	public static Reservation createReservation2() {
//		Reservation reservation = new Reservation();
//		reservation.setId(2L);
//		reservation.setCreatedTime(LocalDateTime.now());
//		reservation.setStartDate(new Date());
//		reservation.setEndDate(new Date());
//		reservation.setTotalPrice(200.0);
//		reservation.setGuestsNumber(4);
//		reservation.setAccommodation(new Accommodation(/* accommodation data */));
//		reservation.setGuest(new Guest(/* guest data */));
//		reservation.setStatus(ReservationStatus.ACCEPTED);
//		return reservation;
//	}
//
//	public static Accommodation createAccommodation1() {
//		Accommodation accommodation = new Accommodation();
//		accommodation.setId(1L);
//		accommodation.setName("Cozy Cabin");
//		accommodation.setAddress(new Address(/* address data */));
//		accommodation.setDescription("A comfortable cabin in the woods");
//		accommodation.setAmenities(new ArrayList<>()); // Add amenities as needed
//		accommodation.setPhotos(new ArrayList<>()); // Add photos as needed
//		accommodation.setMinGuests(2);
//		accommodation.setMaxGuests(4);
//		accommodation.setType(AccommodationType.HOSTEL);
//		accommodation.setAvailability(new ArrayList<>()); // Add date ranges as needed
//		accommodation.setPrice(100.0);
//		accommodation.setPriceType(PriceType.PER_NIGHT);
//		accommodation.setStatus(AccommodationStatus.ACTIVE);
//		accommodation.setPriceChanges(new ArrayList<>()); // Add price changes as needed
//		accommodation.setCancellationDeadline(7);
//		accommodation.setAutomaticReservationAcceptance(true);
//		accommodation.setReservations(new ArrayList<>()); // Add reservations as needed
//		accommodation.setReviews(new ArrayList<>()); // Add reviews as needed
//		accommodation.setAverageRating(4.5);
//
//		return accommodation;
//	}
//
//	public static Accommodation createAccommodation2() {
//		Accommodation accommodation = new Accommodation();
//		accommodation.setId(2L);
//		accommodation.setName("Sunny Villa");
//		accommodation.setAddress(new Address(/* address data */));
//		accommodation.setDescription("A beautiful villa with a view");
//		accommodation.setAmenities(new ArrayList<>()); // Add amenities as needed
//		accommodation.setPhotos(new ArrayList<>()); // Add photos as needed
//		accommodation.setMinGuests(4);
//		accommodation.setMaxGuests(8);
//		accommodation.setType(AccommodationType.VILLA);
//		accommodation.setAvailability(new ArrayList<>()); // Add date ranges as needed
//		accommodation.setPrice(200.0);
//		accommodation.setPriceType(PriceType.PER_NIGHT);
//		accommodation.setStatus(AccommodationStatus.ACTIVE);
//		accommodation.setPriceChanges(new ArrayList<>()); // Add price changes as needed
//		accommodation.setCancellationDeadline(14);
//		accommodation.setAutomaticReservationAcceptance(true);
//		accommodation.setReservations(new ArrayList<>()); // Add reservations as needed
//		accommodation.setReviews(new ArrayList<>()); // Add reviews as needed
//		accommodation.setAverageRating(4.8);
//
//		return accommodation;
//	}
//
//	public static Notification createNotificationToGuest(User from, User to){
//		Notification notification = new Notification();
//		notification.setId(28L);
//		notification.setFrom(from);
//		notification.setTo(to);
//		notification.setTitle("Reservation Accepted!");
//		notification.setMessage("Hey, your reservation for 28.12. is accepted. Happy Weekend.");
//		notification.setTimestamp(new Date());
//		notification.setType(NotificationType.RESERVATION_REQUEST_RESPONSE);
//		return notification;
//	}
//
//	public static Notification createNotificationToHost(User from, User to){
//		Notification notification = new Notification();
//		notification.setId(33L);
//		notification.setFrom(from);
//		notification.setTo(to);
//		notification.setTitle("Reservation Created!");
//		notification.setMessage("One Reservation is waiting for reviewing!");
//		notification.setTimestamp(new Date());
//		notification.setType(NotificationType.RESERVATION_CREATED);
//		return notification;
//	}
}
