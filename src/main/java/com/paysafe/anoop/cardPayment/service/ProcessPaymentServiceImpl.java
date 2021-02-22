package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.Constants;
import com.paysafe.anoop.cardPayment.client.CustomerClient;
import com.paysafe.anoop.cardPayment.dto.input.PaymentProcessInputDto;
import com.paysafe.anoop.cardPayment.dto.output.ProcessPaymentOutputDto;
import com.paysafe.anoop.cardPayment.dto.input.PaymentRequest;
import com.paysafe.anoop.cardPayment.translator.PaymentProcessInputTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProcessPaymentServiceImpl implements ProcessPaymentService {
    private final PaymentProcessInputTranslator paymentProcessInputTranslator;
    @Autowired
    CustomerClient customerClient;

    @Override
    public CompletableFuture<ProcessPaymentOutputDto> processPayment(PaymentRequest data) {
        return CompletableFuture.supplyAsync(() -> {
            PaymentProcessInputDto paymentProcessInputDto = paymentProcessInputTranslator.translate(data);
            ProcessPaymentOutputDto processPaymentOutputDto = customerClient.processPayment(Constants.authorization, Constants.simulator, paymentProcessInputDto);
            return processPaymentOutputDto;
        });
        }
}
