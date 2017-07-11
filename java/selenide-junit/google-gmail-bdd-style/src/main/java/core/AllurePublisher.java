package core;

import com.codeborne.selenide.Screenshots;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static com.codeborne.selenide.ex.ErrorMessages.screenshot;
import static java.nio.file.Files.readAllBytes;

/**
 * Created by Alex on 1/10/2017.
 */
public class AllurePublisher extends TestWatcher {
    private final boolean onFail;
    private final boolean onSuccess;
    private final boolean saveScreens;
    private final boolean saveHtml;

    private AllurePublisher(Builder builder) {
        this.onFail = builder.onFail;
        this.onSuccess = builder.onSuccess;
        this.saveScreens = builder.saveScreens;
        this.saveHtml = builder.saveHtml;
    }

    public static Builder onFail() {
        return new Builder(true, false);
    }

    public static Builder onSuccess() {
        return new Builder(false, true);
    }

    public static Builder always() {
        return new Builder(true, true);
    }

    @Override
    protected void starting(Description test) {
        Screenshots.startContext(test.getClassName(), test.getMethodName());
    }

    @Override
    protected void finished(Description description) {
        Screenshots.finishContext();
    }

    @Override
    protected void succeeded(Description test) {
        if (onSuccess) {
            String path = getPathOf(screenshot());
            if (saveScreens) {
                publishScreenshot(path);
            }
            if (saveHtml) {
                publishPageSource(path);
            }
        }
    }

    @Override
    protected void failed(Throwable e, Description description) {
        if (onFail) {
            String path = getPathOf(screenshot());
            try {
                if (saveScreens) {
                    publishScreenshot(path);
                }
                if (saveHtml) {
                    publishPageSource(path);
                }
            } catch (AssertionError ae) {
                e.addSuppressed(ae);
            }
        }
    }

    public static void publishScreen() {
        String path = getPathOf(screenshot());
        publishScreenshot(path);
    }

    public static void publishPageSource() {
        String path = getPathOf(screenshot());
        publishPageSource(path);
    }

    public static void publishScreenAndPageSource() {
        String path = getPathOf(screenshot());
        publishScreenshot(path);
        publishPageSource(path);
    }

    @Attachment(value = "Page-screenshot", type = "image/png")
    private static byte[] publishScreenshot(String path) {
        File screen = new File(path + ".png");
        System.err.println("Publishing screenshot to Allure...");
        try {
            return readAllBytes(Paths.get(screen.toURI()));
        } catch (IOException e) {
            throw new AssertionError("IOException occurred with file path: " + path, e);
        }
    }

    @Attachment(value = "Page-source", type = "text/html")
    private static byte[] publishPageSource(String path) {
        File html = new File(path + ".html");
        System.err.println("Publishing page source to Allure...");
        try {
            return readAllBytes(Paths.get(html.toURI()));
        } catch (IOException e) {
            throw new AssertionError("IOException occurred with file path: " + path, e);
        }
    }

    private static String getPathOf(String screenshot) {
        // skip 'Screenshot: file:/'
        // and extension
        return screenshot.substring(19, screenshot.lastIndexOf('.'));
    }

    public static class Builder {
        private boolean onFail = false;
        private boolean onSuccess = false;
        private boolean saveScreens = false;
        private boolean saveHtml = false;

        public Builder(boolean onFail, boolean onSuccess) {
            this.onFail = onFail;
            this.onSuccess = onSuccess;
        }

        public Builder saveScreens() {
            this.saveScreens = true;
            return this;
        }

        public Builder saveHtml() {
            this.saveHtml = true;
            return this;
        }

        public Builder saveScreenAndHtml() {
            this.saveScreens = true;
            this.saveHtml = true;
            return this;
        }

        public AllurePublisher create() {
            return new AllurePublisher(this);
        }
    }
}
