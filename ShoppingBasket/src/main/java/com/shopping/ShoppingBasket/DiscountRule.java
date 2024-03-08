package com.shopping.ShoppingBasket;

import java.util.List;

public interface DiscountRule {
    int calculate(List<String> items);
}
