package ee.page;

import ee.element.Booking;
import ee.element.DatePicker;
import ee.type.BookingType;
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
    private DatePicker checkinDatePickerElement;

    @FindBy(id="checkout")
    private DatePicker checkoutDatePickerElement;

    @FindBy(css="#form input[type='button']")
    private WebElementFacade save;

    @FindBy(css = bookingRowCssSelector)
    private List<Booking> bookingElements;

    private int preSaveElementCount;

    @WhenPageOpens
    public void onOpen() {
        waitForRenderedElements(By.cssSelector(bookingRowCssSelector));
    }

    public void fillCreateBookingForm(BookingType bookingType) {
        firstnameInputElement.sendKeys(bookingType.getFirstname());
        lastnameInputElement.sendKeys(bookingType.getLastname());
        totalPriceInputElement.sendKeys(bookingType.getTotalPrice().toString());
        depositDropdownElement.selectByVisibleText(bookingType.getDepositPaid().toString());
        checkinDatePickerElement.selectDate(bookingType.getBookingDates().getCheckin());
        checkoutDatePickerElement.selectDate(bookingType.getBookingDates().getCheckout());
    }

    public WebElementFacade getDepositDropdownElement() {
        return depositDropdownElement;
    }

    public void save() {
        preSaveElementCount = bookingElements.size();
        save.click();
    }

    public boolean bookingIsPresent(BookingType bookingType) {
        return waitForCondition().until(
            webDriver -> preSaveElementCount != bookingElements.size() &&
                bookingElements.stream().anyMatch(booking -> booking.compareTo(bookingType) > 0)
        );
    }

    public void delete(BookingType bookingType) {
        bookingElements.stream().filter(
            booking -> booking.getWrappedElement().getAttribute("id").equals(bookingType.getBookingid().toString())
        ).findFirst()
            .orElseThrow(IllegalArgumentException::new)
            .clickDelete();
    }

    public boolean bookingIsNotPresent(BookingType bookingType) {
        setWaitForTimeout(1000);
        try {
            bookingIsPresent(bookingType);
            return false;
        } catch (TimeoutException e) {
            return true;
        } finally {
            setWaitForTimeout(10000);
        }
    }
}