package praktikum.burger.pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    private WebDriver driver;

    // Кнопка "Выход"
    private final By exitButton = By.xpath(".//li/button[text()='Выход']");
    // Проверочная надпись для перехода в Личный кабинет
    public final By textOnProfilePage = By.xpath(".//nav/p[text()='В этом разделе вы можете изменить свои персональные данные']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }


    @Step("Клик по кнопке 'Выйти'.")
    public void clickOnExitButton() {
        driver.findElement(exitButton).click();

    }

    @Step("Ожидание загрузки страницы личного кабинета с текстом изменения персональных данных.")
    public void waitForLoadProfilePage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(textOnProfilePage));
    }

}

