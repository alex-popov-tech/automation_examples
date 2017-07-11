package gmail.pageobjects;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selectors.byCssSelector;
import static com.codeborne.selenide.Selenide.$;
import static core.Helpers.mapToInnerElements;

/**
 * Created by apop on 1/26/2017.
 */
public class Emails {
    private final ElementsCollection mailsContainers;
    private final ElementsCollection authors;
    private final ElementsCollection topics;
    private final ElementsCollection messages;

    private Emails(By mailsContainersLocator) {
        mailsContainers = $("[role='main']").findAll(mailsContainersLocator);
        authors = mapToInnerElements(mailsContainers, ".yX .yW>span");
        topics = mapToInnerElements(mailsContainers, ".y6 span[id*=':']");
        messages = mapToInnerElements(mailsContainers, ".y6 .y2");
    }

    @Step
    public static Emails all() {
        return new Emails(byCssSelector("tr"));
    }

    @Step
    public static Emails unreaded() {
        return new Emails(byCssSelector("tr.zE"));
    }

    @Step
    public static Emails readed() {
        return new Emails(byCssSelector("tr.yO"));
    }

    @Step
    public Emails shouldHaveSize(int quantity) {
        mailsContainers.shouldHaveSize(quantity);
        return this;
    }

    @Step
    public Emails shouldHaveTopics(String... texts) {
        topics.shouldHave(exactTexts(texts));
        return this;
    }

}
