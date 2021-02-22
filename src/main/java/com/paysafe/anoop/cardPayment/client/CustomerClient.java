package com.paysafe.anoop.cardPayment.client;

import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.input.CustomerInputDto;
import com.paysafe.anoop.cardPayment.dto.input.PaymentProcessInputDto;
import com.paysafe.anoop.cardPayment.dto.input.SingleUseCustomerTokenInputDto;
import com.paysafe.anoop.cardPayment.dto.output.ProcessPaymentOutputDto;
import com.paysafe.anoop.cardPayment.dto.output.SingleUseCustomerTokenOutputDto;
import feign.HeaderMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="CustomerClient", url="https://api.test.paysafe.com/paymenthub/v1")
public interface CustomerClient {

    @PostMapping(value = "/customers", consumes = MediaType.APPLICATION_JSON_VALUE)
    CreateCustomerDto createCustomer(@RequestHeader("Authorization") String authorization,
                                     @RequestHeader("Simulator") String simulator,
                                     @RequestBody CustomerInputDto customerInputDto);

    @PostMapping(value = "/customers/{customerId}/singleusecustomertokens", consumes = MediaType.APPLICATION_JSON_VALUE)
    SingleUseCustomerTokenOutputDto singleUseCustomerToken(@RequestHeader("Authorization") String authorization,
                                                           @RequestHeader("Simulator") String simulator,
                                                           @PathVariable("customerId") String customerId,
                                                           @RequestBody SingleUseCustomerTokenInputDto singleUseCustomerTokenInputDto);

    @PostMapping(value = "/payments", consumes = MediaType.APPLICATION_JSON_VALUE)
    ProcessPaymentOutputDto processPayment(@RequestHeader("Authorization") String authorization,
                                           @RequestHeader("Simulator") String simulator,
                                           @RequestBody PaymentProcessInputDto paymentProcessInputDto);
}
