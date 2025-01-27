package com.rsm.homework.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Objects;
import java.util.Optional;

public class SearchResultsPage extends BasePage {

    private final By firstSearchResultContainer = By.xpath("(//div[@data-component-type='s-search-result'])[1]");
    private final By titleLink = By.xpath(".//div[@data-cy='title-recipe']//h2[@class='a-size-medium a-spacing-none a-color-base a-text-normal']/span");
    private final By ratingStars = By.xpath(".//a[contains(@aria-label, 'out of 5 stars')]");
    private final By paperbackLink = By.xpath(".//a[contains(text(),'Paperback')]");
    private final By paperbackPrice = By.xpath(".//a[contains(text(),'Paperback')]/../following-sibling::div//span[@class='a-offscreen']");
    private final By deliveryToBulgaria = By.xpath(".//span[contains(text(),'Delivers to Bulgaria')]");

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
        String ariaLabel = ratingElement.getDomAttribute("aria-label");
        return Objects.requireNonNullElse(ariaLabel, "").replaceAll(",.*", "").trim();    }

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
