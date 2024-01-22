package rs.ac.uns.ftn.asd.BookedUp.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ReservationStatus {
    CREATED,
    REJECTED,
    ACCEPTED,
    CANCELLED,
    COMPLETED;

    @JsonValue
    public String getValue() {
        return name();
    }

    @JsonCreator
    public static ReservationStatus fromValue(String value) {
        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid ReservationStatus value: " + value);
    }

}
