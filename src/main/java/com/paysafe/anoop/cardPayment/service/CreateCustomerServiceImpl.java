package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.Constants;
import com.paysafe.anoop.cardPayment.client.CustomerClient;
import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.input.CustomerInputDto;
import com.paysafe.anoop.cardPayment.dto.input.SingleUseCustomerTokenInputDto;
import com.paysafe.anoop.cardPayment.dto.output.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.dto.output.SingleUseCustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerInputDto;
import com.paysafe.anoop.cardPayment.repository.CustomerRepository;
import com.paysafe.anoop.cardPayment.translator.CustomerInputTranslator;
import com.paysafe.anoop.cardPayment.translator.CustomerToEntityTranslator;
import com.paysafe.anoop.cardPayment.translator.SingleUseTokenInputTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public class CreateCustomerServiceImpl implements CreateCustomerService{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerToEntityTranslator customerTranslator;
    @Autowired
    CustomerClient customerClient;
    @Autowired
    CustomerInputTranslator customerInputTranslator;
    @Autowired
    SingleUseTokenInputTranslator singleUseTokenInputTranslator;

    public CompletableFuture<CustomerTokenOutputDto> createCustomer(CreateCustomerInputDto requestBody) {
        return CompletableFuture.supplyAsync(() -> {
            CustomerTokenOutputDto customerTokenOutputDto;
            Optional<CustomerEntity> ce = customerRepository.findByEmail(requestBody.getEmail());
            if(ce.isEmpty()) {
                CustomerInputDto customerInputDto = customerInputTranslator.translate(requestBody);
                CreateCustomerDto ccd = customerClient.createCustomer(Constants.authorization, Constants.simulator, customerInputDto);
                CustomerEntity customerEntity = customerTranslator.translate(ccd);
                customerRepository.save(customerTranslator.translate(ccd));
                customerTokenOutputDto = createSingleUseCustomerToken(customerEntity);
            }
            else{
                final CustomerTokenOutputDto[] customerTokenOutputDtos = {new CustomerTokenOutputDto()};
                ce.ifPresent(customerEntity -> {
                    customerTokenOutputDtos[0] = createSingleUseCustomerToken(customerEntity);
                });
                return customerTokenOutputDtos[0];
            }
            return customerTokenOutputDto;
        });
    }
    public CustomerTokenOutputDto createSingleUseCustomerToken(CustomerEntity ce){
        SingleUseCustomerTokenInputDto singleUseCustomerTokenInputDto = singleUseTokenInputTranslator.translate(ce);
        SingleUseCustomerTokenOutputDto singleUseCustomerTokenOutputDto = customerClient.singleUseCustomerToken(Constants.authorization, Constants.simulator,ce.getId(), singleUseCustomerTokenInputDto);
        CustomerTokenOutputDto customerTokenOutputDto = new CustomerTokenOutputDto();
        customerTokenOutputDto.setSingleUseCustomerToken(singleUseCustomerTokenOutputDto.getSingleUseCustomerToken());
        customerTokenOutputDto.setCustomerId(singleUseCustomerTokenOutputDto.getCustomerId());
        customerTokenOutputDto.setMerchantRefNum(singleUseCustomerTokenInputDto.getMerchantRefNum());
        return customerTokenOutputDto;
    }
}
