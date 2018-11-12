package ee.element;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;

@ImplementedBy(DatePickerElementImpl.class)
public interface DatePickerElement extends WebElementFacade {
    void selectDate(String date);
}
