package rs.ac.uns.ftn.asd.BookedUp.domain;

import java.util.Date;

public class PriceChange {
    private Date changeDate;
    private double newPrice;

    public PriceChange(Date changeDate, double newPrice) {
        this.changeDate = changeDate;
        this.newPrice = newPrice;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
