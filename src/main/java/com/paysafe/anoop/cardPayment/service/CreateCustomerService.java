package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.dto.output.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerInputDto;

import java.util.concurrent.CompletableFuture;

public interface CreateCustomerService {
    CompletableFuture<CustomerTokenOutputDto> createCustomer(CreateCustomerInputDto createCustomerInputDto);

}
