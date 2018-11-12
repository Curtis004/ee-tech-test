package ee.steps.backend;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ee.type.BookingDataType;

import java.util.List;

/**
 * Marked as pending as there is no validation and didn't want to invest more time.
 */
public class BookingValidationSteps {
    @Given("^I fill out the hotel booking with the following information:$")
    public void iFillOutTheHotelBookingWithTheFollowingInformation(List<BookingDataType> bookingDataTypes) {
        throw new PendingException();
    }

    @When("^I save that booking information$")
    public void iSaveThatBookingInformation() {
        throw new PendingException();
    }

    @Then("^I should get an appropriate error message akin to \"([^\"]*)\"$")
    public void iShouldGetAnAppropriateErrorMessageAkinTo(String message) {
        throw new PendingException();
    }
}
