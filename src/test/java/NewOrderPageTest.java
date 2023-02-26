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

import static org.junit.Assert.assertTrue;

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
    private final String OrderButtonType;

    public NewOrderPageTest(String name, String surname, String address, String metro, String phone, String date, String duration, String colour, String comment, String OrderButtonType) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phone = phone;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
        this.OrderButtonType = OrderButtonType;
    }

    @Parameterized.Parameters
    public static Object[][] getNewOrderPageTestData() {
        return new Object[][] {
                {"Иван", "Иванов", "Абрикосовая 1", "Бульвар Рокоссовского", "89991234567", "31.12.2023", "сутки", "чёрный жемчуг", "домофон не работает", "headerOrderButton"},
                {"Пётр", "Петров", "Виноградная 2", "Черкизовская", "89997654321", "01.01.2024", "двое суток", "серая безысходность", "", "footerOrderButton"}
        };
    }

    @Test
    public void newOrderTest() {

        driver = AppConfig.getBrowser();
        driver.get(AppConfig.MAIN_PAGE_URL);

        //клик по кнопке заказа
        if(OrderButtonType.equals("headerOrderButton")) {
            new MainPage(driver).headerOrderButtonClick();
        } else {
            new MainPage(driver).footerOrderButtonClick();
        }

        NewOrderPage page = new NewOrderPage(driver);

        page.editOrderForm1(name, surname, address, metro, phone);
        page.nextButtonClick();
        page.editOrderForm2(date, duration, colour, comment);
        page.orderSubmitButtonClick();
        page.orderConfirmButtonClick();

        assertTrue("Всплывающее окно с сообщением об успешном создании заказа не появилось", page.isSuccessOrderModalDisplayed());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

}
