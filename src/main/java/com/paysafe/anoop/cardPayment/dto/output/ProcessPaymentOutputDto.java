package com.paysafe.anoop.cardPayment.dto.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcessPaymentOutputDto {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class GatewayResponse {
         String authCode;
         String avsResponse;
         String cvvVerification;
         boolean serializable;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class BillingDetails {
        String nickName;
        String street;
        String street2;
        String city;
        String state;
        String zip;
        String country;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class MerchantDescriptor {
        String dynamicDescriptor;
        String phone;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Profile {
        String id;
        String merchantCustomerId;
        String locale;
        String firstName;
        String lastName;
        String email;
        String phone;
    }
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    class Card {
        @Getter
        @Setter
        @AllArgsConstructor
        @NoArgsConstructor
        class CardExpiry{
            String month;
            String year;
        }
        CardExpiry cardExpiry;
        String holderName;
        String cardType;
        String cardBin;
        String lastDigits;
        String cardCategory;
    }
    String id;
    int amount;
    String merchantRefNum;
    String settleWithAuth;
    String paymentHandleToken;
    String txnTime;
    BillingDetails billingDetails;
    GatewayResponse gatewayResponse;
    String customerIp;
    String preAuth;
    boolean dupCheck;
    String description;
    MerchantDescriptor merchantDescriptor;
    Card card;
    Profile profile;
    String currencyCode;
    String paymentType;
    String status;
    int availableToSettle;

}
