import config.AppConfig;
import org.junit.After;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.MainPage;

@RunWith(Parameterized.class)
public class MainPageTest {

    private WebDriver driver;
    private final int questionId;

    public MainPageTest(int questionId) {
        this.questionId = questionId;
    }

    @Parameterized.Parameters
    public static Object[][] getMainPageTestData() {
        return new Object[][] {
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        };
    }

    @Test
    public void isAnswerVisibleAfterClickOnQuestion() {

        driver = AppConfig.getBrowser();
        driver.get(AppConfig.MAIN_PAGE_URL);
        MainPage page = new MainPage(driver);

        //передаем id в локатор вопроса и ответа
        page.setFaqQuestionId(questionId);

        //делаем клик по вопросу
        page.faqQuestionClick();

        //проверяем, что ответ отображается
        assertTrue("После нажатия на вопрос не отображается ответ "+questionId, page.isFaqAnswerDisplayed());

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
