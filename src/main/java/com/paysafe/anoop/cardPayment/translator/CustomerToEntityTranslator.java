package com.paysafe.anoop.cardPayment.translator;

import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomerToEntityTranslator {
    public CustomerEntity translate(CreateCustomerDto cdo){
        CustomerEntity cc= new CustomerEntity();
        if(cdo != null){
            cc.setEmail(cdo.getEmail());
            cc.setId(cdo.getId());
        }
        return cc;
    }
}
