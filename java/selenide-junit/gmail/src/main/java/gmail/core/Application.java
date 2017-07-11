package gmail.core;

import gmail.pageobjects.InboxPage;
import gmail.pageobjects.LoginPage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.WebDriverRunner.url;
import static gmail.core.Config.long_timeout;

/**
 * Created by Alex on 2/4/2017.
 */
public class Application {

    @Step
    public static Application WHEN() {
        return new Application();
    }

    @Step
    public static Application GIVEN() {
        return new Application();
    }

    @Step
    public static Application AND() {
        return new Application();
    }

    @Step
    public static Application THEN() {
        return new Application();
    }

    @Step
    public InboxPage atInboxPage() {
        return new InboxPage();
    }

    @Step
    public LoginPage atLoginPage() {
        return new LoginPage();
    }

    @Step
    public void ensureLoggedAs(String email, String password) {

        // if some user logged in
        if (url().contains("accounts.google.com")) {

            atLoginPage().loginAs(email, password);
            $(".cmsg").waitUntil(hidden, long_timeout());

        } else {

            $(".cmsg").waitUntil(hidden, long_timeout());

            // click at avatar
            $(".gb_9a.gbii").click();

            // if correct user logged in return
            if ($(".gb_wb").is(have(text(email)))) {
                // click at avatar
                $(".gb_9a.gbii").click();
                return;
            } else {
                // logout
                $("#gb_71").click();
                $("#account-chooser-link").click();
                // find expected user, click on it and type password
                $$("#gaia_loginform li")
                        .filterBy(visible)
                        .filterBy(text(email))
                        .shouldHaveSize(1)
                        .first()
                        .click();
                atLoginPage().typePassword(password);
            }
        }
    }

}
