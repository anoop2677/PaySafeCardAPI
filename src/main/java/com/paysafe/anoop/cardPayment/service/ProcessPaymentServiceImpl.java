package com.paysafe.anoop.cardPayment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paysafe.anoop.cardPayment.dto.CreateCustomerDto;
import com.paysafe.anoop.cardPayment.dto.ProcessPaymentOutputDto;
import com.paysafe.anoop.cardPayment.models.Data;
import com.paysafe.anoop.cardPayment.models.PaymentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class ProcessPaymentServiceImpl implements ProcessPaymentService {
    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public CompletableFuture<ProcessPaymentOutputDto> processPayment(PaymentRequest data) {
        return CompletableFuture.supplyAsync(() -> {
            ProcessPaymentOutputDto processPaymentOutputDto = new ProcessPaymentOutputDto();
            Map<Object, Object> map = new HashMap<>();
            map.put("merchantRefNum", data.getMerchantRefNum());
            map.put("amount", data.getAmount());
            map.put("currencyCode", "USD");
            map.put("dupCheck", true);
            map.put("settleWithAuth", false);
            map.put("paymentHandleToken", data.getPaymentHandleToken());
            map.put("customerIp", "10.10.12.64");
            map.put("description", "Magazine subscription");
            try {
                String requestData = objectMapper
                        .writerWithDefaultPrettyPrinter()
                        .writeValueAsString(map);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("https://api.test.paysafe.com/paymenthub/v1/payments"))
                        .setHeader("Content-Type", "application/json")
                        .setHeader("Authorization", "Basic cHJpdmF0ZS03NzUxOkItcWEyLTAtNWYwMzFjZGQtMC0zMDJkMDIxNDQ5NmJlODQ3MzJhMDFmNjkwMjY4ZDNiOGViNzJlNWI4Y2NmOTRlMjIwMjE1MDA4NTkxMzExN2YyZTFhODUzMTUwNWVlOGNjZmM4ZTk4ZGYzY2YxNzQ4")
                        .setHeader("Simulator", "EXTERNAL")
                        .POST(HttpRequest.BodyPublishers.ofString(requestData))
                        .build();
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                    processPaymentOutputDto = objectMapper.readValue(response.body(), ProcessPaymentOutputDto.class);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return processPaymentOutputDto;
        });
        }
}
