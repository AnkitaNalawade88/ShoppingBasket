package com.shopping.ShoppingBasket;

import org.junit.Before;
import org.junit.Test;

import static com.shopping.ShoppingBasket.Catalog.APPLE;
import static com.shopping.ShoppingBasket.Catalog.MELON;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MultiBuyDiscountRuleTest {

    private MultiBuyDiscountRule discountRule;
    private String discountedItem;
    private int costOfDiscountedItem;

    @Before
    public void setUp() {
        discountedItem = MELON;
        costOfDiscountedItem = 50;
        discountRule = new MultiBuyDiscountRule(discountedItem, 2, costOfDiscountedItem);
    }

    @Test
    public void noDiscountWhenNoItems() {
        int discount = discountRule.calculate(emptyList());
        assertThat(discount, is(0));
    }

    @Test
    public void noDiscountWhenNoDiscountedItemsInBasket() {
        int discount = discountRule.calculate(singletonList(APPLE));
        assertThat(discount, is(0));
    }

    @Test
    public void singleDiscountWhenHaveMultipleItems() {
        int discount = discountRule.calculate(asList(discountedItem, discountedItem));
        assertThat(discount, is(costOfDiscountedItem));
    }

    @Test
    public void singleDiscountWhenMoreThanMultiBuyAmountButLessThanMultiDiscountAmount() {
        int discount = discountRule.calculate(asList(discountedItem, discountedItem, discountedItem));
        assertThat(discount, is(costOfDiscountedItem));
    }

    @Test
    public void multipleDiscounts() {
        int discount = discountRule.calculate(asList(discountedItem, discountedItem, discountedItem, discountedItem));
        assertThat(discount, is(2 * costOfDiscountedItem));
    }
}