package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {

    // We locate the first listing
    private By firstSearchResultContainer = By.xpath("(//div[@data-component-type='s-search-result'])[1]");

    // Title is scoped inside the first listing (relative xpath: .//...)
    private By titleLink = By.xpath(".//div[@data-cy='title-recipe']//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']/span");

    // Rating stars also scoped to the same container
    private By ratingStars = By.xpath(".//a[contains(@aria-label, 'out of 5 stars')]");

    // Paperback link is inside the same container
    private By paperbackLink = By.xpath(".//a[contains(text(),'Paperback')]");

    // Paperback price is found near the "Paperback" link
    private By paperbackPrice = By.xpath(".//a[contains(text(),'Paperback')]/../following-sibling::div//span[@class='a-offscreen']");

    // Delivery info to Bulgaria also inside same container
    private By deliveryToBulgaria = By.xpath(".//span[contains(text(),'Delivers to Bulgaria')]");

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public SearchResultsPage waitForResults() {
        waitForElementVisible(firstSearchResultContainer);
        return this;
    }

    public String getFirstResultTitle() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        return container.findElement(titleLink).getText().trim();
    }

    public String getRatingStars() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        WebElement ratingElement = container.findElement(ratingStars);
        // The rating is in aria-label; we remove trailing text after a comma
        return ratingElement.getAttribute("aria-label").replaceAll(",.*", "").trim();
    }

    public boolean isPaperbackListed() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        return !container.findElements(paperbackLink).isEmpty();
    }

    public String getPaperbackPrice() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        WebElement priceElement = container.findElement(paperbackPrice);
        String price = priceElement.getAttribute("innerText").trim();
        return price;
    }

    public boolean isDeliveryToBulgariaAvailable() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        return !container.findElements(deliveryToBulgaria).isEmpty();
    }

    public ProductPage clickOnPaperbackVersion() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        container.findElement(paperbackLink).click();
        return new ProductPage(driver);
    }
}
