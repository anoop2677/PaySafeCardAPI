package com.paysafe.anoop.cardPayment.repository;

import com.paysafe.anoop.cardPayment.domain.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, String> {
    @Transactional(readOnly = true)
    Optional<CustomerEntity> findByEmail(String name);
}
