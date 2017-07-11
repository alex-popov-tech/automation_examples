package gmail.pageobjects;

import com.codeborne.selenide.Condition;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * Created by apop on 1/26/2017.
 */
public class NewEmail {

    @Step
    public NewEmail withRecipients(String... recipients) {
        for (String recipient : recipients) {
            $$(".GS .eV").filter(Condition.visible)
                    .first()
                    .find(".oj .vO")
                    .setValue(recipient)
                    .pressEnter();
        }
        return this;
    }

    @Step
    public NewEmail withMessage(String message) {
        $(".editable").setValue(message);
        return this;
    }

    @Step
    public NewEmail withTopic(String topic) {
        $(".aoD.az6 .aoT").setValue(topic);
        return this;
    }

    @Step
    public void send() {
        $(byText("Send")).click();
    }
}
