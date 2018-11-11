package ee.element;

import ee.type.BookingType;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;

@ImplementedBy(BookingImpl.class)
public interface Booking extends WebElementFacade, Comparable<BookingType> {
    void clickDelete();
}
