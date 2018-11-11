package ee.element;

import ee.type.BookingType;
import net.serenitybdd.core.pages.WebElementFacadeImpl;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.Objects;

public class BookingImpl extends WebElementFacadeImpl implements Booking {
    @FindBy(css = "div:nth-child(1)")
    private WebElement firstnameElement;

    @FindBy(css = "div:nth-child(2)")
    private WebElement lastnameElement;

    @FindBy(css = "div:nth-child(3)")
    private WebElement totalPriceElement;

    @FindBy(css = "div:nth-child(4)")
    private WebElement depositPaidElement;

    @FindBy(css = "div:nth-child(5)")
    private WebElement checkinElement;

    @FindBy(css = "div:nth-child(6)")
    private WebElement checkoutElement;

    @FindBy(css = "input[type='button']")
    private WebElement deleteButton;

    public BookingImpl(WebDriver driver, ElementLocator locator, WebElement webElement, long implicitTimeoutInMilliseconds) {
        super(driver, locator, webElement, implicitTimeoutInMilliseconds);

        PageFactory.initElements(new DefaultElementLocatorFactory(getElement()), this);
    }

    public void clickDelete() {
        deleteButton.click();
    }

    @Override
    public int compareTo(@NotNull BookingType bookingType) {
        if (Objects.equals(firstnameElement.getText(), bookingType.getFirstname()) &&
            Objects.equals(lastnameElement.getText(), bookingType.getLastname()) &&
            Objects.equals(totalPriceElement.getText(), bookingType.getTotalPrice().toString()) &&
            Objects.equals(depositPaidElement.getText(), bookingType.getDepositPaid().toString()) &&
            Objects.equals(checkinElement.getText(), bookingType.getBookingDates().getCheckin()) &&
            Objects.equals(checkoutElement.getText(), bookingType.getBookingDates().getCheckout())
        ) {
            return 1;
        }

        return 0;
    }
}
