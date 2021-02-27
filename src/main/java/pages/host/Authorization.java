package pages.host;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import models.Client;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;


/**
 * {@link Authorization} class is  a page orientated model. The main target of this class is to implement
 * locators and methods for 'signing up' automation.
 *
 */
public class Authorization {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private SelenideElement header = $(byXpath("//h3[contains(text(),'Sign up')]")),
            signUpSelection = $(byXpath("//li[@ui-sref='auth.signup']")),
            nameInput = $(byXpath("//input[@ng-model = 'cart.input.auth.name']")),
            emailInput = $(byXpath("//input[@ng-model = 'cart.input.auth.email']")),
            passwordInput = $(byXpath("//input[@ng-model = 'cart.input.auth.password']")),
            createAccountAndCheckout = $(byId("hgr-cart-sign_up_checkout_button"));

    public Authorization validatePageOpening() {
        header.shouldBe(Condition.visible, Duration.ofMillis(10000));
        return this;
    }

    public Authorization createAccount(Client client) {
        createAccount(client.getName(), client.getEmail(), client.getPassword());
        return this;
    }

    public Authorization createAccount(String name, String email, String password) {
        selectSignUpForm();
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        pressCreateAccountAndCheckoutButton();
        return this;
    }

    public Authorization selectSignUpForm() {
        LOGGER.info("Selecting 'Sign up' authorization form.");
        signUpSelection.click();
        return this;
    }

    public Authorization enterName(String name) {
        LOGGER.info("Entering name: " + name);
        nameInput.val(name);
        return this;
    }

    public Authorization enterEmail(String email) {
        LOGGER.info("Entering name: " + email);
        emailInput.val(email);
        return this;
    }

    public Authorization enterPassword(String password) {
        LOGGER.info("Entering password: " + password);
        passwordInput.val(password);
        return this;
    }

    public Authorization pressCreateAccountAndCheckoutButton() {
        LOGGER.info("Pressing create account and checkout button.");
        createAccountAndCheckout.click();
        return this;
    }
}
