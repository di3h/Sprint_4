package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class NewOrderPage {

    private WebDriver driver;

    //поле Имя
    private By inputName = By.xpath(".//div[@class='Order_Form__17u6u']//input[@placeholder='* Имя']");
    //поле Фамилия
    private By inputSurname = By.xpath(".//div[@class='Order_Form__17u6u']//input[@placeholder='* Фамилия']");
    //поле Адрес
    private By inputAddress = By.xpath(".//div[@class='Order_Form__17u6u']//input[@placeholder='* Адрес: куда привезти заказ']");
    //поле Телефон
    private By inputPhone = By.xpath(".//div[@class='Order_Form__17u6u']//input[@placeholder='* Телефон: на него позвонит курьер']");
    //кнопка Далее
    private By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Далее']");
    //поле Дата доставки
    private By inputDate = By.xpath(".//div[@class='Order_Form__17u6u']//input[@placeholder='* Когда привезти самокат']");
   //поле Комментарий
    private By inputComment = By.xpath(".//div[@class='Order_Form__17u6u']//input[@placeholder='Комментарий для курьера']");
    //кнопка Заказать
    private By buttonSubmit = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM' and text()='Заказать']");
    //кнопка подтверждения заказа
    private By buttonOrderConfirm = By.xpath(".//div[@class='Order_Modal__YZ-d3']//button[text()='Да']");

    //всплывающее окно с сообщением об успешном создании заказа
    private By successOrderModal = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and contains(text(), 'Заказ оформлен')]");

    public NewOrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //заполнение первой части формы заказа
    public void editOrderForm1(String name, String surname, String address, String metro, String phone) {
        driver.findElement(inputName).sendKeys(name);
        driver.findElement(inputSurname).sendKeys(surname);
        driver.findElement(inputAddress).sendKeys(address);
        orderFormMetroClick(metro);
        driver.findElement(inputPhone).sendKeys(phone);
    }

    //выбор станции метро
    public void orderFormMetroClick(String metro) {
        driver.findElement(By.className("select-search")).click();
        assertTrue(driver.findElement(By.xpath(".//div[text()='"+metro+"']")).isEnabled());
        driver.findElement(By.xpath(".//div[text()='"+metro+"']")).click();
    }

    //выбор цвета
    public void orderFormColourClick(String colour) {
        driver.findElement(By.xpath(".//label[text()='"+colour+"']/input")).click();
    }

    //выбор продолжительности аренды
    public void orderFormDurationClick(String duration) {
        driver.findElement(By.className("Dropdown-control")).click();
        assertTrue(driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='"+duration+"']")).isEnabled());
        driver.findElement(By.xpath(".//div[@class='Dropdown-menu']/div[text()='"+duration+"']")).click();
    }

    //нажатие на кнопку Далее
    public void nextButtonClick() {
        driver.findElement(buttonNext).click();
    }

    //нажатие на кнопку Заказать в форме заказа
    public void orderSubmitButtonClick() {
        driver.findElement(buttonSubmit).click();
    }

    //нажатие на кнопку подтвердения создания заказа
    public void orderConfirmButtonClick() {
        assertTrue(driver.findElement(buttonOrderConfirm).isEnabled());
        driver.findElement(buttonOrderConfirm).click();
    }

    //заполнение второй части формы заказа
    public void editOrderForm2(String date, String duration, String colour, String comment) {
        orderFormDurationClick(duration);
        orderFormColourClick(colour);
        driver.findElement(inputComment).sendKeys(comment);
        driver.findElement(inputDate).sendKeys(date);
    }

    //проверка отображения всплывающего окна с сообщением об успешном создании заказа
    public boolean isSuccessOrderModalDisplayed() {
        assertTrue(driver.findElement(successOrderModal).isDisplayed());
        return driver.findElement(successOrderModal).isDisplayed();
    }
}
