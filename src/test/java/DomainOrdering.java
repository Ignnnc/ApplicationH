import com.codeborne.selenide.Selenide;
import models.Client;
import org.junit.jupiter.api.*;
import pages.EmailExtraction;
import pages.host.Authorization;
import pages.host.Cart;
import pages.host.DomainFinder;
import pages.host.Payment;

import static configs.UiConfigs.setupDriver;

@DisplayName("Domain ordering")
public class DomainOrdering {

    private static EmailExtraction validEmailExtraction = new EmailExtraction();
    private DomainFinder domainFinderPage = new DomainFinder();
    private Cart cartPage = new Cart();
    private Authorization authorizationPage = new Authorization();
    private Payment paymentPage = new Payment();
    private static Client client;

    /**
     * Method contains all the steps needed before all tests execution. Since client, with valid data, could be reused
     * in other tests, its extraction is placed inside beforeAll method.
     */
    @DisplayName("Suite setup")
    @BeforeAll
    public static void suiteSetup() {
        setupDriver();
        // Preparing client data
        String email = validEmailExtraction.getValidEmail();
        client = Client.generateRandomClient();
        client.setEmail(email);
    }

    /**
     * Method contains all the steps needed after all test execution.
     */
    @DisplayName("Suite teardown")
    @AfterAll
    public static void suiteTeardown() {
        Selenide.closeWebDriver();
    }

    /**
     * The main purpose of this test is to validate:
     * 1. That domain finder is working.
     * 2. That cart page contains domains and its data, selected from domains finder.
     * 3. That sign up is working fine.
     * 4. That there are several payments methods inside payments page.
     */
    @Test
    @Tag("TEST-001")
    @DisplayName("Domain Processing (Successful)")
    public void orderDomain() {
        domainFinderPage
                .getToPageViaBrowser()
                .enterRandomDomainName()
                .pressButtonSearch()
                .selectRandomAlternativeDomain()
                .accessCartViaAnySelection();
        cartPage
                .readAndValidateRegisteredDomains(domainFinderPage)
                .readAndValidateDomainsInSummary(domainFinderPage)
                .pressCheckoutButton();

        authorizationPage
                .validatePageOpening()
                .createAccount(client);

        paymentPage
                .validatePageOpening()
                .validateIfAllPaymentMethodsExists();
    }

}
