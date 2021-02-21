package com.paysafe.anoop.cardPayment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SingleUseCustomerTokenDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class DateOfBirth{
        int day;
        int month;
        int year;
    }
    String id;
    String customerId;
    int timeToLiveSeconds;
    String status;
    String singleUseCustomerToken;
    ArrayList<String> paymentTypes;
    String locale;
    String firstName;
    String middleName;
    String lastName;
    DateOfBirth dateOfBirth;
    String email;
    String phone;
    String ip;
    String nationality;
}
