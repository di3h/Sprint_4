import config.AppConfig;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.MainPage;
import pages.NewOrderPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class NewOrderPageTest {
    private WebDriver driver;

    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String duration;
    private final String colour;
    private final String comment;
    private final boolean isNewOrderCreated;

    public NewOrderPageTest(String name, String surname, String address, String metro, String phone, String date, String duration, String colour, String comment, boolean isNewOrderCreated) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
        this.isNewOrderCreated = isNewOrderCreated;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Иван", "Иванов", "Абрикосовая 1", "Бульвар Рокоссовского", "89991234567", "31.12.2023", "сутки", "чёрный жемчуг", "домофон не работает", true},
                {"Пётр", "Петров", "Виноградная 2", "Черкизовская", "89997654321", "01.01.2024", "двое суток", "серая безысходность", "", true}
        };
    }

    @Test
    public void newOrderTest() {

        driver = AppConfig.getBrowser();
        driver.get(AppConfig.MAIN_PAGE_URL);

        //клик по кнопке заказа в хедере
        new MainPage(driver).headerOrderButtonClick();

        NewOrderPage page = new NewOrderPage(driver);

        page.editOrderForm1(name, surname, address, metro, phone);
        page.nextButtonClick();
        page.editOrderForm2(date, duration, colour, comment);
        page.orderSubmitButtonClick();
        page.orderConfirmButtonClick();

        assertEquals("Всплывающее окно с сообщением об успешном создании заказа не появилось", isNewOrderCreated, page.isSuccessOrderModalDisplayed());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
