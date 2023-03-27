package com.example.demo.customer;

import com.example.demo.house.House;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @ManyToMany(mappedBy = "regCustomers")

    private Set<House> houses = new HashSet<>();

    private String name;
    private String phoneNumber;

    public Customer(Set<House> houses, String name, String phoneNumber) {
        this.name = name;
        this.houses = houses;
        this.phoneNumber = phoneNumber;
    }
    public Customer(){

    }

    public Long getId(){
        return id;
    }
     public String getName(){
        return name;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Set<House> getHouses() {
        return houses;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", houses=" + houses +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
