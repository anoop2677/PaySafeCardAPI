package com.paysafe.anoop.cardPayment.controller;

import com.paysafe.anoop.cardPayment.dto.output.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.dto.output.ProcessPaymentOutputDto;
import com.paysafe.anoop.cardPayment.dto.input.CreateCustomerInputDto;
import com.paysafe.anoop.cardPayment.dto.input.PaymentRequest;
import com.paysafe.anoop.cardPayment.service.CreateCustomerService;
import com.paysafe.anoop.cardPayment.service.ProcessPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import org.modelmapper.ModelMapper;


@RestController
@RequiredArgsConstructor
public class PaySafeController {

    private final ModelMapper modelMapper;
    private final CreateCustomerService createCustomerService;
    private final ProcessPaymentService processPaymentService;


    @PostMapping("/token")
    public CompletableFuture<ResponseEntity<CustomerTokenOutputDto>> createCustomer(@RequestBody CreateCustomerInputDto createCustomerInputDto) throws Exception {
        CompletableFuture<CustomerTokenOutputDto> result = createCustomerService.createCustomer(createCustomerInputDto)
                .thenApply(cst -> modelMapper.map(cst, CustomerTokenOutputDto.class));
        result.handleAsync((response, exception) -> {
            if (exception != null) {
                try {
                    throw new Exception("data cannot be created");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        });
        return result.thenApply(ResponseEntity::ok);

    }

    @PostMapping("/payment")
    public CompletableFuture<ResponseEntity<ProcessPaymentOutputDto>> processPayment(@RequestBody PaymentRequest data) throws Exception {
        CompletableFuture<ProcessPaymentOutputDto> result = processPaymentService.processPayment(data)
                .thenApply(cst -> modelMapper.map(cst, ProcessPaymentOutputDto.class));
        result.handleAsync((response, exception) -> {
            if (exception != null) {
                try {
                    throw new Exception("data cannot be created");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            return response;
        });
        return result.thenApply(ResponseEntity::ok);
    }
}
