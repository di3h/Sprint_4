package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;

import static junit.framework.Assert.assertTrue;

public class MainPage {
    private WebDriver driver;

    //Вопрос выпадающего списка "Вопросы о важном"
    private By faqQuestion;

    //Ответ выпадающего списка "Вопросы о важном"
    private By faqAnswer;

    //кнопка Заказать вверху страницы
    private By headerOrderButton = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    //кнопка Заказать внизу страницы
    private By footerOrderButton = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button[text()='Заказать']");

    //конструктор класса
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }


    //установка id для локаторов вопроса и ответа
    public void setFaqQuestionId(int questionId) {
        faqQuestion = By.id("accordion__heading-"+questionId);
        faqAnswer = By.id("accordion__panel-"+questionId);
    }

    //клик по вопросу
    public void faqQuestionClick() {
        //проверка, что блок с вопросом кликабелен
        Assert.assertTrue(driver.findElement(faqQuestion).isEnabled());

        //скролл к блоку с вопросом
        WebElement element = driver.findElement(faqQuestion);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        //клик по вопросу
        driver.findElement(faqQuestion).click();
    }

    //проверка отображения ответа
    public boolean isFaqAnswerDisplayed() {
        Assert.assertTrue(driver.findElement(faqQuestion).isDisplayed());
        return driver.findElement(faqAnswer).isDisplayed();
    }

    //клик по кнопке Заказать в хедере
    public void headerOrderButtonClick() {
        //проверка, что кнопка кликабельна
        driver.findElement(headerOrderButton).isEnabled();

        //клик по кнопке
        driver.findElement(headerOrderButton).click();
    }

    //клик по кнопке Заказать в футере
    public void footerOrderButtonClick() {
        //проверка, что кнопка кликабельна
        driver.findElement(footerOrderButton).isEnabled();

        //скролл к кнопке
        WebElement element = driver.findElement(footerOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        //клик по кнопке
        driver.findElement(footerOrderButton).click();
    }

}
