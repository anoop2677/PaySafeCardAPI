package com.paysafe.anoop.cardPayment.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class PaymentProcessInputDto {
    String merchantRefNum;
    int amount;
    String currencyCode;
    boolean dupCheck;
    boolean settleWithAuth;
    String paymentHandleToken;
    String customerIp;
    String description;
}
