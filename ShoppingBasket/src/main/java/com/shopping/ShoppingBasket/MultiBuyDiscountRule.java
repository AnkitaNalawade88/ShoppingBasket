package com.shopping.ShoppingBasket;

import java.util.List;

public class MultiBuyDiscountRule implements DiscountRule {
    private final String item;
    private final int numberForDiscount;
    private final int discount;

    public MultiBuyDiscountRule(String item, int numberForDiscount, int discount) {
        this.item = item;
        this.numberForDiscount = numberForDiscount;
        this.discount = discount;
    }

    @Override
    public int calculate(List<String> items) {
        int numberOfDiscountedItem = (int) items.stream().filter(item -> item.equals(this.item)).count();
        return (numberOfDiscountedItem / numberForDiscount) * discount;
    }
}
