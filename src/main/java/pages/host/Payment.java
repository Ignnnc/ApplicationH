package pages.host;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import enums.PaymentMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class Payment {

    SelenideElement header = $(byXpath("//h2[contains(text(),'Choose a Payment Method')]"));
    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    public Payment validatePageOpening() {
        LOGGER.info("Validating if payments page was opened");
        header.shouldBe(Condition.visible, Duration.ofMillis(10000));
        return this;
    }

    public Payment validateIfAllPaymentMethodsExists() {
        for (PaymentMethod method : PaymentMethod.values()) {
            LOGGER.info("Checking if payments method exists: " + method.name());
            Assertions.assertTrue(method.getLocator().isDisplayed(), " Payment method '" + method + "' is not displayed.");
        }
        return this;
    }


}