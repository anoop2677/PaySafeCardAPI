package com.paysafe.anoop.cardPayment.service;

import com.paysafe.anoop.cardPayment.Constants;
import com.paysafe.anoop.cardPayment.client.CustomerClient;
import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.input.CustomerInputDto;
import com.paysafe.anoop.cardPayment.dto.input.SingleUseCustomerTokenInputDto;
import com.paysafe.anoop.cardPayment.dto.output.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.dto.output.SingleUseCustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.models.CreateCustomerInputDto;
import com.paysafe.anoop.cardPayment.repository.CustomerRepository;
import com.paysafe.anoop.cardPayment.translator.CustomerInputTranslator;
import com.paysafe.anoop.cardPayment.translator.CustomerToEntityTranslator;
import com.paysafe.anoop.cardPayment.translator.EntityToCustomerTranslator;
import com.paysafe.anoop.cardPayment.translator.SingleUseTokenInputTranslator;
import lombok.RequiredArgsConstructor;
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
    EntityToCustomerTranslator entityToCustomerTranslator;
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
                customerRepository.save(customerTranslator.translate(ccd));
                customerTokenOutputDto = createSingleUseCustomerToken(ccd);
            }
            else{
                final CreateCustomerDto[] ccd = {new CreateCustomerDto()};
                ce.ifPresent(customerEntity -> {
                    ccd[0] = entityToCustomerTranslator.translate(customerEntity);
                });
                customerTokenOutputDto = createSingleUseCustomerToken(ccd[0]);
            }
            return customerTokenOutputDto;
        });
    }
    public CustomerTokenOutputDto createSingleUseCustomerToken(CreateCustomerDto ccd){
        SingleUseCustomerTokenInputDto singleUseCustomerTokenInputDto = singleUseTokenInputTranslator.translate(ccd);
        SingleUseCustomerTokenOutputDto singleUseCustomerTokenOutputDto = customerClient.singleUseCustomerToken(Constants.authorization, Constants.simulator,ccd.getId(), singleUseCustomerTokenInputDto);
        CustomerTokenOutputDto customerTokenOutputDto = new CustomerTokenOutputDto();
        customerTokenOutputDto.setSingleUseCustomerToken(singleUseCustomerTokenOutputDto.getSingleUseCustomerToken());
        customerTokenOutputDto.setCustomerId(singleUseCustomerTokenOutputDto.getCustomerId());
        customerTokenOutputDto.setCustomerIp(ccd.getIp());
        customerTokenOutputDto.setMerchantRefNum(ccd.getMerchantCustomerId());
        return customerTokenOutputDto;
    }
}
