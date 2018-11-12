package ee.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingImpl implements Booking<Double>{
    private Long bookingid;

    private String firstname;

    private String lastname;

    private Double totalprice;

    private Boolean depositpaid;

    private Dates bookingdates;

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
    public Double getTotalprice() {
        return totalprice;
    }

    @Override
    public Boolean getDepositpaid() {
        return depositpaid;
    }

    @Override
    public Dates getBookingDates() {
        return bookingdates;
    }

    @Override
    public void setBookingid(Long bookingid) {
        this.bookingid = bookingid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalprice(Double totalprice) {
        this.totalprice = totalprice;
    }

    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public void addBookingDates(Date from, Date until) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Dates dates = new Dates();
        dates.setCheckin(simpleDateFormat.format(from));
        dates.setCheckout(simpleDateFormat.format(until));

        this.bookingdates = dates;
    }
}