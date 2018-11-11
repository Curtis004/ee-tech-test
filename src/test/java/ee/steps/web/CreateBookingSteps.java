package ee.steps.web;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import ee.mother.BookingTypeMother;
import ee.page.HotelBookingPage;
import net.thucydides.core.annotations.Steps;

public class CreateBookingSteps {
    @Steps
    private BookingTypeMother bookingTypeMother;

    private HotelBookingPage hotelBookingPage;

    @Given("^I fill out the hotel booking with my information$")
    public void iFillOutTheHotelBookingWithMyInformation() {
        hotelBookingPage.open();
        hotelBookingPage.fillCreateBookingForm(bookingTypeMother.booking());
    }

    @And("^I specify that I (.*) be paying a deposit$")
    public void iSpecifyThatIConditionBePayingADeposit(String payingDeposit) {
        if (!payingDeposit.equals("will") && !payingDeposit.equals("will not")) {
            throw new IllegalArgumentException();
        }

        hotelBookingPage.getDepositDropdownElement().selectByVisibleText(
            payingDeposit.equals("will") ? "true" : "false"
        );
    }

    @When("^I save that information$")
    public void iSaveThatInformation() {
        hotelBookingPage.save();
    }
}
