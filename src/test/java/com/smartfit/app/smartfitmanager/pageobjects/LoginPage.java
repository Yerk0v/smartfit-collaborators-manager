package com.smartfit.app.smartfitmanager.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginPage {

    @Autowired
    WebDriver driver;

    public void login(String username, String password) throws InterruptedException {
        driver.findElement(By.id("email")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Thread.sleep(10000);
    }

    public void navigate() {
    }

    public void writeUsername(String string) {
    }

    public void writePassword(String string) {
    }
}
