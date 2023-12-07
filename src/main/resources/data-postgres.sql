INSERT INTO address (country, city, postal_code, street_and_number, lat, lon, active)
VALUES
    ('United Kingdom', 'London', 'W1J 9BR', '150 Piccadilly', 51.5074, -0.1278, true),
    ('France', 'Paris', '75008', '10 Place de la Concorde', 48.8656, 2.3111, true),
    ('Germany', 'Berlin', '10117', 'Unter den Linden 77', 52.5162, 13.3761, true),
    ('Russia', 'St. Petersburg', '', 'Mikhailovskaya Ulitsa 1/7', 59.9343, 30.3351, true),
    ('Italy', 'Rome', '00187', 'Via del Babuino, 9', 41.9062, 12.4784, true),
    ('Switzerland', 'St. Moritz', '7500', 'Via Serlas 27', 46.4907, 9.8350, true),
    ('Spain', 'Barcelona', '08007', 'Passeig de Gràcia, 38-40', 41.3925, 2.1657, true),
    ('United Kingdom', 'London', 'W1K 4HR', 'Brook Street, Mayfair', 51.5094, -0.1493, true),
    ('Italy', 'Venice', '30122', 'Riva degli Schiavoni, 4196', 45.4336, 12.3433, true),
    ('Italy', 'Rome', '00187', 'Via Vittorio Veneto, 125', 41.9064, 12.4881, true);


INSERT INTO public.authority (role) VALUES
                                        ('ADMIN'),
                                        ('GUEST'),
                                        ('HOST');

INSERT INTO photo (url, caption, width, height, active)
VALUES
    ('src/main/resources/images/people/people1.png', 'Description1', 264, 264, true),
    ('src/main/resources/images/people/people2.png', 'Description2', 488, 511, true),
    ('src/main/resources/images/people/people3.png', 'Description3', 980, 980, true),
    ('src/main/resources/images/people/people4.png', 'Description4', 535, 466, true),
    ('src/main/resources/images/people/people5.png', 'Description5', 500, 500, true),
    ('src/main/resources/images/people/people6.png', 'Description6', 220, 230, true),
    ('src/main/resources/images/people/people7.png', 'Description7', 524, 476,true),
    ('src/main/resources/images/people/profilPicture.png', 'No profile picture', 640, 960,true);

INSERT INTO users (first_name, last_name, address_id, phone, email, password, is_blocked, verified, photo_id, type, last_password_reset_date, notification_enable, accommodation_rating_notification_enabled, average_rating, cancellation_notification_enabled, host_rating_notification_enabled, reservation_created_notification_enabled, active)
VALUES
    ('Jovan', 'Jovanović', 1, 123456789, 'jovan.jovanovic@example.com', 'jovanpass', false, true, 1, 'admin', '2023-01-01 10:00:00', null, null, null, null, null, null,true),
    ('Ana', 'Anić', 2, 987654321, 'ana.anic@example.com', 'anapass', false, true, 2, 'host', '2023-02-01 12:30:00', null, true, 9.8, true, true, true,true),
    ('Milica', 'Milosavljević', 3, 987654322, 'milica.milosavljevic@example.com', 'milicinapass', false, true, 3, 'host', '2023-03-01 15:45:00', null, true, 8.0, false, true, false,true),
    ('Marko', 'Marković', 4, 0631234567, 'marko.markovic@example.com', 'markopass', false, true, 4, 'guest', '2023-04-01 18:20:00', true, null, null, null, null, null,true),
    ('Jovana', 'Jovanović', 5, 0649876543, 'jovana.jovanovic@example.com', 'jovanapass', false, true, 5, 'guest', '2023-04-01 18:20:00', true, null, null, null, null, null,true),
    ('Nenad', 'Nenadić', 6, 0658765432, 'nenad.nenadic@example.com', 'nenadpass', false, true, 6, 'guest', '2023-05-01 21:10:00', true, null, null, null, null, null,true),
    ('Mila', 'Milićević', 7, 0661122334, 'mila.milicevic@example.com', 'milinpass', false, true, 7, 'guest', '2022-06-01 09:30:00', false, null, null, null, null, null,true);

INSERT INTO users_authority (user_id, authority_id)
VALUES
    (1, 1),
    (2, 3),
    (3, 3),
    (4, 2),
    (5, 2),
    (6, 2),
    (7, 2);

