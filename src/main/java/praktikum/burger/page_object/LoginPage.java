package praktikum.burger.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;

    //Поле "Email"
    private final By emailField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_text input_size_default']/input[@name='name']");
    //Поле "Пароль"
    private final By passwordField = By.xpath(".//div[@class='input pr-6 pl-6 input_type_password input_size_default']/input[@name='Пароль']");
    //кнопка Войти
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //Кнопка "Зарегистрироваться"
    private final By registerButton = By.xpath(".//a[@href='/register' and text()='Зарегистрироваться']");
    //Кнопка "Восстановить пароль"
    private final By forgotPasswordButton = By.xpath(".//a[@href='/forgot-password' and text()='Восстановить пароль']");
    //Логотип "Stellar Burgers"
    public final By stellarBurgersLogo = By.xpath(".//div/a[@href='/']");
    //Кнопка "Конструктор"
    private final By constructorButton = By.xpath(".//a/p[text()='Конструктор']");
    //Заголовок "Вход"
    public final By entrance = By.xpath(".//main/div/h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик по кнопке 'Войти' ")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();

    }

    @Step("Клик по кнопке 'Зарегистрироваться' ")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();

    }

    @Step("Клик по кнопке 'Восстановить пароль'")
    public void clickOnForgotPasswordButton() {
        driver.findElement(forgotPasswordButton).click();
    }

    @Step("Клик по кнопке 'Конструктор'")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Клик по кнопке 'Stellar Burgers' ")
    public void clickOnStellarBurgersLogo() {
        driver.findElement(stellarBurgersLogo).click();
    }
    @Step("Авторизация пользователя")
    public void authorization(String email, String password) {
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        clickOnLoginButton();
    }
    @Step("Ожидание загрузки страницы с текстом 'Вход' ")
    public void waitForLoadEntrance() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(entrance));
    }





}
