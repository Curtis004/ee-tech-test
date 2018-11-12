package ee.mother;

import com.github.javafaker.Faker;
import ee.domain.Booking;
import ee.domain.BookingImpl;
import net.serenitybdd.core.Serenity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static ee.SerenitySession.BOOKING;

public class BookingTypeMother {
    private Faker faker = new Faker();

    public Booking<Double> booking() {
        BookingImpl bookingImpl = new BookingImpl();
        bookingImpl.setFirstname(faker.name().firstName());
        bookingImpl.setLastname(faker.name().lastName());
        bookingImpl.setDepositpaid(faker.bool().bool());
        bookingImpl.setTotalprice(faker.number().randomDouble(2, 100, 1000));

        Date from = faker.date().future(5, TimeUnit.DAYS);
        bookingImpl.addBookingDates(
            faker.date().future(5, TimeUnit.DAYS),
            faker.date().future(5, TimeUnit.DAYS, from)
        );

        Serenity.setSessionVariable(BOOKING).to(bookingImpl);

        return bookingImpl;
    }
}
