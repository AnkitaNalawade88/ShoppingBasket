package com.shopping.ShoppingBasket;

import java.util.HashMap;
import java.util.Map;

public class Catalog {
    public static final String APPLE = "Apple";
    public static final String BANANA = "Banana";
    public static final String MELON = "Melon";
    public static final String LIME = "Lime";

    private Map<String, Integer> prices;

    public Catalog(Map<String, Integer> prices) {
        this.prices = prices;
    }

    public static Catalog build() {
        Map<String, Integer> prices = new HashMap<>();
        prices.put(Catalog.APPLE, 35);
        prices.put(Catalog.BANANA, 20);
        prices.put(Catalog.MELON, 50);
        prices.put(Catalog.LIME, 15);

        return new Catalog(prices);
    }

    public int getPriceFor(String item) {
        if (prices.containsKey(item)) {
            return prices.get(item);
        } else {
            throw new IllegalArgumentException("No price for [" + item + "]");
        }
    }
}
