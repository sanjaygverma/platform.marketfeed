package com.marketfeed.stompwebsocket;

//import brave.sampler.CountingSampler;
//import brave.sampler.Sampler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableDiscoveryClient
public class MarketFeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketFeedApplication.class, args);
	}

	/*@Bean
	Sampler customSampler() {
		return Sampler.ALWAYS_SAMPLE;
	}*/
}
