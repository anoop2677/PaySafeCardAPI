package com.paysafe.anoop.cardPayment.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerTokenOutputDto {
    String customerId;
    String singleUseCustomerToken;
    String merchantRefNum;
    String customerIp;
}
