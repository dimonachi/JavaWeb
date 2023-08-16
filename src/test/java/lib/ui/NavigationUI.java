package lib.ui;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class NavigationUI extends MainPageObject {

    public NavigationUI(AppiumDriver driver)
    {
        super(driver);
    }

    private static final String
        MY_LISTS_LINK = "org.wikipedia:id/nav_tab_reading_lists";

    @Step("Clicking on my saved list")
    public void clickMyList()
    {
        this.waitForElementAndClick(
                By.id(MY_LISTS_LINK),
                "Cannot find 'Saved'",
                5
        );
    }
}
