package com.paysafe.anoop.cardPayment.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@lombok.Data
@RequiredArgsConstructor
public class PaymentRequest {
    String merchantRefNum;
    String paymentHandleToken;
    int amount;
    String currency;
    String custId;
    String paymentMethod;
    String transactionType;
}
