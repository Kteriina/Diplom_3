package praktikum.burger.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {

    private WebDriver driver;

    // Имя
    private final By nameField = By.xpath(".//fieldset[1]//input[@class='text input__textfield text_type_main-default']");
    // Email
    private final By emailField = By.xpath(".//fieldset[2]//input[@class='text input__textfield text_type_main-default']");
    // Пароль
    private final By passwordField = By.xpath(".//fieldset[3]//input[@class='text input__textfield text_type_main-default']");
    // Зарегистрироваться
    private final By registerButton = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa' and text()='Зарегистрироваться']");
    // Войти
    private final By signInButton = By.xpath(".//a[@class='Auth_link__1fOlj' and text()='Войти']");
    //Сообщение о некорректном пароле
    public final By errorPassworMessage = By.xpath(".//p[text()='Некорректный пароль']");
    // Текст заголовка "Регистрация" для проверки перехода на страницу регистрации
    public final By registerText = By.xpath(".//div/h2[text()='Регистрация']");


    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Регистрация пользователя")
    public void registration(String name, String email, String password) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();

    }


    @Step("Проверка сообщения о некорректном пароле")
    public boolean checkInvalidPassword() {
        return driver.findElement(errorPassworMessage).isDisplayed();
    }
    @Step("Выставлено ожидание загрузки страницы регистрации через текст 'Регистрация' ")
    public void waitForLoadRegisterPage() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(registerText));
    }



}

