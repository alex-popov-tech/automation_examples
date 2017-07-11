package gmail.pageobjects;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Alex on 1/15/2017.
 */
public class InboxPage {

    @Step
    public NewEmail composeEmail() {
        $(byText("COMPOSE")).click();
        return new NewEmail();
    }

    @Step
    public void search(String searchString) {
        $("#gbqfq").setValue(searchString).pressEnter();
    }

    @Step
    public Emails emails() {
        return Emails.all();
    }

    @Step
    public Emails unreadedEmails() {
        return Emails.unreaded();
    }

    @Step
    public Emails readedEmails() {
        return Emails.readed();
    }

}