INSERT INTO accommodation (name, description, address_id, price, min_guests, max_guests, cancellation_deadline, automatic_reservation_acceptance, status, price_type, type, average_rating, host_id, active)
VALUES
    ('Luksuzni hotel', 'Ovo je opis hotela', 1, 200.0, 2, 4, 7, true, 'ACTIVE', 'PER_NIGHT', 'HOTEL', 7.8, 2, true),
    ('Pansion "Suncokret"', 'Prijatan porodični pansion', 2, 50.0, 1, 3, 3, true, 'ACTIVE', 'PER_NIGHT', 'HOSTEL', 8.0, 3, true),
    ('Vila "Miris mora"', 'Vila sa pogledom na more', 3, 300.0, 4, 8, 14, true, 'ACTIVE', 'PER_NIGHT', 'VILLA', 9.0, 2, true),
    ('Apartman "Sunce"', 'Centralni apartman u srcu grada', 4, 80.0, 1, 2, 2, true, 'ACTIVE', 'PER_NIGHT', 'APARTMENT', 9.5, 3, true),
    ('Luksuzni resort', 'Odmor iz snova', 5, 500.0, 2, 6, 14, true, 'ACTIVE', 'PER_NIGHT', 'RESORT', 7.5, 2, true),
    ('Planinski apartman', 'Pogled na planine iz svih prozora', 6, 120.0, 2, 4, 7, true, 'ACTIVE', 'PER_NIGHT', 'APARTMENT', 8.8, 3, true),
    ('Luksuzna vila na jezeru', 'Vila sa privatnim pristupom jezeru', 7, 450.0, 6, 10, 14, true, 'ACTIVE', 'PER_NIGHT', 'VILLA', 10.0, 2, true),
    ('Ekskluzivni hotel "Bella Vista"', 'Savršen odmor sa panoramskim pogledom', 8, 300.0, 2, 4, 7, true, 'ACTIVE', 'PER_NIGHT', 'HOTEL', 9.8, 3, true),
    ('Seoski dom za odmor', 'Uživajte u tišini seoskog ambijenta', 9, 70.0, 1, 3, 3, true, 'ACTIVE', 'PER_NIGHT', 'VILLA', 8.4, 2, true),
    ('Moderan apartman blizu plaže', 'Savršen smeštaj za letovanje', 10, 90.0, 2, 4, 5, true, 'ACTIVE', 'PER_NIGHT', 'APARTMENT', 8.7, 3, true);

INSERT INTO amenities (accommodation_id, amenities)
VALUES
    (1, 'FREE_WIFI'),
    (1, 'NON_SMOKING_ROOMS'),
    (1, 'PARKING'),
    (2, 'RESTAURANT'),
    (2, 'SWIMMING_POOL'),
    (3, 'FREE_WIFI'),
    (3, 'FITNESS_CENTRE'),
    (4, 'NON_SMOKING_ROOMS'),
    (5, 'PARKING'),
    (5, 'RESTAURANT'),
    (6, 'FREE_WIFI'),
    (6, 'NON_SMOKING_ROOMS'),
    (7, 'PARKING'),
    (8, 'RESTAURANT'),
    (8, 'SWIMMING_POOL'),
    (9, 'FREE_WIFI'),
    (9, 'FITNESS_CENTRE'),
    (10, 'NON_SMOKING_ROOMS'),
    (10, 'PARKING'),
    (10, 'RESTAURANT');


INSERT INTO date_range (accommodation_id, start_date, end_date)
VALUES
    (1, '2023-12-01', '2023-12-10'),
    (1, '2023-12-15', '2023-12-20'),
    (2, '2023-12-05', '2023-12-12'),
    (2, '2023-12-18', '2023-12-25'),
    (3, '2023-12-08', '2023-12-14'),
    (3, '2023-12-21', '2023-12-28'),
    (4, '2023-12-02', '2023-12-09'),
    (5, '2023-12-12', '2023-12-19'),
    (5, '2023-12-26', '2024-01-02'),
    (6, '2023-12-03', '2023-12-11'),
    (7, '2023-12-07', '2023-12-14'),
    (8, '2023-12-14', '2023-12-21'),
    (8, '2023-12-28', '2024-01-04'),
    (9, '2023-12-10', '2023-12-17'),
    (9, '2023-12-22', '2023-12-29'),
    (10, '2023-12-05', '2023-12-12'),
    (10, '2023-12-19', '2023-12-26');

INSERT INTO price_change (accommodation_id, change_date, new_price)
VALUES
    (1, '2023-12-05', 120.0),
    (1, '2023-12-15', 130.0),
    (2, '2023-12-10', 150.0),
    (2, '2023-12-20', 160.0),
    (3, '2023-12-14', 100.0),
    (3, '2023-12-28', 110.0),
    (4, '2023-12-08', 180.0),
    (5, '2023-12-16', 200.0),
    (5, '2023-12-26', 220.0),
    (6, '2023-12-12', 130.0),
    (7, '2023-12-15', 90.0),
    (8, '2023-12-25', 170.0),
    (8, '2024-01-05', 190.0),
    (9, '2023-12-20', 120.0),
    (9, '2023-12-30', 130.0),
    (10, '2023-12-18', 110.0),
    (10, '2023-12-28', 120.0);

