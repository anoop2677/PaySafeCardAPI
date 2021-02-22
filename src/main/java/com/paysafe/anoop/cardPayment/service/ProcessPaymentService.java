package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.dto.output.ProcessPaymentOutputDto;
import com.paysafe.anoop.cardPayment.models.PaymentRequest;

import java.util.concurrent.CompletableFuture;

public interface ProcessPaymentService {
    CompletableFuture<ProcessPaymentOutputDto> processPayment(PaymentRequest data);

}
