package com.rsm.homework.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AmazonBookTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    public void testAmazonBookPurchaseFlow() {
        HomePage homePage = new HomePage(driver).openAmazonCoUk();
        homePage.acceptCookiesIfPresent();
        homePage.dismissGlowToasterIfPresent();

        SearchResultsPage resultsPage = homePage
                .selectBooksCategory()
                .searchForBook("harry potter and the cursed child 1 and 2")
                .waitForResults();

        // Verify the title of the first search result
        String firstTitle = resultsPage.getFirstResultTitle();
        Assertions.assertTrue(
                firstTitle.toLowerCase().contains("harry potter and the cursed child"),
                "Title mismatch or no results for the expected book"
        );

        // Verify the rating stars
        String ratingStars = resultsPage.getRatingStars();
        Assertions.assertFalse(ratingStars.isEmpty(), "Rating stars not found");
        Assertions.assertTrue(
                ratingStars.startsWith("4.5") || ratingStars.startsWith("4.6"),
                "Unexpected rating stars value: " + ratingStars
        );

        // Verify the paperback option
        Assertions.assertTrue(resultsPage.isPaperbackListed(), "No paperback option");

        // Verify the paperback price
        String paperbackPrice = resultsPage.getPaperbackPrice();
        Assertions.assertEquals("£8.99", paperbackPrice, "Paperback price mismatch");

        // Verify delivery to Bulgaria
        Assertions.assertTrue(resultsPage.isDeliveryToBulgariaAvailable(), "Delivery to Bulgaria not available");

        // Click on the Paperback version
        ProductPage productPage = resultsPage.clickOnPaperbackVersion();
        productPage.waitForProductPageToLoad();

//        // Verify product page title
//        Assertions.assertTrue(
//                productPage.getProductTitle().contains("Harry Potter and the Cursed Child"),
//                "Incorrect product title on Product Page"
//        );
//
//        // Verify price on Product Page matches
//        Assertions.assertEquals(paperbackPrice, productPage.getPrice(), "Price mismatch on Product Page");
//
//        // Verify it's the Paperback edition
//        Assertions.assertTrue(productPage.isPaperbackEdition(), "Not paperback edition on Product Page");
//
//        // Add to basket and mark as gift
//        productPage.addToBasket().markAsGift();
//
//        // Proceed to Cart Page
//        CartPage cartPage = productPage.goToCart().waitForCartPageToLoad();
//
//        // Verify cart item title
//        Assertions.assertTrue(cartPage.getCartItemTitle().contains("Harry Potter and the Cursed Child"), "Cart title mismatch");
//
//        // Verify cart item price
//        Assertions.assertEquals(paperbackPrice, cartPage.getCartItemPrice(), "Cart price mismatch");
//
//        // Verify edition is Paperback in cart
//        Assertions.assertTrue(cartPage.isPaperbackInDescription(), "Edition in cart not paperback");
//
//        // Verify item is marked as gift
//        Assertions.assertTrue(cartPage.isGiftMarked(), "Item not marked as gift");
//
//        // Verify the number of items in the cart
//        Assertions.assertEquals(1, cartPage.getNumberOfItems(), "Cart should have 1 item");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
