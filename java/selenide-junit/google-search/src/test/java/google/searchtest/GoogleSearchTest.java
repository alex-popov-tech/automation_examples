package google.searchtest;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

/**
 * Created by Alex on 7/11/2017.
 */
public class GoogleSearchTest {

  @BeforeClass
  public static void baseBeforeClass() {
    Configuration.browser = "chrome";
  }

  @Test
  public void searchAndFollowFirstResultTest() {

    open("http://google.com/ncr");

    $("#lst-ib").setValue("Selenium automates browsers").pressEnter();

    $$(".g .r").filter(visible).shouldHaveSize(10)
            .first().shouldHave(text("Selenium - Web Browser Automation"))
            .find("a")
            .click();

    Wait().until(urlToBe("http://www.seleniumhq.org/"));

  }

}
