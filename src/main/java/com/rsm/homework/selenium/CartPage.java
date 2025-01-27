package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    private final By cartItemTitle = By.cssSelector(".sc-product-title");
    private final By cartItemPrice = By.cssSelector(".sc-price");
    private final By giftCheckbox = By.cssSelector(".sc-gift-option-container input[type='checkbox']");
    private final By cartItems     = By.cssSelector(".sc-list-item");

    // Selector for the "Paperback" label within a cart item
    private final By paperbackLabel = By.cssSelector(".a-size-small.sc-product-binding.a-text-bold");

    // Selector for the "In stock" label within a cart item
    private final By inStockLabel = By.cssSelector(".a-size-small.a-color-success.sc-product-availability");

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
        List<WebElement> checkboxes = driver.findElements(giftCheckbox);
        for (WebElement checkbox : checkboxes) {
            if (checkbox.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public int getNumberOfItems() {
        List<WebElement> items = driver.findElements(cartItems);
        return items.size();
    }

    public boolean isPaperbackInDescription() {
        List<WebElement> labels = driver.findElements(paperbackLabel);
        for (WebElement label : labels) {
            if (label.getText().trim().equalsIgnoreCase("Paperback")) {
                return true;
            }
        }
        return false;
    }

    public boolean isInStock() {
        List<WebElement> stockLabels = driver.findElements(inStockLabel);
        for (WebElement label : stockLabels) {
            if (label.getText().trim().equalsIgnoreCase("In stock")) {
                return true;
            }
        }
        return false;
    }
}
