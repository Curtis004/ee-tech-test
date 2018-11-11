package ee.steps.web;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import ee.mother.BookingTypeMother;
import ee.page.HotelBookingPage;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Steps;

import static ee.SerenitySession.BOOKING;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DeleteBookingSteps {
    @Steps
    private BookingTypeMother bookingTypeMother;

    private HotelBookingPage hotelBookingPage;

    @When("^I delete that booking$")
    public void iDeleteThatBooking() {
        hotelBookingPage.delete(Serenity.sessionVariableCalled(BOOKING));
    }

    @Then("^I should see that my booking is removed$")
    public void iShouldSeeThatMyBookingIsRemoved() {
        assertThat(hotelBookingPage.bookingIsNotPresent(Serenity.sessionVariableCalled(BOOKING)), is(true));
    }
}
