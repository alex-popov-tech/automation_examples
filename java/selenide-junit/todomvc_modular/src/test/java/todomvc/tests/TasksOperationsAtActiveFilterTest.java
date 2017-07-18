package todomvc.tests;

import todomvc.configuration.BaseTest;
import todomvc.pages.Tasks;
import org.junit.Test;

import static todomvc.given.GivenHelper.given;


/**
 * Created by Alex on 12/25/2016.
 */
public class TasksOperationsAtActiveFilterTest extends BaseTest {

    @Test
    public void add() {
        given().active("1")
                .atActiveFilter()
                .prepare();

        Tasks.add("2");
        Tasks.shouldBe("1", "2");
        Tasks.itemsLeftShouldBe(2);
    }

    @Test
    public void editConfirmedByEnter() {
        given().active("1", "2")
                .atActiveFilter()
                .prepare();

        Tasks.editByEnter("2", "edited2");
        Tasks.shouldBe("1", "edited2");
        Tasks.itemsLeftShouldBe(2);
    }

    @Test
    public void editConfirmClickOutside() {
        given().active("1")
                .atActiveFilter()
                .prepare();

        Tasks.editByClickOuside("1", "edited1");
        Tasks.shouldBe("edited1");
        Tasks.itemsLeftShouldBe(1);
    }

    @Test
    public void deleteByEditToEmpty() {
        given().active("1")
                .completed("2")
                .atActiveFilter()
                .prepare();

        Tasks.editByEnter("1", "");
        Tasks.shouldBeEmpty();
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void destroy() {
        given().active("1", "2")
                .atActiveFilter()
                .prepare();

        Tasks.destroy("1");
        Tasks.shouldBe("2");
        Tasks.itemsLeftShouldBe(1);
    }

    @Test
    public void completeAll() {
        given().active("1", "2")
                .atActiveFilter()
                .prepare();

        Tasks.toggleAll();
        Tasks.shouldBeEmpty();
        Tasks.itemsLeftShouldBe(0);
    }

    @Test
    public void clearCompleted() {
        given().active("1")
                .completed("2")
                .atActiveFilter()
                .prepare();

        Tasks.clearCompleted();
        Tasks.itemsLeftShouldBe(1);
        Tasks.goCompleted();
        Tasks.shouldBeEmpty();
    }

    @Test
    public void navigateToAllFilter() {
        given().active("1")
                .completed("2")
                .atActiveFilter()
                .prepare();

        Tasks.goAll();
        Tasks.shouldBe("1", "2");
        Tasks.itemsLeftShouldBe(1);
    }

}
