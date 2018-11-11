package ee.element;

import net.serenitybdd.core.pages.WebElementFacadeImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerImpl extends WebElementFacadeImpl implements DatePicker {
    private final WebDriver driver;

    public DatePickerImpl(WebDriver driver, ElementLocator locator, WebElement webElement, long implicitTimeoutInMilliseconds) {
        super(driver, locator, webElement, implicitTimeoutInMilliseconds);

        this.driver = driver;
    }

    @Override
    public void selectDate(String date) {
        try {
            getElement().click();

            Calendar desiredDateCalender = Calendar.getInstance();
            desiredDateCalender.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));

            int desiredMonth = desiredDateCalender.get(Calendar.MONTH);
            int desiredDay = desiredDateCalender.get(Calendar.DAY_OF_MONTH);

            Calendar currentDateCalendar = Calendar.getInstance();
            currentDateCalendar.setTime(new SimpleDateFormat("MMMM").parse(
                driver.findElement(By.id("ui-datepicker-div"))
                    .findElement(
                        By.className("ui-datepicker-month")
                    ).getText()
            ));

            if(desiredMonth == currentDateCalendar.get(Calendar.MONTH)) {
                driver.findElements(By.cssSelector("td[data-handler='selectDay']")).get(desiredDay - 1).click();
            } else if (desiredMonth < currentDateCalendar.get(Calendar.MONTH)) {
                driver.findElement(By.className("ui-datepicker-prev")).click();
                driver.findElements(By.cssSelector("td[data-handler='selectDay']")).get(desiredDay - 1).click();
            } else if ((desiredMonth > currentDateCalendar.get(Calendar.MONTH))) {
                driver.findElement(By.className("ui-datepicker-next")).click();
                driver.findElements(By.cssSelector("td[data-handler='selectDay']")).get(desiredDay - 1).click();
            }
        } catch (ParseException e) {
            throw new RuntimeException("Unable to parse date " + date + " into a date object.", e);
        }
    }
}
