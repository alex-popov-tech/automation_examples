package todomvc.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.CollectionCondition.empty;
import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static todomvc.core.Helpers.waitUntilAjaxComplete;

/**
 * Created by Alex on 12/26/2016.
 */
public class Tasks {
  private static final ElementsCollection tasks = $$("#todo-list>li");
  private static final ElementsCollection filters = $$("#filters>li");

  public static void visit() {
    open("https://todomvc4tasj.herokuapp.com/#/");
    waitUntilAjaxComplete();
  }

  @Step
  public static void add(String taskName) {
    $("#new-todo").setValue(taskName).pressEnter();
  }

  @Step
  public static void add(String... taskNames) {
    for (String taskName : taskNames) {
      add(taskName);
    }
  }

  @Step
  public static void editByEnter(String fromTaskName, String toTaskName) {
    startEdit(fromTaskName, toTaskName).pressEnter();
  }

  @Step
  public static void editByTab(String fromTaskName, String toTaskName) {
    startEdit(fromTaskName, toTaskName).pressTab();
  }

  @Step
  public static void cancelByEdit(String fromTaskName, String toTaskName) {
    startEdit(fromTaskName, toTaskName).pressEscape();
  }

  @Step
  public static void editByClickOuside(String fromTaskName, String toTaskName) {
    startEdit(fromTaskName, toTaskName);
    $("#header>h1").click();
  }

  @Step
  public static void destroy(String taskName) {
    tasks.findBy(exactText(taskName)).hover().find(".destroy").click();
  }

  @Step
  public static void toggle(String taskName) {
    tasks.findBy(exactText(taskName)).find(".toggle").click();
  }

  @Step
  public static void toggleAll() {
    $("#toggle-all").click();
  }

  @Step
  public static void clearCompleted() {
    $("#clear-completed").click();
  }

  @Step
  public static void goAll() {
    filters.findBy(exactText("All")).click();
  }

  @Step
  public static void goActive() {
    filters.findBy(exactText("Active")).click();
  }

  @Step
  public static void goCompleted() {
    filters.findBy(exactText("Completed")).click();
  }

  @Step
  public static void shouldBe(String... taskNames) {
    tasks.filter(visible).shouldHave(exactTexts(taskNames));
  }

  @Step
  public static void shouldBeEmpty() {
    tasks.filter(visible).shouldBe(empty);
  }

  @Step
  public static void itemsLeftShouldBe(int itemsCount) {
    $("#todo-count>strong").shouldHave(exactText(String.valueOf(itemsCount)));
  }

  private static SelenideElement startEdit(String fromTaskName, String toTaskName) {
    tasks.findBy(exactText(fromTaskName))
            .doubleClick();
    return tasks.findBy(cssClass("editing"))
            .find(".edit")
            .setValue(toTaskName);
  }

}