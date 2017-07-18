package todomvc.tests;

import todomvc.configuration.BaseTest;
import todomvc.pages.Tasks;
import org.junit.Test;

import static todomvc.given.GivenHelper.given;

/**
 * Created by Alex on 12/25/2016.
 */
public class TasksOperationsAtAllFilterTest extends BaseTest {

    @Test
    public void editConfirmByTab() {
        given().completed("1")
                .atAllFilter()
                .prepare();

        Tasks.editByTab("1", "edited1");
        Tasks.shouldBe("edited1");
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void editConfirmByClickOutside() {
        given().active("1", "2")
                .atAllFilter()
                .prepare();

        Tasks.editByClickOuside("1", "2");
        Tasks.shouldBe("2", "2");
        Tasks.itemsLeftShouldBe(2);
    }

    @Test
    public void editCancelByEsc() {
        given().active("1")
                .atAllFilter()
                .prepare();

        Tasks.cancelByEdit("1", "notdited1");
        Tasks.shouldBe("1");
        Tasks.itemsLeftShouldBe(1);
    }

    @Test
    public void destroy() {
        given().completed("1", "2")
                .atAllFilter()
                .prepare();

        Tasks.destroy("2");
        Tasks.shouldBe("1");
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void complete() {
        given().active("1", "2")
                .atAllFilter()
                .prepare();

        Tasks.toggle("2");
        Tasks.itemsLeftShouldBe(1);
        Tasks.goCompleted();
        Tasks.shouldBe("2");
    }

    @Test
    public void completeAll() {
        given().active("1", "2")
                .completed("3")
                .atAllFilter()
                .prepare();

        Tasks.toggleAll();
        Tasks.shouldBe("1", "2", "3");
        Tasks.itemsLeftShouldBe(0);
        Tasks.goCompleted();
        Tasks.shouldBe("1", "2", "3");
    }

    @Test
    public void clearCompleted() {
        given().active("1")
                .completed("2", "3")
                .atAllFilter()
                .prepare();

        Tasks.clearCompleted();
        Tasks.shouldBe("1");
        Tasks.itemsLeftShouldBe(1);
    }

    @Test
    public void activate() {
        given().completed("1", "2")
                .atAllFilter()
                .prepare();

        Tasks.toggle("2");
        Tasks.itemsLeftShouldBe(1);
        Tasks.goActive();
        Tasks.shouldBe("2");
    }

    @Test
    public void activateAll() {
        given().completed("1", "2")
                .atAllFilter()
                .prepare();

        Tasks.toggleAll();
        Tasks.itemsLeftShouldBe(2);
        Tasks.goActive();
        Tasks.shouldBe("1", "2");
    }

    @Test
    public void navigateToCompleteFilter() {
        given().active("1")
                .completed("2")
                .atAllFilter()
                .prepare();

        Tasks.goCompleted();
        Tasks.shouldBe("2");
        Tasks.itemsLeftShouldBe(1);
    }
}

