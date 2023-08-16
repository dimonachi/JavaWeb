package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject {

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    private static final String
        FOLDER_BY_NAME_TPL = "//*[@text ='{FOLDER_NAME}']",
        ARTICLE_BY_TITLE_TPL = "//*[@text ='{TITLE}']";

    @Step("Getting name of folder")
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }
    @Step("Opening '{name_of_folder}'")
    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folder_name_xpath),
                "Cannot find folder by name " + name_of_folder,
                5
        );
    }

    @Step("Waiting appear title '{article_title}'")
    public void waitForArticleAppearByTitle (String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementPresent(By.xpath(article_xpath), "Cannot find saved article by title " + article_title ,15);
    }

    @Step("Waiting disappear title '{article_title}'")
    public void waitForArticleToDisappearByTitle (String article_title)
    {
        String article_xpath = getFolderXpathByName(article_title);
        this.waitForElementNotPresent(By.xpath(article_xpath), "Saved article still present withe title " + article_title ,15);
    }

    @Step("Swiping article to delete")
    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleAppearByTitle(article_title);
        String article_xpath = getFolderXpathByName(article_title);
        this.swipeElementToLeft(
                By.xpath(article_xpath),
                "Cannot find article"
        );
        this.waitForArticleToDisappearByTitle(article_title);
    }

}
