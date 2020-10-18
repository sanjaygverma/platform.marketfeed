package com.marketfeed.stompwebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class MarketFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketFeedApplication.class, args);
	}

}
