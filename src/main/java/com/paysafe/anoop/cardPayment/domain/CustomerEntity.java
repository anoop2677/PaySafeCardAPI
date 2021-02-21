package com.paysafe.anoop.cardPayment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "CUSTOMERS", schema = "CUSTOMERS")
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {
    @Id
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    String id;
    @Column(name = "MERCHANT_CUSTOMER_ID", nullable = false)
    String merchantCustomerId;
    @Column(name = "LOCALE")
    String locale;
    @Column(name = "FIRST_NAME")
    String firstName;
    @Column(name = "LAST_NAME")
    String lastName;
    @Column(name = "MIDDLE_NAME")
    String middleName;
    @Column(name = "DATE_OF_BIRTH")
    String dateOfBirth;
    @Column(name = "EMAIL", unique = true, nullable = false, updatable = false)
    String email;
    @Column(name = "PHONE")
    String phone;
    @Column(name = "IP")
    String ip;
    @Column(name = "GENDER")
    String gender;
    @Column(name = "NATIONALITY")
    String nationality;
    @Column(name = "CELLPHONE")
    String cellPhone;
    @Column(name = "STATUS")
    String status;
    @Column(name = "PAYMENT_TOKEN")
    String paymentToken;
}
