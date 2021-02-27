package enums;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public enum PaymentMethod {

    PAYPAL("'braintree_paypal'"),
    CARDS("'processout') and not(contains(@href, 'coinpayments')"),
    CRYPTO("'processout_apm.coinpayments'"),
    GOOGLE_PAY("'checkout.googlepay'");

    final String locContains;

    PaymentMethod(String locContains) {
        this.locContains = locContains;
    }

    public SelenideElement getLocator() {
        return $(byXpath("//a[contains(@href," + locContains + ")]"));
    }
}
