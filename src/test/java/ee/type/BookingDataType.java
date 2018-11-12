package ee.type;

import ee.domain.Booking;
import ee.domain.BookingImpl;

public class BookingDataType implements Booking<String> {
    private Long bookingid;

    private String firstname;

    private String lastname;

    private String totalprice;

    private Boolean depositpaid;

    private BookingImpl.Dates bookingdates;

    @Override
    public Long getBookingid() {
        return bookingid;
    }

    @Override
    public String getFirstname() {
        return firstname;
    }

    @Override
    public String getLastname() {
        return lastname;
    }

    @Override
    public String getTotalprice() {
        return totalprice;
    }

    @Override
    public Boolean getDepositpaid() {
        return depositpaid;
    }

    @Override
    public BookingImpl.Dates getBookingDates() {
        return null;
    }

    @Override
    public void setBookingid(Long bookingid) {
        this.bookingid = bookingid;
    }
}
