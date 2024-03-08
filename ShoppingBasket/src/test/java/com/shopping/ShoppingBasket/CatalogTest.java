package com.shopping.ShoppingBasket;

import org.junit.Test;
import java.util.HashMap;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class CatalogTest {
    @Test
    public void canGetPriceForItem() {
        HashMap<String, Integer> prices = new HashMap<>();
        int price = 35;
        prices.put(Catalog.APPLE, price);
        int priceForApple = new Catalog(prices).getPriceFor(Catalog.APPLE);

        assertThat(priceForApple, is(price));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getsExceptionIfItemNotInCatalog() {
        new Catalog(new HashMap<>()).getPriceFor(Catalog.APPLE);
    }

}