INSERT INTO guest_favourite (guest_id, accommodation_id)
VALUES
    (4, 1),
    (4, 3),
    (4, 5),
    (4, 7),
    (5, 2),
    (5, 4),
    (5, 6),
    (6, 1),
    (6, 3),
    (7, 2),
    (7, 4),
    (7, 6),
    (7, 8),
    (4, 9),
    (5, 8),
    (6, 10),
    (7, 9);


INSERT INTO user_report (reason, reported_user_id, status)
VALUES
    ('Inappropriate content', 2, true),
    ('Hate speech', 4, true),
    ('Other', 7, false);

INSERT INTO review (guest_id, review, comment, date, host_id, accommodation_id, type, is_review_active)
VALUES
    (4, 4, 'Great experience! Highly recommended.', '2023-12-01T10:30:00', null, 1, 'ACCOMMODATION', true),
    (5, 3, 'Good place, but could be better.', '2023-12-02T11:45:00', null, 2, 'ACCOMMODATION', true),
    (6, 5, 'Amazing host and accommodation!', '2023-12-03T09:15:00', 2, null, 'HOST', true),
    (7, 2, 'Not satisfied with the service.', '2023-12-04T08:00:00', null, 4, 'ACCOMMODATION', true),
    (4, 4, 'Excellent stay, would come back!', '2023-12-05T14:20:00', null, 5, 'ACCOMMODATION', true),
    (5, 2, 'Disappointing experience.', '2023-12-06T16:30:00', 3, null, 'HOST', true),
    (6, 5, 'Host was very helpful and friendly.', '2023-12-07T12:10:00', null, 7, 'ACCOMMODATION', true),
    (7, 3, 'Average place, nothing special.', '2023-12-08T13:45:00', null, 8, 'ACCOMMODATION', true),
    (4, 4, 'Lovely accommodation with a great view.', '2023-12-09T18:00:00', 2, null, 'HOST', true),
    (5, 1, 'Terrible experience, do not recommend.', '2023-12-10T22:00:00',null, 10, 'ACCOMMODATION', true);


INSERT INTO review_report (reason, reported_review_id, status)
VALUES
    ('Inappropriate content', 1, true),
    ('False information', 2, true),
    ('Abusive language', 3, false),
    ('Not related to the review', 4, true),
    ('Spam', 5, true),
    ('Unfair criticism', 6, false),
    ('Not a genuine review', 7, true),
    ('Violation of community guidelines', 8, false),
    ('Slanderous content', 9, true),
    ('Privacy concerns', 10, true);


INSERT INTO reservation (created_time, start_date, end_date, total_price, guests_number, accommodation_id, guest_id, status, active)
VALUES
    ('2023-12-10 08:00:00', '2024-01-05', '2024-01-10', 300.00, 2, 1, 4, 'CREATED', true),
    ('2023-12-11 09:30:00', '2024-02-15', '2024-02-22', 450.00, 3, 2, 5, 'COMPLETED', true),
    ('2023-12-12 10:45:00', '2024-03-20', '2024-03-25', 200.00, 1, 3, 6, 'REJECTED', true),
    ('2023-12-13 12:15:00', '2024-04-05', '2024-04-15', 600.00, 4, 4, 7, 'ACCEPTED', true),
    ('2023-12-14 14:00:00', '2024-05-12', '2024-05-20', 350.00, 2, 5, 4, 'CANCELLED', true),
    ('2023-12-15 16:30:00', '2024-06-18', '2024-06-25', 700.00, 3, 6, 5, 'COMPLETED', true),
    ('2023-12-16 18:20:00', '2024-07-10', '2024-07-15', 250.00, 1, 7, 6, 'CREATED', true),
    ('2023-12-17 20:45:00', '2024-08-22', '2024-08-30', 500.00, 2, 8, 7, 'REJECTED', true),
    ('2023-12-18 22:10:00', '2024-09-05', '2024-09-15', 400.00, 3, 9, 4, 'REJECTED', true),
    ('2023-12-19 23:55:00', '2024-10-12', '2024-10-20', 800.00, 4, 10, 5, 'REJECTED', true),
    ('2023-12-20 01:30:00', '2024-11-18', '2024-11-25', 300.00, 2, 1, 6, 'CANCELLED', true),
    ('2023-12-21 03:40:00', '2024-12-15', '2025-01-02', 600.00, 3, 2, 7, 'COMPLETED', true),
    ('2023-12-22 05:25:00', '2025-01-20', '2025-02-01', 350.00, 2, 3, 4, 'CREATED', true),
    ('2023-12-23 07:15:00', '2025-02-15', '2025-02-22', 450.00, 3, 4, 5, 'COMPLETED', true),
    ('2023-12-24 09:00:00', '2025-03-20', '2025-03-25', 200.00, 1, 5, 6, 'REJECTED', true),
    ('2023-12-25 11:30:00', '2025-04-05', '2025-04-15', 700.00, 4, 6, 7, 'ACCEPTED', true),
    ('2023-12-26 13:15:00', '2025-05-12', '2025-05-20', 350.00, 2, 7, 4, 'CANCELLED', true),
    ('2023-12-27 15:45:00', '2025-06-18', '2025-06-25', 800.00, 3, 8, 5, 'COMPLETED', true),
    ('2023-12-28 17:30:00', '2025-07-10', '2025-07-15', 250.00, 1, 9, 6, 'CREATED', true),
    ('2023-12-29 19:20:00', '2025-08-22', '2025-08-30', 500.00, 2, 10, 7, 'ACCEPTED', true);


