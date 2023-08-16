package lib.ui;


import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject{

    private static final String
        TITLE = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.drawerlayout.widget.DrawerLayout/android.widget.FrameLayout/android.widget.FrameLayout[1]/android.view.ViewGroup/android.view.ViewGroup/android.webkit.WebView/android.webkit.WebView/android.view.View/android.view.View[1]/android.widget.TextView[1]",
        FOOTER_ELEMENT = "//*[@text='View article in browser']",
        SAVE_BUTTON = "org.wikipedia:id/page_save",
        ADD_TO_MY_LIST_BUTTON = "//*[@text = 'Add to another reading list']",
        CREATE_NEW_LIST_BUTTON = "//*[@text = 'Create new']",
        MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON = "android:id/button1",
        RETURN_ARTICLE_BUTTON = "Navigate up",
        NAME_OF_FOLDER_TPL = "//*[@text = '{NameOfFolder}']";
    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    @Step("Waiting for title on the article page")
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.xpath(TITLE),"Cannot find article title on page", 15);

    }

    /* TEMPLATES METHODS */
    @Step("Get name of folder")
    private static String getNameOfFolder(String name_of_folder)
    {
        return NAME_OF_FOLDER_TPL.replace("{NameOfFolder}", name_of_folder);
    }
    /* TEMPLATES METHODS */
    @Step("Get article title")
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        screenshot(this.takeScreenshot("article_title"));
        return title_element.getAttribute("text");
    }

    @Step("Swiping to footer on article page")
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of article",
                20
        );
    }

    @Step("Adding the article ot my list")
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find 'Save' button",
                5
        );
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find 'Save' button",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to another reading list'",
                5
        );
        this.waitForElementAndClick(
                By.xpath(CREATE_NEW_LIST_BUTTON),
                "Cannot find 'Create new'",
                5
        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot clean input element",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );
        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Cannot find 'OK' button",
                5
        );
    }

    @Step("Returning from article page")
    public void returnFromArticle()
    {
        this.waitForElementAndClick(
                By.id(RETURN_ARTICLE_BUTTON),
                "Cannot find '<-' button",
                5
        );
    }
    @Step("Adding article to my exist list")
    public void addArticleToMyExistList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find 'Save' button",
                5
        );
        this.waitForElementAndClick(
                By.id(SAVE_BUTTON),
                "Cannot find 'Save' button",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "Cannot find 'Add to another reading list'",
                5
        );

        String name_of_folder_xpath = getNameOfFolder(name_of_folder);
        this.waitForElementAndClick(By.xpath(name_of_folder_xpath), "Cannot find and click this name of folder " + name_of_folder, 10);

    }

    @Step("Asserting title of article present")
    public void assertTitleOfArticlePresent()
    {
        this.assertElementPresent(
                By.xpath(TITLE),
                "text",
                "Cannot find title of article"
        );
    }
}
