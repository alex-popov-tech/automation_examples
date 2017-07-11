package google.gmail.tests;

import com.codeborne.selenide.Configuration;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.open;
import static gmail.core.Application.*;

/**
 * Created by Alex on 1/15/2017.
 */
public class GoogleGmailTest {

    String email = "test_email";
    String password = "test_password";
    String messageTopic = "unic topic" + System.nanoTime();

    @BeforeClass
    public static void beforeClassSetup() {
        Configuration.browser = "chrome";
        Configuration.timeout = 15_000;
        Configuration.baseUrl = "http://gmail.com";
    }

    @Test
    public void sendEmailSearchEmailTest() {

        open("/");

        GIVEN().ensureLoggedAs(email, password);

        WHEN().atInboxPage()
                .composeEmail()
                .withRecipients(email)
                .withTopic(messageTopic)
                .withMessage("")
                .send();
        AND().atInboxPage()
                .search(messageTopic);

        THEN().atInboxPage()
                .emails()
                .shouldHaveTopics(messageTopic);
    }

}