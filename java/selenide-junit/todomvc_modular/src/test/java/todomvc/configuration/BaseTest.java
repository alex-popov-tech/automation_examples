package todomvc.configuration;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Rule;
import todomvc.core.AllurePublisher;

import static java.io.File.separator;

/**
 * Created by Alex on 12/25/2016.
 */
public class BaseTest {

  public final static String ROOT_REPORT = "target" + separator + "site" + separator + "allure-maven-plugin" + separator;

  @Rule
  public AllurePublisher publisher = AllurePublisher.always().saveScreenAndHtml().create();

  @BeforeClass
  public static void baseBeforeClass() {
    Configuration.browser = "chrome";
    Configuration.reportsFolder = ROOT_REPORT + "screenshotsAndHtml";
  }

}