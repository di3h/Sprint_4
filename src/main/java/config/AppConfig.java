package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AppConfig {
    public static final String MAIN_PAGE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static final String NEW_ORDER_PAGE_URL = "https://qa-scooter.praktikum-services.ru/order";

    public static WebDriver getBrowser() {
        return new ChromeDriver();
    }

}
