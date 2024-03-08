package com.shopping.ShoppingBasket;

import org.junit.Before;
import java.util.List;
import org.junit.Test;

import static com.shopping.ShoppingBasket.Catalog.APPLE;
import static com.shopping.ShoppingBasket.Catalog.BANANA;
import static com.shopping.ShoppingBasket.Catalog.MELON;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BasketTest {
    private Catalog catalog;
    private List<DiscountRule> discounts;
    private DiscountRule discountRule;

    @Before
    public void setUp() {
        catalog = Catalog.build();
        discountRule = mock(DiscountRule.class);
        when(discountRule.calculate(any())).thenReturn(0);
        discounts = singletonList(discountRule);
    }

    @Test
    public void calculateCostOfEmptyBasket() {
        int total = new Basket(catalog, discounts).total(emptyList());

        assertThat(total, is(0));
    }

    @Test
    public void calculateCostOfBasketWithOneItemNoDiscount() {
        int total = new Basket(catalog, discounts).total(singletonList(APPLE));

        assertThat(total, is(35));
    }

    @Test
    public void calculateCostOfBasketWithMultipleItemsNoDiscount() {
        int total = new Basket(catalog, discounts).total(asList(APPLE, BANANA));

        assertThat(total, is(55));
    }

    @Test
    public void calculateCostOfBasketWithADiscount() {
        List<String> items = asList(MELON, MELON);
        when(discountRule.calculate(items)).thenReturn(50);
        int total = new Basket(catalog, discounts).total(items);

        assertThat(total, is(50));
    }
}
