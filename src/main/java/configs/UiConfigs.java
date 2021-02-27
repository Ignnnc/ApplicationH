package configs;

import com.codeborne.selenide.Configuration;

public class UiConfigs {

    public static void setupDriver() {
        Configuration.timeout = 5000;
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    }
}
