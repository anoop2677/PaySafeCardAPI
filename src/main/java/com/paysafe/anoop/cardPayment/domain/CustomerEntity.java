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
    @Column(name = "EMAIL", unique = true, nullable = false, updatable = false)
    String email;
}
