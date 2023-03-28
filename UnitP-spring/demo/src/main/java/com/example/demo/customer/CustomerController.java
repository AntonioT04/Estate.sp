package com.example.demo.customer;


import com.example.demo.salesperson.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/v1/customer")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;



    @Autowired
    SalespersonRepository storeRepository;

    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService   = customerService;
    }
    @GetMapping
    public List<Customer> getCustomers(){
        return customerService.getCustomers();
    }

    @GetMapping(path = "{model}")
    public Optional<Customer> getCustomer(@PathVariable("model") Long model){
        return customerService.getCustomer(model);
    }

    @PostMapping
    public void registerNewCustomer(@RequestBody  Customer customer){
        customerService.addNewCustomer(customer);
    }





    @DeleteMapping(path = "{customerId}")
    public void deleteCustomer(@PathVariable("customerId")Long customerId){
        customerService.deleteCustomer(customerId);
    }


    @PutMapping(path= "{customerId}")
    public void updateStudent(
            @PathVariable("customerId") Long customerId,
            @RequestParam(required = false)String brand,
            @RequestParam(required = false)String model){
        customerService.updateCustomer(customerId,brand,model);
    }

}