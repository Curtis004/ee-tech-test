package ee.domain;

public interface Booking<T> {
    Long getBookingid();

    String getFirstname();

    String getLastname();

    T getTotalprice();

    Boolean getDepositpaid();

    BookingImpl.Dates getBookingDates();

    void setBookingid(Long bookingid);
}
