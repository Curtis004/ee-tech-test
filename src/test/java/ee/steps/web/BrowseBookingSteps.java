package ee.steps.web;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ee.page.HotelBookingPage;
import net.serenitybdd.core.Serenity;

import static ee.SerenitySession.BOOKING;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class BrowseBookingSteps {
    private HotelBookingPage hotelBookingPage;

    @When("^I land on the booking application$")
    public void iLandOnTheBookingApplication() {
        hotelBookingPage.open();
    }

    @Then("^I should see my booking$")
    public void iShouldSeeMyBooking() {
        assertThat(
            "Unable to find the booking created.",
            hotelBookingPage.bookingIsPresent(Serenity.sessionVariableCalled(BOOKING)), is(true)
        );
    }
}
