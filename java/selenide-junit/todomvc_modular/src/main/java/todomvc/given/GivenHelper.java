package todomvc.given;

import com.codeborne.selenide.Selenide;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static todomvc.core.Helpers.waitUntilAjaxComplete;

/**
 * Created by Alex on 1/12/2017.
 */
public class GivenHelper {
  private final List<Task> tasks;
  private final Filter filter;

  private GivenHelper(Builder builder) {
    this.filter = builder.filter;
    this.tasks = builder.tasks;
  }

  public static Builder given() {
    return new Builder();
  }

  public void prepare() {
    openFilterPage();
    addTasksToLocalStorage();
    getWebDriver().navigate().refresh();
    waitUntilAjaxComplete();
  }

  private void addTasksToLocalStorage() {
    executeJavaScript("localStorage.setItem('todos-troopjs', JSON.stringify([" + doJsonFromList(this.tasks) + "]))");
  }

  private void openFilterPage() {
    Selenide.open(this.filter.toString());
  }

  private String doJsonFromList(List<Task> tasks) {
    return String.join(",",
            tasks.stream()
                    .map((task) -> "{'completed':" + task.type.lsValue + ", 'title':'" + task.name + "'}")
                    .collect(Collectors.toList()));
  }

  private enum Filter {
    ALL(""),
    ACTIVE("active"),
    COMPLETED("completed");

    private String url;

    Filter(String url) {
      this.url = url;
    }

    @Override
    public String toString() {
      return "https://todomvc4tasj.herokuapp.com/#/" + this.url;
    }
  }


  private enum TaskType {
    ACTIVE("false"),
    COMPLETED("true");

    private String lsValue;

    TaskType(String lsValue) {
      this.lsValue = lsValue;
    }
  }

  public static class Builder {
    private List<Task> tasks = new ArrayList<>();
    private Filter filter = Filter.ALL;

    public Builder active(String... taskNames) {
      return tasks(TaskType.ACTIVE, taskNames);
    }

    public Builder completed(String... taskNames) {
      return tasks(TaskType.COMPLETED, taskNames);
    }

    public GivenHelper atAllFilter() {
      this.filter = Filter.ALL;
      return new GivenHelper(this);
    }

    public GivenHelper atActiveFilter() {
      this.filter = Filter.ACTIVE;
      return new GivenHelper(this);
    }

    public GivenHelper atCompletedFilter() {
      this.filter = Filter.COMPLETED;
      return new GivenHelper(this);
    }

    private Builder tasks(TaskType taskType, String... taskNames) {
      Stream.of(taskNames).forEach((taskName) -> tasks.add(new Task(taskType, taskName)));
      return this;
    }
  }

  private static class Task {
    private final String name;
    private final TaskType type;

    public Task(TaskType type, String name) {
      this.name = name;
      this.type = type;
    }
  }
}
