import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.junit.After;
import praktikum.burger.page_object.LoginPage;
import praktikum.burger.page_object.MainPage;
import praktikum.burger.page_object.ForgotPasswordPage;
import praktikum.burger.page_object.RegisterPage;
import praktikum.burger.util.WebDriverUtil;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@RunWith(Parameterized.class)
public class LoginTest {
    private WebDriver driver;
    private String driverType;
    private final static String EMAIL = "katy@yandex.ru";
    private final static String PASSWORD = "katy12katy";

    public LoginTest(String driverType) {
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
    @DisplayName("Вход по кнопке 'Войти в аккаунт'.")
    @Description("Проверка кнопки 'Войти в аккаунт' на главной странице")
    public void enterByLoginButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(EMAIL, PASSWORD);
        //loginPage.clickOnLoginButton();
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход по кнопке 'Личный Кабинет'.")
    @Description("Проверка кнопки 'Личный Кабинет' на хедере главной страницы.")
    public void enterByPersonalAccountButtonTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации.")
    @Description("Проверка входа через форму регистрации.")
    public void enterByRegistrationFormTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnLoginButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnRegisterButton();
        RegisterPage registerPage = new RegisterPage(driver);
        String name = randomAlphanumeric(4, 8);
        String email = randomAlphanumeric(6, 10) + "@yandex.ru";;
        String password = randomAlphanumeric(6, 10);
        registerPage.registration(name, email, password);
        loginPage.waitForLoadEntrance();
        loginPage.authorization(email, password);
        mainPage.waitForLoadMainPage();
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля.")
    @Description("Проверка входа через форму восстановления пароля.")
    public void enterByPasswordRecoveryFormatTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOnProfileButton();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickOnForgotPasswordButton();
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.waitForLoadedRecoverPassword();
        forgotPasswordPage.clickSignIn();
        loginPage.authorization(EMAIL, PASSWORD);
        mainPage.waitForLoadMainPage();
    }

    @After
    public void tearDown() {
        // Закрытие браузера
        driver.quit();
    }
}
