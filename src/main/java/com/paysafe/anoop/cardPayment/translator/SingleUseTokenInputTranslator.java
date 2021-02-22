package com.paysafe.anoop.cardPayment.translator;

import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.input.SingleUseCustomerTokenInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SingleUseTokenInputTranslator {
    public SingleUseCustomerTokenInputDto translate(CustomerEntity ccd) {
        SingleUseCustomerTokenInputDto singleUseCustomerTokenInputDto = new SingleUseCustomerTokenInputDto();
        singleUseCustomerTokenInputDto.setPaymentTypes(new ArrayList<>(){{add("CARD");}});
        singleUseCustomerTokenInputDto.setMerchantRefNum(UUID.randomUUID().toString());
        return singleUseCustomerTokenInputDto;
    }
}
