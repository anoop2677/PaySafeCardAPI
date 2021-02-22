package com.paysafe.anoop.cardPayment;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.paysafe"})
public class CardPaymentApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardPaymentApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
