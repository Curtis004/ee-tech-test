package ee.element;

import ee.domain.Booking;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;

@ImplementedBy(BookingElementImpl.class)
public interface BookingElement extends WebElementFacade, Comparable<Booking> {
    void clickDelete();

    String getFirstName();
}
