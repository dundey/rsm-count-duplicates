package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    // Cookie and glow-toaster locators
    private final By cookieForm      = By.id("sp-cc");
    private final By cookieAcceptBtn = By.id("sp-cc-accept");
    private final By glowDismissBtn  = By.xpath("//span[contains(@class,'glow-toaster-button-dismiss')]//input[@data-action-type='DISMISS']");

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    protected WebElement waitForElementVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void clickIfVisible(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        if (!elements.isEmpty() && elements.get(0).isDisplayed()) {
            elements.get(0).click();
        }
    }

    public void acceptCookiesIfPresent() {
        if (!driver.findElements(cookieForm).isEmpty()) {
            clickIfVisible(cookieAcceptBtn);
        }
    }

    public void dismissGlowToasterIfPresent() {
        clickIfVisible(glowDismissBtn);
    }
}
