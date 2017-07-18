package todomvc.core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.Wait;
import static todomvc.core.CustomConditions.ajaxCompleted;

/**
 * Created by Alex on 12/26/2016.
 */
public class Helpers {

  public static ElementsCollection mapToInnerElements(ElementsCollection elements, By locator) {
    return $$(elements.stream().map(e -> e.find(locator)).collect(Collectors.toList()));
  }

  public static ElementsCollection mapToInnerElements(ElementsCollection elements, String css) {
    return mapToInnerElements(elements, byCssSelector(css));
  }

  public static void waitUntilAjaxComplete() {
    waitUntilAjaxComplete(Configuration.timeout);
  }

  public static void waitUntilAjaxComplete(long timeoutMills) {
    Wait().until(ajaxCompleted());
  }

}