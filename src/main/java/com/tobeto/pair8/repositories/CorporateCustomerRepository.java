package com.tobeto.pair8.repositories;

import com.tobeto.pair8.entities.concretes.CorporateCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CorporateCustomerRepository extends JpaRepository<CorporateCustomer, Integer> {

    boolean existsByTaxNo(String taxNo);
    boolean existsByCompanyName(String componyName);
    boolean existsByUserId(int userId);
}
