package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.dto.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.models.Data;

import java.util.concurrent.CompletableFuture;

public interface CreateCustomerService {
    CompletableFuture<CustomerTokenOutputDto> createCustomer(Data data);

}
