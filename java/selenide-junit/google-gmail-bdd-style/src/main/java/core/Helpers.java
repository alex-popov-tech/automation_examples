package core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.Wait;
import static core.CustomConditions.ajaxCompleted;

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

    public static void killProcess(String name) throws IOException {
        Runtime rt = Runtime.getRuntime();
        if (System.getProperty("os.name").toLowerCase().contains("windows")) {
            rt.exec("taskkill /F /IM " + name);
        } else {
            rt.exec("kill -9 " + name);
        }
    }

}