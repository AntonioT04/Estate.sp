package com.example.demo.house;

import com.example.demo.customer.Customer;
import com.example.demo.salesperson.Salesperson;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "house_sequence")

     private  Long id;

    @ManyToMany
    @JoinTable(
            name= "customer_reg",
            joinColumns = @JoinColumn(name= "house_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private Set<Customer> regCustomers = new HashSet<>();


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "salesperson_id", referencedColumnName = "id")
    private Salesperson salesperson;
    private String price;
    private String seller;






    public House(Long id,
                   String price,
                   String seller

    ) {
        this.id = id;
        this.price = price;
        this.seller = seller;


    }

    public House(String price, String seller) {
        this.price = price;
        this.seller = seller;

    }
    public House() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }


    public Set<Customer> getRegCustomers() {
        return regCustomers;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }








    @Override
    public String toString()
    {
        return "House{" +
                "id=" + id +
                ", price='" + price + '\'' +
                ", seller='" + seller + '\'' +


                '}';
    }

    public void regCustomer(Customer customer) {
        regCustomers.add(customer);
    }

    public void assignSalesperson(Salesperson salesperson) {
        this.salesperson= salesperson;
    }


}