package ee.page;

import ee.domain.Booking;
import ee.element.BookingElement;
import ee.element.DatePickerElement;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.annotations.WhenPageOpens;
import net.thucydides.core.pages.PageObject;
import org.openqa.selenium.TimeoutException;

import java.util.List;

@DefaultUrl("http://hotel-test.equalexperts.io/")
public class HotelBookingPage extends PageObject {
    private final String bookingRowCssSelector = "#bookings .row[id]";

    @FindBy(id="firstname")
    private WebElementFacade firstnameInputElement;

    @FindBy(id="lastname")
    private WebElementFacade lastnameInputElement;

    @FindBy(id="totalprice")
    private WebElementFacade totalPriceInputElement;

    @FindBy(id="depositpaid")
    private WebElementFacade depositDropdownElement;

    @FindBy(id="checkin")
    private DatePickerElement checkinDatePickerElementElement;

    @FindBy(id="checkout")
    private DatePickerElement checkoutDatePickerElementElement;

    @FindBy(css="#form input[type='button']")
    private WebElementFacade save;

    @FindBy(css = bookingRowCssSelector)
    private List<BookingElement> bookingElementElements;

    private int preSaveElementCount;

    @WhenPageOpens
    public void onOpen() {
        waitForRenderedElements(By.cssSelector(bookingRowCssSelector));
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
        preSaveElementCount = bookingElementElements.size();
        save.click();
    }

    public boolean bookingIsPresent(Booking booking) {
        return waitForCondition().until(
            webDriver -> preSaveElementCount != bookingElementElements.size() &&
                bookingElementElements.stream().anyMatch(bookingElement -> bookingElement.compareTo(booking) > 0)
        );
    }

    public void delete(Booking booking) {
        bookingElementElements.stream().filter(
            bookingElement -> bookingElement.getWrappedElement().getAttribute("id").equals(booking.getBookingid().toString())
        ).findFirst()
            .orElseThrow(IllegalArgumentException::new)
            .clickDelete();
    }

    public boolean bookingIsNotPresent(Booking booking) {
        setWaitForTimeout(1000);
        try {
            bookingIsPresent(booking);
            return false;
        } catch (TimeoutException e) {
            return true;
        } finally {
            setWaitForTimeout(10000);
        }
    }
}