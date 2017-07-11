package gmail.core;

import com.codeborne.selenide.Configuration;

/**
 * Created by Alex on 2/15/2017.
 */
public class Config {
    public static long long_timeout() { return Configuration.timeout * 3; }
}
