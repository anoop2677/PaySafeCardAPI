package com.paysafe.anoop.cardPayment.translator;
import com.paysafe.anoop.cardPayment.dto.input.CustomerInputDto;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerInputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CustomerInputTranslator {
    public CustomerInputDto translate(CreateCustomerInputDto ccdo){
        CustomerInputDto cid = new CustomerInputDto();
        cid.setMerchantCustomerId(UUID.randomUUID().toString());
        cid.setLocale("en_US");
        cid.setFirstName(ccdo.getFirstName());
        cid.setMiddleName("James");
        cid.setLastName("Dee");
        cid.setDateOfBirth(1, 7, 1990);
        cid.setEmail(ccdo.getEmail());
        cid.setPhone("1234567890");
        cid.setIp("10.10.1.111");
        cid.setGender("M");
        cid.setNationality("US");
        cid.setCellPhone("1234567890");
        return cid;
    }
}
