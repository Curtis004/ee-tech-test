package ee.type;

import ee.domain.Booking;
import ee.domain.BookingImpl;
import ee.domain.Dates;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class BookingDataType implements Booking<String> {
    private Long bookingid;

    private String firstname;

    private String lastname;

    private String totalprice;

    private Boolean depositpaid;

    private Dates bookingdates;

    private String fromto;

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
    public Dates getBookingDates() {
        return parseFromToIntoDates();
    }

    @Override
    public void setBookingid(Long bookingid) {
        this.bookingid = bookingid;
    }

    @Override
    public void setDepositpaid(Boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    private Dates parseFromToIntoDates() {
        try {
            Dates dates = new Dates();
            String[] fromTo = fromto.split(" to ");

            if (!fromTo[0].contains("today") || !fromTo[1].contains("today")) {
                dates.setCheckin(fromTo[0]);
                dates.setCheckout(fromTo[1]);

                return dates;
            }

            Calendar fromCalendar = Calendar.getInstance();
            fromCalendar.setTime(new Date());

            Calendar toCalendar = Calendar.getInstance();
            toCalendar.setTime(new Date());

            fromCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(fromTo[0].substring("today".length())));
            toCalendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(fromTo[1].substring("today".length())));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dates.setCheckin(simpleDateFormat.format(fromCalendar.getTime()));
            dates.setCheckout(simpleDateFormat.format(toCalendar.getTime()));

            return dates;
        } catch (IndexOutOfBoundsException e) {
            return new Dates();
        }
    }
}
