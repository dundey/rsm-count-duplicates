package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage extends BasePage {

    private By productTitle       = By.id("productTitle");
    private By productSubtitle    = By.id("productSubtitle");
    private By paperbackPrice     = By.cssSelector("#tmm-grid-swatch-PAPERBACK .slot-price .a-size-base.a-color-price");
    private By addToCartButton    = By.id("add-to-cart-button");
    private By giftCheckbox       = By.name("is-gift");

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public ProductPage waitForProductPageToLoad() {
        waitForElementVisible(productTitle);
        return this;
    }

    public String getProductTitle() {
        return driver.findElement(productTitle).getText().trim();
    }

    public String getProductSubtitle() {
        return driver.findElement(productSubtitle).getText().trim();
    }

    public String getPrice() {
        WebElement priceElement = waitForElementVisible(paperbackPrice);
        return priceElement.getAttribute("aria-label").trim();
    }

    public boolean isPaperbackEdition() {
        String subtitle = getProductSubtitle().toLowerCase();
        return subtitle.contains("paperback");
    }

    public ProductPage addToBasket() {
        waitForElementVisible(addToCartButton).click();
        return this;
    }

    public ProductPage markAsGift() {
        clickIfVisible(giftCheckbox);
        return this;
    }

    public CartPage goToCart() {
        driver.get("https://www.amazon.co.uk/gp/cart/view.html");
        return new CartPage(driver);
    }
}
