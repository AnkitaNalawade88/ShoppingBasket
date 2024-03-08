package com.shopping.ShoppingBasket;

import java.util.List;

import static com.shopping.ShoppingBasket.Catalog.LIME;
import static com.shopping.ShoppingBasket.Catalog.MELON;
import static java.util.Arrays.asList;
public class Basket {
    private final Catalog catalog;
    private final List<DiscountRule> discountRules;

    public Basket(Catalog catalog, List<DiscountRule> discountRules) {
        this.discountRules = discountRules;
        this.catalog = catalog;
    }

    public int total(List<String> items) {
        int priceBeforeDiscounts = items.stream()
                .mapToInt(catalog::getPriceFor)
                .sum();

        int discount = discountRules.stream()
                .mapToInt(discountRule -> discountRule.calculate(items))
                .sum();

        return priceBeforeDiscounts - discount;
    }

    public static Basket build() {
        return new Basket(Catalog.build(),
                asList(
                        new MultiBuyDiscountRule(MELON, 2, 50),
                        new MultiBuyDiscountRule(LIME, 3, 15)
                )
        );
    }
}
