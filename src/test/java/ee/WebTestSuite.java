package ee;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = {
        "src/test/resources/features/booking/Booking Validation.feature",
        "src/test/resources/features/booking/Browse Bookings.feature",
        "src/test/resources/features/booking/Create Booking.feature",
        "src/test/resources/features/booking/Delete Booking.feature"
    },
    tags = "@Web",
    glue = {
        "ee.hooks",
        "ee.steps.web",
        "ee.steps.libraries"
    }
)
public class WebTestSuite {
}
