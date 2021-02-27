package enums;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public enum PaymentMethod {

    PAYPAL("contains(@href, 'braintree_paypal')"),
    CARDS("contains(@href, 'processout') and not(contains(@href, 'coinpayments'))"),
    CRYPTO("contains(@href, 'processout_apm.coinpayments')"),
    GOOGLE_PAY("contains(@href, 'checkout.googlepay')");

    final String locContains;

    PaymentMethod(String locContains) {
        this.locContains = locContains;
    }

    public SelenideElement getLocator() {
        return $(byXpath("//a[" + locContains + "]"));
    }
}
