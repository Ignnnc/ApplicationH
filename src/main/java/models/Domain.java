package models;

import java.util.ArrayList;
import java.util.List;

public class Domain {

    private String name;
    private String price;
    private String currency;

    public Domain(String name, String price, String currency) {
        this.name = name;
        this.price = price;
        this.currency = currency;
    }

    public String toString() {
        return name + " " + currency + price;
    }

    public static List<String> toString(List<Domain> domains) {
        List<String> allDomains = new ArrayList<>();
        // Converting all array of Domain to String array for comparison
        for (Domain domain : domains) {
            allDomains.add(domain.toString());
        }
        return allDomains;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
