package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

@Epic("Tests for search")
public class SearchTests extends CoreTestCase {

    @Test
    @DisplayName("Search")
    @Description("We open search field and write name of something article")
    @Step("Starting test testSearch")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSearch() {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Object-oriented programming language");
    }

    @Test
    @DisplayName("Cancel search")
    @Description("We open search field and then close it")
    @Step("Starting test testCancelSearch")
    public void testCancelSearch()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForReturnButtonToAppear();
        SearchPageObject.clickReturnSearch();
        SearchPageObject.waitForReturnButtonToDisappear();

    }

    //ex3 refactor
    @Test
    @DisplayName("Cancel search with some article")
    @Description("We open search field, write name of something article and then close it")
    @Step("Starting test testCancelSearchWithSomeArticle")
    public void testCancelSearchWithSomeArticle()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.waitForFindSomeArticle();
        SearchPageObject.cleanSearchLine();
        SearchPageObject.waitForReturnButtonToAppear();
        SearchPageObject.clickReturnSearch();
        SearchPageObject.waitForReturnButtonToDisappear();

    }

    @Test
    @DisplayName("Having at least one search result")
    @Description("We open search field, write name of something and made sure there is at least one search result")
    @Step("Starting test testAmountOfNotEmptySearch")
    public void testAmountOfNotEmptySearch(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        String search_line = "Linkin Park discography";
        SearchPageObject.typeSearchLine(search_line);
        int amount_of_search_results = SearchPageObject.getAmountOfFoundArticles();

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results >0
        );
    }

    @Test
    @DisplayName("Amount Of Empty Search")
    @Description("We open search field, write something not exist and made sure there is not search result")
    @Step("Starting test testAmountOfEmptySearch")
    public void testAmountOfEmptySearch()
    {

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        String search_line = "frgrsgwrgsrg";
        SearchPageObject.typeSearchLine(search_line);
        SearchPageObject.waitForEmptyResultLabel();
        SearchPageObject.assertThereIsNoResultOfSearch();

    }
}
