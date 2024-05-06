import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.burger.page_object.MainPage;
import org.openqa.selenium.WebDriver;
import praktikum.burger.util.WebDriverUtil;

@RunWith(Parameterized.class)
public class ConstructorSectionTest {
    private WebDriver driver;
    private String driverType;

    public ConstructorSectionTest(String driverType) {
        this.driverType = driverType;
    }

    @Before
    public void startUp() {
        driver = WebDriverUtil.initializeDriver(driverType);
        WebDriverUtil.navigateToUrl(driver, "https://stellarburgers.nomoreparties.site/");
    }

    @Parameterized.Parameters(name = "Результаты проверок браузера: {0}")
    public static Object[][] getDataDriver() {
        return new Object[][]{
                {"chromedriver"},
                {"yandexdriver"},
        };
    }

    @Test
    @DisplayName("Переход в раздел 'Булки'.")
    @Description("Проверка перехода в раздел 'Булки', и появление картинки с булкой.")
    public void transitionToBunsInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.clickOnBunsButton();
        mainPage.checkVisibilityOfBunsSection();
    }

    @Test
    @DisplayName("Переход в раздел 'Соусы'.")
    @Description("Проверка перехода в раздел 'Соусы', и появление картинки с соусом.")
    public void transitionToSaucesInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnSaucesButton();
        mainPage.checkVisibilityOfSaucesSection();
    }

    @Test
    @DisplayName("Переход в раздел 'Начинки'.")
    @Description("Проверка перехода в раздел 'Начинки', и появление картинки с начинкой.")
    public void transitionToFillingsInConstructorTest() throws InterruptedException {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnFillingButton();
        mainPage.checkVisibilityOfFillingsSection();
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
