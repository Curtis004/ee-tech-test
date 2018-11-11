package ee.steps.libraries;

import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import net.serenitybdd.core.Serenity;

import static ee.SerenitySession.FAKE_BOOKING_REFERENCE;

public class CommonSteps {
    @Given("^I do not have a previous booking$")
    public void iDoNotHaveAPreviousBooking() {
    }

    @But("^I have a booking reference$")
    public void iHaveABookingReference() {
        Serenity.setSessionVariable(FAKE_BOOKING_REFERENCE).to(-100);
    }
}
