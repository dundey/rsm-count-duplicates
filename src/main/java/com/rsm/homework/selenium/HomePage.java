package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private final By amazonLogoContainer = By.id("nav-logo");
    private final By searchTextBox = By.id("twotabsearchtextbox");
    private final By searchButton  = By.id("nav-search-submit-button");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage openAmazonCoUk() {
        driver.get("https://www.amazon.co.uk/");
        waitForElementVisible(amazonLogoContainer);
        return this;
    }

    public SearchResultsPage searchForBook(String bookName) {
        waitForElementVisible(searchTextBox).clear();
        driver.findElement(searchTextBox).sendKeys(bookName);
        driver.findElement(searchButton).click();
        return new SearchResultsPage(driver);
    }
}
