package ee.mother;

import com.github.javafaker.Faker;
import ee.type.BookingType;
import net.serenitybdd.core.Serenity;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import static ee.SerenitySession.BOOKING;

public class BookingTypeMother {
    private Faker faker = new Faker();

    public BookingType booking() {
        BookingType bookingType = new BookingType();
        bookingType.setFirstname(faker.name().firstName());
        bookingType.setLastname(faker.name().lastName());
        bookingType.setDepositPaid(faker.bool().bool());
        bookingType.setTotalPrice(faker.number().randomDouble(2, 100, 1000));

        Date from = faker.date().future(5, TimeUnit.DAYS);
        bookingType.addBookingDates(
            faker.date().future(5, TimeUnit.DAYS),
            faker.date().future(5, TimeUnit.DAYS, from)
        );

        Serenity.setSessionVariable(BOOKING).to(bookingType);

        return bookingType;
    }
}
