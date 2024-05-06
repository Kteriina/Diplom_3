package praktikum.burger.page_object;


import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private final WebDriver driver;

    // Кнопка "Личный кабинет"
    private final By personalProfile = By.xpath(".//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']");
    // Кнопка "Войти в аккаунт"
    private final By signIn = By.xpath(".//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']");

    private final By bunsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Булки']");
    //Кнопка перехода "Соуса"
    private final By saucesButton = By.xpath("//span[@class='text text_type_main-default'][text()='Соусы']");
    //Кнопка перехода "Начинки"
    private final By fillingsButton = By.xpath("//span[@class='text text_type_main-default'][text()='Начинки']");
    //картинка с "Булкой" для проверки видимости раздела
    public By bunsImg = By.xpath(".//img[@alt='Краторная булка N-200i']");
    //текст заголовка "Булки" для проверки видимости раздела
    public By bunsText = By.xpath(".//h2[text()='Булки']");
    //картинка с "Соусом" для проверки видимости раздела
    public By saucesImg = By.xpath(".//p[text()='Соус с шипами Антарианского плоскоходца']");
    //картинка с "Начинкой" для проверки видимости раздела
    public By fillingsImg = By.xpath(".//img[@alt='Плоды Фалленианского дерева']");
    //текст для проверки видимости на главной странице
    public By textBurgerMainPage = By.xpath(".//section/h1[text()='Соберите бургер']");


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

    @Step("Клик по кнопке 'Булки'")
    public void clickOnBunsButton() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(bunsButton).click();

    }

    @Step("Клик по кнопке 'Соуса'")
    public void clickOnSaucesButton() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(saucesButton).click();
    }

    @Step("Клик по кнопке 'Начинки'")
    public void clickOnFillingButton() throws InterruptedException {
        Thread.sleep(500);
        driver.findElement(fillingsButton).click();
    }

    @Step("Проверка отображения раздела с булками")
    public void checkVisibilityOfBunsSection(){

        driver.findElement(bunsImg).isDisplayed();
        driver.findElement(bunsText).isDisplayed();
    }
    @Step("Проверка отображения раздела с соусами")
    public void checkVisibilityOfSaucesSection(){

        driver.findElement(saucesImg).isDisplayed();
    }

    @Step("Проверка отображения раздела с начинками")
    public void checkVisibilityOfFillingsSection(){

        driver.findElement(fillingsImg).isDisplayed();
    }
    @Step("Ожидание главная страница, и загрузка текста 'Соберите бургер")
    public void waitForLoadMainPage() {
        new WebDriverWait(driver, 15)
                .until(ExpectedConditions.visibilityOfElementLocated(textBurgerMainPage));
    }


    @Step("Ожидание загрузки страницы полностью, анимация исчезнет.")
    public void waitForInvisibilityLoadingAnimation() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.invisibilityOfElementLocated
                        (By.xpath(".//img[@src='./static/media/loading.89540200.svg' and @alt='loading animation']")));
        waitDocReady();
    }

    @Step("Ожидание загрузки страницы полностью, дополнительный метод ожидания.")
    public void waitDocReady() {
        new WebDriverWait(driver, 20)
                .until((ExpectedCondition<Boolean>) wd ->
                        ((JavascriptExecutor) wd)
                                .executeScript("return document.readyState")
                                .equals("complete"));
    }
}

