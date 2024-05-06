package praktikum.burger.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver driver;
    // кнопка Войти
    private final By signInButton = By.xpath(".//a[@class='Auth_link__1fOlj']");
    // Заголовок "Восстановление пароля"
    public final By recoverPassword = By.xpath(".//main/div/h2[text()='Восстановление пароля']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Клик на Войти в форме восставноления пароля")
    public void clickSignIn() {
        driver.findElement(signInButton).click();
    }
    @Step("Ожидание загрузки страницы с восстановления пароля.")
    public void waitForLoadedRecoverPassword() {
        new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(recoverPassword));
    }
}

