package com.marketfeed.stompwebsocket.stock;

import java.math.BigDecimal;

public final class StockFeed {

    private final String symbol;

    private final BigDecimal price;

    private final String trend;

    public StockFeed(String symbol, final BigDecimal price, String trend)
    {
        this.symbol = symbol;
        //this.price = bid.add(ask).divide(new BigDecimal(2));
        this.price = price;
        this.trend = trend;
    }


    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "StockFeed{" +
                "symbol='" + symbol + '\'' +
                ", price=" + price +
                ", trend='" + trend + '\'' +
                '}';
    }

    public String getTrend() {
        return trend;
    }
}
