package com.paysafe.anoop.cardPayment.translator;

import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.CreateCustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class CustomerToEntityTranslator {
    public CustomerEntity translate(CreateCustomerDto cdo){
        CustomerEntity cc= new CustomerEntity();
        if(cdo != null){
            cc.setCellPhone(cdo.getCellPhone());
            cc.setEmail(cdo.getEmail());
            cc.setDateOfBirth(cdo.getDateOfBirth().getDat());
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
