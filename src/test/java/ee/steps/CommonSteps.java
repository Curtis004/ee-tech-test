package ee.steps;

import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import ee.steps.libraries.PreconditionSteps;
import net.serenitybdd.core.Serenity;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.Steps;

import static ee.SerenitySession.FAKE_BOOKING_REFERENCE;

public class CommonSteps {
    @Steps
    private PreconditionSteps preconditionSteps;

    @Given("^I do not have a previous booking$")
    public void iDoNotHaveAPreviousBooking() {
        preconditionSteps.doNothing();
    }

    @Given("^I have a previous booking$")
    public void iHaveAPreviousBooking() {
        preconditionSteps.createBooking();
    }

    @But("^I have a booking reference$")
    public void iHaveABookingReference() {
        preconditionSteps.createFakeBookingRef();
    }
}
