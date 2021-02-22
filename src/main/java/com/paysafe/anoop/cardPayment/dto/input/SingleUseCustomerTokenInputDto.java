package com.paysafe.anoop.cardPayment.dto.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class SingleUseCustomerTokenInputDto {
    String merchantRefNum;
    ArrayList<String> paymentTypes;
}
