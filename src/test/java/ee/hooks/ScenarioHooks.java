package ee.hooks;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.restassured.RestAssured;

public class ScenarioHooks {
    @Before
    public void beforeScenario() {
        RestAssured.baseURI = "http://hotel-test.equalexperts.io";
    }

    @After
    public void afterScenario() {
        // Remove created resources.
    }
}
