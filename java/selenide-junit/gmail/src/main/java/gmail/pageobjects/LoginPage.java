package gmail.pageobjects;

import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;

/**
 * Created by Alex on 1/15/2017.
 */
public class LoginPage {

    @Step
    public void loginAs(String email, String password) {
        typeEmail(email);
        typePassword(password);
    }

    public LoginPage typeEmail(String email) {
        $("#identifierId").setValue(email).pressEnter();
        return this;
    }

    public LoginPage typePassword(String password) {
        $("#password").find("input").setValue(password).pressEnter();
        return this;
    }

}
