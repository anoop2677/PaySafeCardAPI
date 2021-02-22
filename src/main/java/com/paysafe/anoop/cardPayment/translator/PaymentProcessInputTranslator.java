package com.paysafe.anoop.cardPayment.translator;

import com.paysafe.anoop.cardPayment.dto.input.PaymentProcessInputDto;
import com.paysafe.anoop.cardPayment.dto.input.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentProcessInputTranslator {
    public PaymentProcessInputDto translate(PaymentRequest pr) {
        PaymentProcessInputDto paymentProcessInputDto = new PaymentProcessInputDto();
        paymentProcessInputDto.setMerchantRefNum(pr.getMerchantRefNum());
        System.out.println(pr.getMerchantRefNum());
        paymentProcessInputDto.setAmount(pr.getAmount());
        paymentProcessInputDto.setCurrencyCode("USD");
        paymentProcessInputDto.setDupCheck(true);
        paymentProcessInputDto.setSettleWithAuth(false);
        paymentProcessInputDto.setPaymentHandleToken(pr.getPaymentHandleToken());
        paymentProcessInputDto.setCustomerIp("10.10.12.64");
        paymentProcessInputDto.setDescription("Magazine subscription");
        return paymentProcessInputDto;
    }
}
