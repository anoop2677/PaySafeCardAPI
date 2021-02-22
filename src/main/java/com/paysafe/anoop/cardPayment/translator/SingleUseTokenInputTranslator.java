package com.paysafe.anoop.cardPayment.translator;

import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.input.SingleUseCustomerTokenInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@RequiredArgsConstructor
public class SingleUseTokenInputTranslator {
    public SingleUseCustomerTokenInputDto translate(CreateCustomerDto ccd) {
        SingleUseCustomerTokenInputDto singleUseCustomerTokenInputDto = new SingleUseCustomerTokenInputDto();
        singleUseCustomerTokenInputDto.setPaymentTypes(new ArrayList<>(){{add("CARD");}});
        singleUseCustomerTokenInputDto.setMerchantRefNum(ccd.getMerchantCustomerId());
        return singleUseCustomerTokenInputDto;
    }
}
