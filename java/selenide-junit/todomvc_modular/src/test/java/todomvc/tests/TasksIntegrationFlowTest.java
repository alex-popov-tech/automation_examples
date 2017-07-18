package todomvc.tests;

import todomvc.configuration.BaseTest;
import todomvc.pages.Tasks;
import org.junit.Test;

import static todomvc.given.GivenHelper.given;

/**
 * Created by Alex on 1/10/2017.
 */
public class TasksIntegrationFlowTest extends BaseTest {

    @Test
    public void basicTasksFlow() {
        given().completed("1")
                .atAllFilter()
                .prepare();

        Tasks.add("2");
        Tasks.shouldBe("1", "2");
        Tasks.editByEnter("2", "edited2");
        Tasks.shouldBe("1", "edited2");

        Tasks.goActive();
        Tasks.shouldBe("edited2");
        Tasks.cancelByEdit("edited2", "notedited2");
        Tasks.toggle("edited2");
        Tasks.shouldBeEmpty();

        Tasks.goCompleted();
        Tasks.shouldBe("1", "edited2");
        Tasks.destroy("edited2");
        Tasks.shouldBe("1");
        Tasks.toggle("1");
        Tasks.shouldBeEmpty();

        Tasks.goAll();
        Tasks.shouldBe("1");
    }
}