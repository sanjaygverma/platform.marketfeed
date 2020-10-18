package com.marketfeed.stompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import com.marketfeed.stompwebsocket.stock.StockFeed;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;


@EnableScheduling
@Controller
public class StockPriceFeedController {

	private final static Map<String, BigDecimal> sym2Price = new HashMap<>();

	@Value("${symbols}")
	private String symbols;

	@Autowired
	private SimpMessagingTemplate	 template;

	@Scheduled(fixedRate = 1000)
	public void getMarketFeed() throws Exception {
		//Thread.sleep(1000);
		this.template.convertAndSend("/feed/stocks/", getFeedData());
	}

	private List<StockFeed> getFeedData()
	{
		String attrs [] = symbols.split(" ");
		return Arrays.stream(attrs).map(this::getStockFeed).collect(Collectors.toList());
	}

	private StockFeed getStockFeed(String s) {
		String [] sym_PriceRange = s.split(",");
		String symbol = sym_PriceRange[0];
		String priceRanges []  = sym_PriceRange[1].split("-");
		double min = Double.parseDouble(priceRanges[0]);
		double max = Double.parseDouble(priceRanges[1]);
		double ask = ThreadLocalRandom.current().nextDouble(min, max);
		double bid = ThreadLocalRandom.current().nextDouble(min, max);
		BigDecimal price = BigDecimal.valueOf(ask).add(BigDecimal.valueOf(ask)).divide(BigDecimal.valueOf(2));

		price = price.setScale(2, RoundingMode.HALF_EVEN);

		BigDecimal earlierPrice  = sym2Price.put(symbol,price);
		String priceTrend = "UP";
		if(earlierPrice != null) {
			switch (earlierPrice.compareTo(price))
			{
				case 1:
					priceTrend = "UP";
					break;
				case 0:
					priceTrend = "NEUTRAL";
					break;
				case -1:
					priceTrend = "DOWN";
					break;
			}
		}
		sym2Price.put(symbol, price);
		StockFeed stockFeed = new StockFeed(symbol, price,priceTrend);
		return  stockFeed;
	}

}
