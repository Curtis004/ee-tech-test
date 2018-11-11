package ee.steps.libraries;

import cucumber.api.java.en.Given;
import ee.mother.BookingTypeMother;
import ee.type.BookingType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;

import static ee.SerenitySession.BOOKING;
import static net.serenitybdd.rest.RestRequests.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class PreconditionSteps {
    private BookingTypeMother bookingTypeMother = new BookingTypeMother();

    @Given("^I have a previous booking$")
    public BookingType createBooking() {
        BookingType bookingType = bookingTypeMother.booking();

        Response response = given()
            .contentType("application/json")
            .accept("application/json")
            .body(bookingType)
        .when()
            .post("/booking")
        .then()
            .body("bookingid", is(notNullValue()))
            .body("booking", is(notNullValue()))
            .extract().response();

        // Creating a bookingType does not return a canonical object.. for whatever reason.
        BookingType returnedBookingType = response.jsonPath().getObject("booking", BookingType.class);
        returnedBookingType.setBookingid(response.jsonPath().getLong("bookingid"));

        Serenity.setSessionVariable(BOOKING).to(returnedBookingType);

        return returnedBookingType;
    }
}
