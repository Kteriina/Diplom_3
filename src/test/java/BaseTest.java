import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import praktikum.burger.client.UserClient;
import praktikum.burger.util.WebDriverUtil;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class BaseTest extends WebDriverUtil{

    protected static final String BASE_URL = "https://stellarburgers.nomoreparties.site";
    protected WebDriver driver;
    protected static String accessToken;
    protected static String email;
    protected static String password;
    protected static String name;

    @Before
    public void setUp() {
        driver = getWebDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.get(BASE_URL);

    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

        if ((!Objects.equals(accessToken, "")) && (accessToken != null)) {

            Response deletion = UserClient.delete(accessToken);
            deletion.then().log().all().assertThat().statusCode(202).and().body("success", Matchers.is(true)).body("message", Matchers.is("User successfully removed"));
        }
        else {
            System.out.println("User was not created");
        }



}
}
