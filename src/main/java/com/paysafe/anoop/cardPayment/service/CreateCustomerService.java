package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.dto.output.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.models.CreateCustomerInputDto;

import java.util.concurrent.CompletableFuture;

public interface CreateCustomerService {
    CompletableFuture<CustomerTokenOutputDto> createCustomer(CreateCustomerInputDto createCustomerInputDto);

}
