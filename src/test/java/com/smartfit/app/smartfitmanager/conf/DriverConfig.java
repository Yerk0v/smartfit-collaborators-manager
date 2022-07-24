package com.smartfit.app.smartfitmanager.conf;

//import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class DriverConfig {

    @Bean
    public WebDriver driver() {
        System.setProperty("webdriver.chrome.driver", "/Users/Yerko/Downloads/chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        //driver.manage().timeouts().setScriptTimeout(30, 1);
        driver.get("http://localhost:8080/");
        return driver;
    }
}
