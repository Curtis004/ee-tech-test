package ee.steps.web;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import ee.page.HotelBookingPage;
import ee.type.BookingDataType;

import java.util.List;

public class BookingValidationSteps {
    private HotelBookingPage hotelBookingPage;

    @Given("^I fill out the hotel booking with the following information:$")
    public void iFillOutTheHotelBookingWithTheFollowingInformation(List<BookingDataType> bookingDataTypes) {
        if (bookingDataTypes.size() > 1) throw new IllegalArgumentException();

        hotelBookingPage.open();
        hotelBookingPage.fillCreateBookingForm(bookingDataTypes.get(0));
    }

    @Then("^I should get an appropriate error message akin to \"([^\"]*)\"$")
    public void iShouldGetAnAppropriateErrorMessageAkinTo(String message) {
    }
}
