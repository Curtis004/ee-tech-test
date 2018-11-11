package ee.type;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BookingType {
    protected Long bookingid;

    protected String firstname;

    protected String lastname;

    protected Double totalprice;

    protected Boolean depositpaid;

    protected Dates bookingdates;

    public Long getBookingid() {
        return bookingid;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Double getTotalPrice() {
        return totalprice;
    }

    public Boolean getDepositPaid() {
        return depositpaid;
    }

    public Dates getBookingDates() {
        return bookingdates;
    }

    public class Dates {
        protected String checkin;

        protected String checkout;

        public String getCheckin() {
            return checkin;
        }

        public String getCheckout() {
            return checkout;
        }
    }

    public void setBookingid(Long bookingid) {
        this.bookingid = bookingid;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalprice = totalPrice;
    }

    public void setDepositPaid(Boolean depositPaid) {
        this.depositpaid = depositPaid;
    }

    public void addBookingDates(Dates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public void addBookingDates(String from, String until) {
        this.bookingdates = new Dates();
        bookingdates.checkin = from;
        bookingdates.checkout = until;
    }

    public void addBookingDates(Date from, Date until) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        this.bookingdates = new Dates();
        bookingdates.checkin = simpleDateFormat.format(from);
        bookingdates.checkout = simpleDateFormat.format(until);
    }
}