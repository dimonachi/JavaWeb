package tests;

import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

@Epic("Tests for Saved list")
public class MyListsTest extends CoreTestCase {

    @Test
    @Features(value = {@Feature(value = "Saved list"), @Feature(value = "Article")})
    @DisplayName("Save the article to my list")
    @Description("We open an article and saved it in my saved list with name: '{name_of_folder}'")
    @Step("Starting test testSaveFirstArticleToMyList")
    @Severity(value = SeverityLevel.CRITICAL)
    public void testSaveFirstArticleToMyList(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.returnFromArticle();
        ArticlePageObject.returnFromArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);

    }

    //ex 5 refactor
    @Test
    @Features(value = {@Feature(value = "Saved list"), @Feature(value = "Article")})
    @DisplayName("Save two article to my list")
    @Description("We open an article and saved it in my saved list with name: '{name_of_folder}' then repeat it for another article")
    @Step("Starting test testSaveTwoArticleToMyList")
    public void testSaveTwoArticleToMyList(){

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipStartPage();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        ArticlePageObject.waitForTitleElement();
        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Test1";
        ArticlePageObject.addArticleToMyList(name_of_folder);
        ArticlePageObject.returnFromArticle();

        SearchPageObject.clickByArticleWithSubstring("Island in Indonesia");

        ArticlePageObject.waitForTitleElement();
        ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToMyExistList(name_of_folder);
        ArticlePageObject.returnFromArticle();
        ArticlePageObject.returnFromArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyList();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(article_title);

        SearchPageObject.clickByArticleWithSubstring("Island in Indonesia");


    }


}
