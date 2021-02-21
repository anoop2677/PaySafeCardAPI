package com.paysafe.anoop.cardPayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import com.paysafe.anoop.cardPayment.dto.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.CustomerTokenOutputDto;
import com.paysafe.anoop.cardPayment.dto.SingleUseCustomerTokenDto;
import com.paysafe.anoop.cardPayment.models.Data;
import com.paysafe.anoop.cardPayment.repository.CustomerRepository;
import com.paysafe.anoop.cardPayment.translator.CustomerToEntityTranslator;
import com.paysafe.anoop.cardPayment.translator.EntityToCustomerTranslator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CreateCustomerServiceImpl implements CreateCustomerService{

    private final CustomerRepository customerRepository;
    private final CustomerToEntityTranslator customerTranslator;
    private final EntityToCustomerTranslator entityToCustomerTranslator;
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    ObjectMapper objectMapper = new ObjectMapper();

    public CompletableFuture<CustomerTokenOutputDto> createCustomer(Data requestBody) {
        return CompletableFuture.supplyAsync(() -> {
            CustomerTokenOutputDto customerTokenOutputDto = new CustomerTokenOutputDto();
            Optional<CustomerEntity> ce = customerRepository.findByEmail(requestBody.getEmail());
            if(ce.isEmpty()) {
                CreateCustomerDto ccd = new CreateCustomerDto();
                Map<Object, Object> data = new HashMap<>();
                Map<Object, Object> obj = new HashMap<>();

                obj.put("year", 1990);
                obj.put("month", 7);
                obj.put("day", 1);

                data.put("merchantCustomerId", UUID.randomUUID().toString());
                data.put("locale", "en_US");
                data.put("firstName", requestBody.getFirstName());
                data.put("middleName", "James");
                data.put("lastName", "Dee");
                data.put("dateOfBirth", obj);
                data.put("email", requestBody.getEmail());
                data.put("phone", "1234567890");
                data.put("ip", "10.10.1.111");
                data.put("gender", "M");
                data.put("nationality", "US");
                data.put("cellPhone", "1234567890");

                try {
                    String requestData = objectMapper
                            .writerWithDefaultPrettyPrinter()
                            .writeValueAsString(data);
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create("https://api.test.paysafe.com/paymenthub/v1/customers"))
                            .setHeader("Content-Type", "application/json")
                            .setHeader("Authorization", "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4")
                            .setHeader("Simulator", "EXTERNAL")
                            .POST(HttpRequest.BodyPublishers.ofString(requestData))
                            .build();
                    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    ccd = objectMapper.readValue(response.body(), CreateCustomerDto.class);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
        Map<Object, Object> data = new HashMap<>();
        SingleUseCustomerTokenDto singleUseCustomerTokenDto = new SingleUseCustomerTokenDto();
        data.put("merchantRefNum", ccd.getMerchantCustomerId());
        ArrayList<String> paymentTypes = new ArrayList<>();
        paymentTypes.add("CARD");
        data.put("paymentTypes", paymentTypes);
        try{
            String requestData = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(data);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://api.test.paysafe.com/paymenthub/v1/customers/"+ccd.getId()+"/singleusecustomertokens"))
                    .setHeader("Content-Type", "application/json")
                    .setHeader("Authorization", "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4")
                    .setHeader("Simulator", "EXTERNAL")
                    .POST(HttpRequest.BodyPublishers.ofString(requestData))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            singleUseCustomerTokenDto = objectMapper.readValue(response.body(), SingleUseCustomerTokenDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        CustomerTokenOutputDto customerTokenOutputDto = new CustomerTokenOutputDto();
        customerTokenOutputDto.setSingleUseCustomerToken(singleUseCustomerTokenDto.getSingleUseCustomerToken());
        customerTokenOutputDto.setCustomerId(singleUseCustomerTokenDto.getCustomerId());
        customerTokenOutputDto.setCustomerIp(ccd.getIp());
        customerTokenOutputDto.setMerchantRefNum(ccd.getMerchantCustomerId());
        return customerTokenOutputDto;
    }
}
