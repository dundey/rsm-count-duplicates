package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private By cartItemTitle = By.cssSelector(".sc-product-title");
    private By cartItemPrice = By.cssSelector(".sc-price");
    private By giftIndicator = By.cssSelector(".sc-gift-option .sc-gift-option-selected");
    private By cartItems     = By.cssSelector(".sc-list-item");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public CartPage waitForCartPageToLoad() {
        waitForElementVisible(cartItems);
        return this;
    }

    public String getCartItemTitle() {
        return driver.findElement(cartItemTitle).getText().trim();
    }

    public String getCartItemPrice() {
        return driver.findElement(cartItemPrice).getText().trim();
    }

    public boolean isGiftMarked() {
        return !driver.findElements(giftIndicator).isEmpty();
    }

    public int getNumberOfItems() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public boolean isPaperbackInDescription() {
        return getCartItemTitle().toLowerCase().contains("paperback");
    }
}
