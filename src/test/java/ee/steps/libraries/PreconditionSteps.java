package ee.steps.libraries;

import cucumber.api.java.en.Given;
import ee.domain.Booking;
import ee.domain.BookingImpl;
import ee.mother.BookingTypeMother;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import static ee.SerenitySession.BOOKING;
import static ee.SerenitySession.FAKE_BOOKING_REFERENCE;
import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PreconditionSteps {
    private BookingTypeMother bookingTypeMother = new BookingTypeMother();

    @Step("Create a booking in the backend")
    public Booking createBooking() {
        Booking bookingImpl = bookingTypeMother.booking();

        Response response = given()
            .contentType("application/json")
            .accept("application/json")
            .body(bookingImpl)
        .when()
            .post("/booking")
        .then()
            .body("bookingid", is(notNullValue()))
            .body("booking", is(notNullValue()))
            .extract().response();

        // Creating a bookingImpl does not return a canonical object.. for whatever reason.
        Booking returnedBooking = response.jsonPath().getObject("booking", BookingImpl.class);
        returnedBooking.setBookingid(response.jsonPath().getLong("bookingid"));

        Serenity.setSessionVariable(BOOKING).to(returnedBooking);

        return returnedBooking;
    }

    @Step("Create a fake booking reference")
    public void createFakeBookingRef() {
        Serenity.setSessionVariable(FAKE_BOOKING_REFERENCE).to(-100);
    }

    @Step("Do nothing")
    public void doNothing() {
        // Syntactic sugar.
    }
}
