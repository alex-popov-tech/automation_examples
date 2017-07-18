package todomvc.tests;

import todomvc.configuration.BaseTest;
import todomvc.pages.Tasks;
import org.junit.Test;

import static todomvc.given.GivenHelper.given;

/**
 * Created by Alex on 12/25/2016.
 */
public class TasksOperationsAtCompleteFilterTest extends BaseTest {

    @Test
    public void editConfirmByEnter() {
        given().completed("1", "2")
                .atCompletedFilter()
                .prepare();

        Tasks.editByEnter("1", "2");
        Tasks.shouldBe("2", "2");
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void editConfirmByClickOutside() {
        given().completed("1", "2")
                .atCompletedFilter()
                .prepare();

        Tasks.editByClickOuside("2", "edited2");
        Tasks.shouldBe("1", "edited2");
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void editCancelByEsc() {
        given().completed("1", "2")
                .atCompletedFilter()
                .prepare();

        Tasks.cancelByEdit("2", "notedited2");
        Tasks.shouldBe("1", "2");
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void clearCompleted() {
        given().completed("1", "2")
                .atCompletedFilter()
                .prepare();

        Tasks.clearCompleted();
        Tasks.shouldBeEmpty();
    }

    @Test
    public void completeAll() {
        given().active("1", "3")
                .completed("2")
                .atCompletedFilter()
                .prepare();

        Tasks.toggleAll();
        Tasks.shouldBe("1", "3", "2");
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void activateAll() {
        given().completed("1", "2")
                .atCompletedFilter()
                .prepare();

        Tasks.toggleAll();
        Tasks.shouldBeEmpty();
        Tasks.itemsLeftShouldBe(2);
    }

}
