package pages.host;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import models.Domain;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

/**
 * {@link Cart} class is  a page orientated model. The main target of this class is to implement
 * locators and methods for selected domain validation in cart perspective. Methods are intended to validate
 * domain existence and data validation in both registration and summary sides.
 *
 * <p>Class is used to ensure, that there are no additional domains in cart, and that there are no domains
 * that were not selected in domain finder page.
 *
 */
public class Cart {

    private final Logger LOGGER = LogManager.getLogger(this.getClass().getName());

    /** Element collections stores all available domain locators in different parts of cart page (registration / summary).
    Ex. several registered domains / several domains in summary.*/
    private ElementsCollection
            domainsForRegistration = $$(byXpath("//div[contains(@ng-show, 'ItemCloudflare')]")),
            domainsForSummary = $$(byXpath("//span[@ng-repeat='item in cart.items']"));
    private SelenideElement checkoutButton = $(byId("cart-checkout-btn"));

    /** Selectors, presented below, are closely related to locators of domainsForRegistration/ domainsForSummary. Selectors
     will be used to extend them.*/
    private By
            domainNameLoc = byXpath(".//*[@ng-bind='item.unicodedDomainName']"),
            domainPriceLoc = byXpath(".//*[contains(@ng-bind,'subtotal')]");

    public Cart pressCheckoutButton() {
        LOGGER.info("Pressing checkout button.");
        checkoutButton.click();
        return this;
    }

    /**
     * Method for domain validation in registration part.
     *
     * @param domainFinder object contains all info, regarding selected domains.
     * @return
     */
    public Cart readAndValidateRegisteredDomains(DomainFinder domainFinder) {
        LOGGER.info("Validating registered domains");
        validationTemplate(domainFinder, "Registered domains", domainsForRegistration);
        return this;
    }

    /**
     * Method for domains in summary validation.
     *
     * @param domainFinder contains all info regarding selected domains.
     * @return
     */
    public Cart readAndValidateDomainsInSummary(DomainFinder domainFinder) {
        LOGGER.info("Validating domains in summary");
        validationTemplate(domainFinder, "Domains in summary", domainsForSummary);
        return this;
    }

    /**
     * Method used for both 'registered domains' / 'domains in summary' validation
     *
     * @param domainFinder    object, that contains selected domains.
     * @param whatIsValidated name of validation type.
     * @param domainsInCart   collection of cart locators for domain validation.
     * @return
     */
    public Cart validationTemplate(DomainFinder domainFinder, String whatIsValidated, ElementsCollection domainsInCart) {
        // Preparing selected domain strings
        List<String> allDomains = Domain.toString(domainFinder.getDomainSelected());

        // Validating if all the existing domains in cart were selected in domain finder page.
        String regResult, regName, regPriceFull;
        for (SelenideElement singleDomain : domainsInCart) {
            regName = singleDomain.find(domainNameLoc).getText();
            regPriceFull = singleDomain.find(domainPriceLoc).getText();
            regResult = regName + " " + regPriceFull;
            Assertions.assertTrue(allDomains.contains(regResult),
                    "Cart (" + whatIsValidated + ") contains domain '" + regResult +
                            "' that was not selected in domain finder: " + allDomains);
            LOGGER.info("Domain '" + regResult + "' is in place");
        }
        Assertions.assertEquals(domainsInCart.size(), allDomains.size(),
                "It looks like there are less domains for billing (" + whatIsValidated + ")," +
                        " than it was selected in domain Finder.");
        return this;
    }
}