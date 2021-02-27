package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


/**
 * {@link EmailExtraction} class is used for extracting valid email from third party source - temp-email. This technique
 * is useful, because it allows to generate valid test data, for example – for creating accounts. Since email is valid, it is
 * easy to automate email verification, so that the test plans could be extended easily.
 */
public class EmailExtraction {
    private final Logger logger = LogManager.getLogger(this.getClass().getName());
    private SelenideElement emailField = $(byId("mail"));

    public void getToPage() {
        open("https://temp-mail.org/en/");
    }

    public String getValidEmail() {
        getToPage();
        return extractEmail();
    }

    public String extractEmail() {
        logger.info("Extracting temporary email.");
        return emailField
                .shouldBe(
                        Condition.attribute("class", "emailbox-input opentip"),
                        Duration.ofMillis(10000))
                .getAttribute("value");
    }
}
