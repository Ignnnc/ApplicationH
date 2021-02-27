package pages.host;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.Domain;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Assertions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class DomainFinder {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    private SelenideElement
            cookiesAcceptionButton = $(byId("hgr-cookie_consent-accept_all_btn")),
            domainInputField = $(byXpath("//input[contains(@class,'header__input')]")),
            domainSearchButton = $(byId("dc-button")),
            searchLoaderLocator = $(byXpath("//*[@id=\"dc-loader\"]")),
            domainSelectionLoaderLocator = $(byXpath("//span[contains(text(),'Loading')]")),
            cartOfSelectedDomain = $(byXpath("//a[@href='/cart']")),
            selectedCurrency = $(byXpath("//div[contains(@class, 'header-currency-switcher__currency')]"));

    private ElementsCollection alternativeDomains =
            $$(byXpath("//h2[contains(text(), 'Alternative titles')]/parent::div//div[contains(@class,'result-box ')]"));

    private List<Domain> domainSelected = new ArrayList<>();
    private final int TIME_OUT = 10000;

    public DomainFinder getToPageViaBrowser() {
        open("https://www.hostinger.com/domain-checker");
        acceptCookiesIfNeeded();
        return this;
    }

    public DomainFinder acceptCookiesIfNeeded() {
        try {
            cookiesAcceptionButton.should(Condition.visible, Duration.ofMillis(3000)).click();
        } finally {
            LOGGER.warn("There was no cookies to accept.");
        }
        return this;
    }

    public DomainFinder enterRandomDomainName() {
        enterDomainName(RandomStringUtils.random(10, true, true));
        return this;
    }

    public DomainFinder enterDomainName(String domain) {
        LOGGER.info("Entering domain name: " + domain);
        domainInputField.scrollIntoView(false).val(domain);
        return this;
    }

    public DomainFinder pressButtonSearch() {
        LOGGER.info("Pressing search button");
        domainSearchButton.scrollIntoView(false).click();
        waitForSearchCompletion();
        return this;
    }

    private DomainFinder waitForSearchCompletion() {
        LOGGER.info("Waiting until domain search is completed.");
        searchLoaderLocator.shouldNot(Condition.visible, Duration.ofMillis(TIME_OUT));
        return this;
    }

    /**
     * Method generates random alternative domain locator and stores its info inside array of domains. Array of domains
     * could be iterated for validation in cart page object.
     *
     * @return
     */
    public DomainFinder selectRandomAlternativeDomain() {
        LOGGER.info("Selecting domain randomly");
        // Preparing random alternative domain locator
        SelenideElement randomDomain = alternativeDomains.get(RandomUtils.nextInt(0, alternativeDomains.size() - 1));

        // Extracting domain info
        String currency = getCurrentCurrency();
        String name = randomDomain.find(byXpath(".//h5")).getText();
        String price = randomDomain.find(byXpath(".//div[contains(@class, 'new-prices')]//div[contains(@class,'h4')]"))
                .getText()
                .replace(currency, "")
                .replace("/yr", "");
        this.domainSelected.add(new Domain(name, price, currency));

        // selecting domain
        randomDomain.find(byXpath(".//button")).scrollIntoView(false).click();
        waitForDomainSelectionCompletion();
        LOGGER.info("Domain selected: " + name + " " + currency + " " + price);
        return this;
    }

    /**
     * After domain selection, there is a loading bar. Need to wait until it ends up.
     *
     * @return
     */
    private DomainFinder waitForDomainSelectionCompletion() {
        LOGGER.info("Waiting until domain selection is completed.");
        domainSelectionLoaderLocator.shouldNot(Condition.visible, Duration.ofMillis(TIME_OUT));
        return this;
    }

    /**
     * Press any cart access button.
     *
     * @return
     */
    public DomainFinder accessCartViaAnySelection() {
        if (domainSelected.size() == 0) {
            Assertions.fail("None of domain were selected. Impossible to access cart using selected domain");
        }
        LOGGER.info("Accessing cart via selected domain");
        cartOfSelectedDomain.scrollIntoView(false).click();
        return this;
    }

    private String getCurrentCurrency() {
        return selectedCurrency.getText();
    }

    public List<Domain> getDomainSelected() {
        return domainSelected;
    }
}
