package com.paysafe.anoop.cardPayment.dto.input;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@lombok.Data
@RequiredArgsConstructor
public class CreateCustomerInputDto {
    String email;
    String firstName;
}
