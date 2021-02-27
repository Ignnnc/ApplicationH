package configs;

import com.codeborne.selenide.Configuration;

/**
 * {@link UiConfigs} class contains methods for configuring web-drivers.
 */
public class UiConfigs {

    /**
     * These configurations are needed to prepare web-driver.
     */
    public static void setupDriver() {
        Configuration.timeout = 5000;
        Configuration.startMaximized = true;
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
    }
}
