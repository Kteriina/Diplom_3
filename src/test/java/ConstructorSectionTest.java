import io.qameta.allure.junit4.DisplayName;
import jdk.jfr.Description;
import org.junit.Test;
import praktikum.burger.pageobject.MainPage;
import static org.junit.Assert.assertTrue;
public class ConstructorSectionTest extends BaseTest {

    @Test
    @DisplayName("Проверка активности раздела 'Булки'.")
    @Description("Проверка активности раздела 'Булки' после скролла.")
    public void checkActivityOfBunsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToBunsSection();
        assertTrue(mainPage.isBunsSectionActive());
    }

    @Test
    @DisplayName("Проверка активности раздела 'Соусы'.")
    @Description("Проверка активности раздела 'Соусы' после скролла.")
    public void checkActivityOfSaucesSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToSaucesSection();
        assertTrue(mainPage.isSaucesSectionActive());
    }

    @Test
    @DisplayName("Проверка активности раздела 'Начинки'.")
    @Description("Проверка активности раздела 'Начинки' после скролла.")
    public void checkActivityOfFillingsSectionTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.scrollToFillingsSection();
        assertTrue(mainPage.isFillingsSectionActive());
    }

}
