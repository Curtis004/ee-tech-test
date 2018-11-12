package ee.page;

import ee.domain.Booking;
import ee.element.BookingElement;
import ee.element.BookingElementImpl;
import ee.element.DatePickerElement;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.annotations.locators.SmartFieldDecorator;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.time.temporal.ChronoUnit.SECONDS;

@DefaultUrl("http://hotel-test.equalexperts.io/")
public class HotelBookingPage extends PageObject {
    private static Logger logger = LoggerFactory.getLogger(HotelBookingPage.class);

    private final String bookingRowCssSelector = "#bookings .row[id]";

    @FindBy(id = "firstname")
    private WebElementFacade firstnameInputElement;

    @FindBy(id = "lastname")
    private WebElementFacade lastnameInputElement;

    @FindBy(id = "totalprice")
    private WebElementFacade totalPriceInputElement;

    @FindBy(id = "depositpaid")
    private WebElementFacade depositDropdownElement;

    @FindBy(id = "checkin")
    private DatePickerElement checkinDatePickerElementElement;

    @FindBy(id = "checkout")
    private DatePickerElement checkoutDatePickerElementElement;

    @FindBy(css = "#form input[type='button']")
    private WebElementFacade save;

    @FindBy(css = bookingRowCssSelector)
    private List<BookingElement> bookingElements;

    @WhenPageOpens
    public void onOpen() {
        try {
            waitForRenderedElements(By.cssSelector(bookingRowCssSelector));
        } catch (TimeoutException e) {
            logger.warn("Unable to find booking rows.. assuming none have been added and carrying on.");
        }
    }

    public void fillCreateBookingForm(Booking booking) {
        firstnameInputElement.sendKeys(booking.getFirstname());
        lastnameInputElement.sendKeys(booking.getLastname());
        totalPriceInputElement.sendKeys(booking.getTotalprice() != null ? booking.getTotalprice().toString() : "");
        depositDropdownElement.selectByVisibleText(booking.getDepositpaid() != null ? booking.getDepositpaid().toString() : "true");
        checkinDatePickerElementElement.selectDate(booking.getBookingDates().getCheckin());
        checkoutDatePickerElementElement.selectDate(booking.getBookingDates().getCheckout());
    }

    public WebElementFacade getDepositDropdownElement() {
        return depositDropdownElement;
    }

    public void save() {
        save.click();
    }

    public boolean bookingIsPresent(Booking booking) {
        return bookingIsPresent(booking, 10);
    }

    private boolean bookingIsPresent(Booking booking, int seconds) {
        return waitForCondition()
            .pollingEvery(300, TimeUnit.MILLISECONDS)
            .withTimeout(seconds, TimeUnit.SECONDS)
            .until(
                webDriver -> bookingElements.stream()
                    .filter(
                        bookingElement -> bookingElement.getFirstName().equals(booking.getFirstname()) // Filter by first name to reduce the stream size.
                    ).anyMatch(
                        bookingElement -> bookingElement.compareTo(booking) > 0
                    )
            );
    }

    public void delete(Booking booking) {
        bookingElements.stream().filter(
            bookingElement -> bookingElement.getWrappedElement().getAttribute("id").equals(booking.getBookingid().toString())
        ).findFirst()
            .orElseThrow(IllegalArgumentException::new)
            .clickDelete();
    }

    public boolean bookingIsNotPresent(Booking booking) {
        try {
            bookingIsPresent(booking, 2);
            return false;
        } catch (TimeoutException e) {
            return true;
        }
    }
}