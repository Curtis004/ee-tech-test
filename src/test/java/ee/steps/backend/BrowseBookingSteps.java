package ee.steps.backend;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;

import static ee.SerenitySession.FAKE_BOOKING_REFERENCE;
import static net.serenitybdd.rest.SerenityRest.when;

public class BrowseBookingSteps {
    private Response response;

    @When("^I try to retrieve that booking$")
    public void iTryToRetrieveThatBooking() {
        response = when()
            .get("/booking/{bookingid}", Serenity.sessionVariableCalled(FAKE_BOOKING_REFERENCE).toString())
        .then()
            .extract().response();
    }

    @Then("^I should see that that booking cannot be found$")
    public void iShouldSeeThatThatBookingCannotBeFound() {
        response.then()
            .statusCode(404);
    }
}