INSERT INTO notification (from_user_id, to_user_id, title, message, timestamp, type, active)
VALUES
    (4, 2, 'New Reservation', 'You have a new reservation request.', '2023-12-10 08:30:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (5, 3, 'Reservation Canceled', 'Your reservation has been canceled.', '2023-12-11 10:00:00', 'RESERVATION_CANCELED', true),
    (6, 2, 'Rating Received', 'You received a rating from a guest.', '2023-12-12 11:15:00', 'HOST_RATED', true),
    (7, 3, 'Rating Received', 'You received a rating for your accommodation.', '2023-12-13 12:45:00', 'ACCOMMODATION_RATED', true),
    (2, 6, 'Reservation Accepted', 'Your reservation has been accepted.', '2023-12-14 14:20:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (2, 7, 'Reservation Canceled', 'Your reservation has been canceled.', '2023-12-15 16:45:00', 'RESERVATION_CANCELED', true),
    (6, 2, 'New Reservation', 'You have a new reservation request.', '2023-12-16 18:30:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (3, 7, 'Reservation on Hold', 'Your reservation is on hold.', '2023-12-17 20:55:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (2, 4, 'Reservation Rejected', 'Your reservation has been rejected.', '2023-12-18 22:20:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (3, 5, 'Reservation Accepted', 'Your reservation has been accepted.', '2023-12-19 23:55:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (2, 6, 'Reservation Canceled', 'Your reservation has been canceled.', '2023-12-20 01:40:00', 'RESERVATION_CANCELED', true),
    (2, 7, 'Reservation Completed', 'Your reservation has been completed.', '2023-12-21 03:55:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (4, 2, 'New Reservation', 'You have a new reservation request.', '2023-12-22 05:40:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (3, 5, 'Reservation on Hold', 'Your reservation is on hold.', '2023-12-23 07:15:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (3, 4, 'Reservation Rejected', 'Your reservation has been rejected.', '2023-12-24 09:30:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (3, 5, 'Reservation Accepted', 'Your reservation has been accepted.', '2023-12-25 11:45:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (2, 6, 'Reservation Canceled', 'Your reservation has been canceled.', '2023-12-26 13:30:00', 'RESERVATION_CANCELED', true),
    (2, 7, 'Reservation Completed', 'Your reservation has been completed.', '2023-12-27 15:55:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (6, 2, 'New Reservation', 'You have a new reservation request.', '2023-12-28 17:40:00', 'RESERVATION_REQUEST_RESPONSE', true),
    (3, 7, 'Reservation on Hold', 'Your reservation is on hold.', '2023-12-29 19:00:00', 'RESERVATION_REQUEST_RESPONSE', true);

INSERT INTO accommodation_statistics (accommodation_id, year, profit, number_of_reservations)
VALUES
    (1, 2023, 1200.50, 15),
    (2, 2023, 800.75, 10),
    (3, 2023, 1500.25, 18),
    (4, 2023, 900.30, 12),
    (5, 2023, 2000.90, 25),
    (6, 2023, 1800.60, 20),
    (7, 2023, 750.40, 8),
    (8, 2023, 3000.20, 30),
    (9, 2023, 1000.80, 14),
    (10, 2023, 2500.45, 22);


INSERT INTO statistics (from_date, to_date, profit, number_of_reservations)
VALUES
    ('2023-01-01', '2023-12-31', 9500.40, 120),
    ('2023-01-01', '2023-12-31', 18500.20, 200);

INSERT INTO statistics_detail (key, profit, number_of_reservations, accommodation_id, statistics_id)
VALUES
    ('May', 5000.75, 50, 1, null),
    ('Seoski dom za odmor', 3200.40, 30, null, 2);











