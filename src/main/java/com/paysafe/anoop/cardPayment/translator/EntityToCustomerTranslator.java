package com.paysafe.anoop.cardPayment.translator;

import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntityToCustomerTranslator {
    public CreateCustomerDto translate(CustomerEntity cdo){
        CreateCustomerDto cc= new CreateCustomerDto();
        if(cdo != null){
            cc.setCellPhone(cdo.getCellPhone());
            cc.setEmail(cdo.getEmail());
            String[] date = cdo.getDateOfBirth().split("/");
            cc.setDateOfBirth(Integer.parseInt(date[1]), Integer.parseInt(date[0]), Integer.parseInt(date[0]));
            cc.setGender(cdo.getGender());
            cc.setId(cdo.getId());
            cc.setFirstName(cdo.getFirstName());
            cc.setIp(cdo.getIp());
            cc.setLastName(cdo.getLastName());
            cc.setMerchantCustomerId(cdo.getMerchantCustomerId());
            cc.setMiddleName(cdo.getMiddleName());
            cc.setStatus(cdo.getStatus());
            cc.setPaymentToken(cdo.getPaymentToken());
            cc.setNationality(cdo.getNationality());
            cc.setPhone(cdo.getPhone());
            cc.setLocale(cdo.getLocale());
        }
        return cc;
    }
}
