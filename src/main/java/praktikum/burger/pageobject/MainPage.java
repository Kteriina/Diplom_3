package praktikum.burger.pageobject;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private WebDriver driver;

    // Кнопка "Личный кабинет"
    private final By personalProfile = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    // Кнопка "Войти в аккаунт"
    private final By signIn = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");
    //Заголовок секции "Булки"
    private final By bunsSection = By.xpath(".//h2[text()='Булки']");
    //Заголовок секции "Соусы"
    private final By saucesSection = By.xpath(".//h2[text()='Соусы']");
    //Заголовок секции "Начинки"
    private final By fillingsSection = By.xpath(".//h2[text()='Начинки']");
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");
    // переменная для определения поведения скрола
    private final String scrollBehavior = "arguments[0].scrollIntoView({ behavior: 'smooth', block: 'start' });";
    // Локатор кнопки "Булки" в активном состоянии
    private final By activeBunsButton = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Булки']");
    // Локатор кнопки "Соусы" в активном состоянии
    private final By activeSausesButton = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Соусы']");
    // Локатор кнопки "Начинки" в активном состоянии
    private final By activeFillingsButton = By.xpath("//div[contains(@class, 'tab_tab_type_current')]//span[text()='Начинки']");

    public MainPage(WebDriver driver) {
        this.driver = driver;


    }

    @Step("Клик по кнопке 'Войти в аккаунт'")
    public void clickOnLoginButton() {
        driver.findElement(signIn).click();
    }

    @Step("Клик по кнопке 'Личный Кабинет'")
    public void clickOnProfileButton() {
        driver.findElement(personalProfile).click();
    }

    @Step("Прокрутка до раздела 'Булки'")
    public void scrollToBunsSection() {
        WebElement bunsSectionElement = driver.findElement(bunsSection);
        Actions actions = new Actions(driver);
        actions.moveToElement(bunsSectionElement).perform();
        ((JavascriptExecutor) driver).executeScript(scrollBehavior, bunsSectionElement);
    }

    @Step("Проверка активности раздела 'Булки'")
    public boolean isBunsSectionActive() {
        WebElement bunsDiv = driver.findElement(activeBunsButton);
        return bunsDiv.isDisplayed();
    }

    @Step("Прокрутка до раздела 'Соусы'")
    public void scrollToSaucesSection() {
        WebElement saucesSectionElement = driver.findElement(saucesSection);
        Actions actions = new Actions(driver);
        actions.moveToElement(saucesSectionElement).perform();
        ((JavascriptExecutor) driver).executeScript(scrollBehavior, saucesSectionElement);
    }

    @Step("Проверка активности раздела 'Соусы'")
    public boolean isSaucesSectionActive() {
        WebElement saucesDiv = driver.findElement(activeSausesButton);
        return saucesDiv.isDisplayed();
    }

    @Step("Прокрутка до раздела 'Начинки'")
    public void scrollToFillingsSection() {
        WebElement fillingsSectionElement = driver.findElement(fillingsSection);
        Actions actions = new Actions(driver);
        actions.moveToElement(fillingsSectionElement).perform();
        ((JavascriptExecutor) driver).executeScript(scrollBehavior, fillingsSectionElement);
    }

    @Step("Проверка активности раздела 'Начинки'")
    public boolean isFillingsSectionActive() {
        WebElement fillingsDiv = driver.findElement(activeFillingsButton);
        return fillingsDiv.isDisplayed();
    }
    @Step("Ожидание главная страница, и загрузка текста 'Соберите бургер")
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }


    @Step("Ожидание загрузки страницы полностью, анимация исчезнет.")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
        waitDocReady();
    }

    @Step("Ожидание загрузки страницы полностью, дополнительный метод ожидания.")
    public void waitDocReady() {
        new WebDriverWait(driver, Duration.ofSeconds(20))
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }

}

