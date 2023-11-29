package rs.ac.uns.ftn.asd.BookedUp.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DateRange {
    private Date startDate;
    private Date endDate;
}