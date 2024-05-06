
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import praktikum.burger.page_object.LoginPage;
import praktikum.burger.page_object.MainPage;
import praktikum.burger.page_object.ProfilePage;
import praktikum.burger.util.WebDriverUtil;


@RunWith(Parameterized.class)
public class TransitionIntoProfileTest {
    private WebDriver driver;
    private String driverType;
    private final static String EMAIL = "qwerty123@yandex.ru";
    private final static String PASSWORD = "qwerty123";

    public TransitionIntoProfileTest(String driverType) {
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
    @DisplayName("Переход в личный кабинет.")
    @Description("Проверка перехода по клику на 'Личный кабинет'")
    public void transitionToProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        Assert.assertTrue("Страница авторизации не отобразилась", driver.findElement(loginPage.entrance).isDisplayed());
    }

    @Test
    @DisplayName("Переход в конструктор из личного кабинета.")
    @Description("Проверка перехода на вкладку 'Конструктор' из страницы авторизации пользователя")
    public void transitionToConstructorFromProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForInvisibilityLoadingAnimation();
        mainPage.clickOnProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnConstructorButton();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Переход  в конструктор из личного кабинете не прошел", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Клик по логотипу 'Stellar Burgers'.")
    @Description("Проверка перехода в конструктор при нажатии на логотип 'Stellar Burgers'.")
    public void transitionToStellarBurgersFromProfilePageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.clickOnStellarBurgersLogo();
        mainPage.waitForLoadMainPage();
        Assert.assertTrue("Конструктор при клике на логотип не загрузился", driver.findElement(mainPage.textBurgerMainPage).isDisplayed());
    }

    @Test
    @DisplayName("Выход из аккаунта")
    @Description("Проверка выхода по кнопке 'Выйти' в личном кабинете.")
    public void exitFromProfileTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
        mainPage.clickOnProfileButton();
        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitForLoadProfilePage();
        profilePage.clickOnExitButton();
        mainPage.waitForInvisibilityLoadingAnimation();
        Assert.assertTrue("Не удалось выйти из аккаунта", driver.findElement(loginPage.entrance).isDisplayed());
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
