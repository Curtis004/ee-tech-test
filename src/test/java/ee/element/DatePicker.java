package ee.element;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;

@ImplementedBy(DatePickerImpl.class)
public interface DatePicker extends WebElementFacade {
    void selectDate(String date);
}
