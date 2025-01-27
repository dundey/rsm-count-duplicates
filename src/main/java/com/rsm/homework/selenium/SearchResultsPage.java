package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage extends BasePage {

    // Locate the first search result container
    private By firstSearchResultContainer = By.xpath("(//div[@data-component-type='s-search-result'])[1]");

    // Title is scoped inside the first listing
    private By titleLink = By.xpath(".//div[@data-cy='title-recipe']//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']/span");

    // Selector for rating stars within the first result
    private By ratingStars = By.xpath(".//a[contains(@aria-label, 'out of 5 stars')]");

    // Selector for the Paperback link within the first result
    private By paperbackLink = By.xpath(".//a[contains(text(),'Paperback')]");

    // Selector for the Paperback price within the first result
    private By paperbackPrice = By.xpath(".//a[contains(text(),'Paperback')]/../following-sibling::div//span[@class='a-offscreen']");

    // Selector to verify delivery to Bulgaria within the first result
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
        return ratingElement.getAttribute("aria-label").replaceAll(",.*", "").trim();
    }

    public boolean isPaperbackListed() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        return !container.findElements(paperbackLink).isEmpty();
    }

    public String getPaperbackPrice() {
        WebElement container = waitForElementVisible(firstSearchResultContainer);
        WebElement priceElement = container.findElement(paperbackPrice);
        return priceElement.getAttribute("innerText").trim();
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
