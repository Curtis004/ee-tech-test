package ee.steps.backend;

import cucumber.api.PendingException;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ee.type.BookingType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;

import static ee.SerenitySession.BOOKING;
import static ee.SerenitySession.FAKE_BOOKING_REFERENCE;
import static net.serenitybdd.rest.SerenityRest.given;

public class DeleteBookingSteps {
    private String basicAuthUser = "admin";

    private String basicAuthPassword = "password123";

    private Response response;

    private boolean enableAuth = true;

    @When("^I delete that booking$")
    public void iDeleteThatBooking() {
        if (Serenity.sessionVariableCalled(FAKE_BOOKING_REFERENCE) != null) {
            Integer fakeBookingRef = Serenity.sessionVariableCalled(FAKE_BOOKING_REFERENCE);
            deleteBooking(fakeBookingRef.toString());
        } else if (Serenity.sessionVariableCalled(BOOKING) != null) {
            BookingType bookingType = Serenity.sessionVariableCalled(BOOKING);
            deleteBooking(bookingType.getBookingid().toString());
        } else {
            throw new RuntimeException("No booking specified to delete.");
        }
    }

    @Then("^I should see an error stating that booking doesn't exist$")
    public void iShouldSeeAnErrorStatingThatBookingDoesntExist() {
        response.then()
            .statusCode(404);
    }

    @Then("^I should see an error stating that I'm not authenticated$")
    public void iShouldSeeAnErrorStatingThatImNotAuthenticated() {
        response.then()
            .statusCode(401);
    }

    @Then("^I should see an error stating that I'm incorrectly authenticated$")
    public void iShouldSeeAnErrorStatingThatImIncorrectlyAuthenticated() {
        response.then()
            .statusCode(403);
    }

    @But("^I am not authenticated$")
    public void iAmNotAuthenticated() {
        this.enableAuth = false;
    }

    @But("^I am incorrectly authenticated$")
    public void iAmIncorrectlyAuthenticated() {
        basicAuthUser = "incorrect";
        basicAuthPassword = "incorrect";
    }

    private void deleteBooking(String bookingId) {
        RequestSpecification requestSpecification = given();

        if (enableAuth) {
            requestSpecification = requestSpecification.auth().preemptive().basic(basicAuthUser, basicAuthPassword);
        }

        response = requestSpecification.when()
            .delete("/booking/{bookingId}", bookingId)
            .then()
            .extract().response();
    }
}
