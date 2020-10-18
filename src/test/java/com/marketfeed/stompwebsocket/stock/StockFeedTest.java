package com.marketfeed.stompwebsocket.stock;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class StockFeedTest {

    @Test
    public void testStockFeed()
    {
        BigDecimal price = BigDecimal.valueOf(21).add(BigDecimal.valueOf(20)).divide(BigDecimal.valueOf(2));

        price = price.setScale(2, RoundingMode.HALF_EVEN);

        StockFeed stockFeed = new StockFeed("D05:SGX",
                price,
                "up");

        assertNotNull(stockFeed.getPrice());
        assertEquals(BigDecimal.valueOf(20.50).setScale(2, RoundingMode.HALF_EVEN), stockFeed.getPrice());

    }

}