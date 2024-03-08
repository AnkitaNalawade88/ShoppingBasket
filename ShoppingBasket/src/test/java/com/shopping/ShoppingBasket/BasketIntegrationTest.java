package com.shopping.ShoppingBasket;

import org.junit.Before;
import org.junit.Test;

import static com.shopping.ShoppingBasket.Catalog.APPLE;
import static com.shopping.ShoppingBasket.Catalog.MELON;
import static com.shopping.ShoppingBasket.Catalog.LIME;
import static com.shopping.ShoppingBasket.Catalog.BANANA;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BasketIntegrationTest {
    private Basket basket;

    @Before
    public void setUp() {
        basket = Basket.build();
    }

    @Test
    public void calculateCostOfEmptyBasket() {
        int total = basket.total(emptyList());

        assertThat(total, is(0));
    }

    @Test
    public void calculateCostOfBasketWithMultipleOfASingleItem() {
        int total = basket.total(asList(APPLE, APPLE));

        assertThat(total, is(70));
    }

    @Test
    public void calculateCostOfBasketWithDiscount() {
        int total = basket.total(asList(MELON, MELON));

        assertThat(total, is(50));
    }

    @Test
    public void calculateCostOfBasketWithMultipleDiscount() {
        int total = basket.total(asList(MELON, MELON, LIME, LIME, LIME));

        assertThat(total, is(80));
    }

    @Test
    public void calculateCostOfBasketWithMultipleItemsAndDiscount() {
        int total = basket.total(asList(APPLE, LIME, MELON, BANANA, MELON, LIME, LIME));

        assertThat(total, is(135));
    }
}