import org.example.HomePage;
import org.example.Item;
import org.example.LauncherPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests {
    @Test
    public void verifyIfSearchTermShowsRelevantResults() {

        WebDriver webDriver = new ChromeDriver();
        try { //Arrange
            String searchItem = "Product";
            String searchKey = "Product";
            LauncherPage launcherPage = new LauncherPage(webDriver);
            launcherPage.navigateTo("https://web-playground.ultralesson.com/");

            //Act
            HomePage homepage = new HomePage(webDriver);
            homepage.search(searchItem);
            List<Item> searchItems = homepage.getSearchItems();

            //Assert
            Assert.assertEquals(4, searchItems.size());
            Assert.assertTrue(searchItems.stream().allMatch(item -> item.getName().contains(searchKey)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            webDriver.quit();
        }
    }

    @Test
    public void verifySearchUnavailableProduct() {
        // Arrange
        String unavailableProduct = "Unobtainium Widget";
        WebDriver webDriver = null;

        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        // Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(unavailableProduct);
        List<Item> searchItems = homepage.getSearchItems();

        // Assert
        Assert.assertTrue(searchItems.isEmpty());
    }

    @Test
    public void verifyBrandSearchListsOnlyBrandItems() {
        // Arrange
        String brandName = "Nike";
        WebDriver webDriver = null;

        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        // Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(brandName);
        List<Item> searchItems = homepage.getSearchItems();

        // Assert
        Assert.assertTrue(searchItems.stream().allMatch(item -> item.getName().contains(brandName)));
    }

    @Test
    public void verifySearchResultCountMatchesDisplayedItems() {
        // Arrange
        String searchItem = "Shoes";
        WebDriver webDriver = null;

        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        // Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(searchItem);
        List<Item> searchItems = homepage.getSearchItems();
        int itemCountDisplayed = homepage.getItemCount(); // Assume getItemCount method returns the number displayed on the page

        // Assert
        Assert.assertEquals(searchItems.size(), itemCountDisplayed);
    }

}