import org.example.HomePage;
import org.example.Item;
import org.example.LauncherPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class SearchTests {
    @Test
    public void verifyIfSearchTermShowsRelevantResults() {
        //Arrange
        String searchItem = "Product";
        String searchKey = "Product";
        WebDriver webDriver = null; //Dummy WebDriver declaration
        LauncherPage launcherPage = new LauncherPage(webDriver);
        launcherPage.navigateTo("https://web-playground.ultralesson.com/");

        //Act
        HomePage homepage = new HomePage(webDriver);
        homepage.search(searchItem);
        List<Item> searchItems = homepage.getSearchItems();

        //Assert
        Assert.assertEquals(4, searchItems.size());
        Assert.assertTrue(searchItems.stream().allMatch(item -> item.getName().contains(searchKey)));
    }
}