import config.AppConfig;
import org.junit.After;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MainPage;


public class MainPageTest {

    private WebDriver driver;

    @Test
    public void isAnswerVisibleAfterClickOnQuestion() {

        driver = AppConfig.getBrowser();
        driver.get(AppConfig.MAIN_PAGE_URL);

        MainPage page = new MainPage(driver);

        //получаем количество вопросов в списке "Вопросы о важном"
        int faqQuestionsCount = page.getFaqQuestionsCount();

        //тестируем каждый вопрос
        for (int i=0; i<faqQuestionsCount; i++) {
            //передаем id в локатор вопроса и ответа
            page.setFaqQuestionId(i);

            //делаем клик по вопросу
            page.faqQuestionClick();

            //проверяем, что ответ отображается
            assertTrue("После нажатия на вопрос не отображается ответ "+i, page.isFaqAnswerDisplayed());
        }
    }

    @Test
    public void clickHeaderOrderButton() {

        driver = AppConfig.getBrowser();
        driver.get(AppConfig.MAIN_PAGE_URL);

        //клик по кнопке заказа в хедере
        new MainPage(driver).headerOrderButtonClick();

        //проверяем, что url изменился
        assertEquals("После нажатия на кнопку Заказать в хедере не открылась страница заказа", driver.getCurrentUrl(), AppConfig.NEW_ORDER_PAGE_URL);
    }

    @Test
    public void clickFooterOrderButton() {

        driver = AppConfig.getBrowser();
        driver.get(AppConfig.MAIN_PAGE_URL);

        //клик по кнопке заказа в футере
        new MainPage(driver).footerOrderButtonClick();

        //проверяем, что url изменился
        assertEquals("После нажатия на кнопку Заказать в футере не открылась страница заказа", driver.getCurrentUrl(), AppConfig.NEW_ORDER_PAGE_URL);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
