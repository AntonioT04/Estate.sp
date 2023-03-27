package com.example.demo.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository
     extends JpaRepository<Customer, Long >
    {
        @Query("SELECT c FROM Customer c WHERE c.name = ?1")
        Optional<Customer> findCustomerByName(String name);
    }

