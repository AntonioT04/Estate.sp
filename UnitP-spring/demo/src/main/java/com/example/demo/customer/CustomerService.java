package com.example.demo.customer;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomer(Long model){
        return customerRepository.findById(model);
    }

    public void addNewCustomer(Customer customer) {
        Optional<Customer> customerOptional= customerRepository
                .findCustomerByName(customer.getName());
        if (customerOptional.isPresent()){
            throw new IllegalStateException("customer added already");
        }
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long customerId) {
        customerRepository.findById(customerId);
        boolean exists = customerRepository.existsById(customerId);
        if (!exists){
            throw new IllegalStateException(
                    "customer with id " + customerId+ " does not exists");
        }
        customerRepository.deleteById(customerId);
    }

    @Transactional
    public void updateCustomer(Long customerId,
                           String brand,
                           String model) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalStateException(
                        "customer with id "+customerId + " does not exist"));
        if (brand != null &&
                model.length() > 0 &&
                !Objects.equals(customer.getName(),model)){
            customer.getName();
        }
        if (model != null &&
                model.length() > 0 &&
                !Objects.equals(customer.getName(),customer)){
            Optional<Customer>studentOptional= customerRepository
                    .findCustomerByName(model);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Customer already made");
            }
            customer.setName(model);
        }
    }
}