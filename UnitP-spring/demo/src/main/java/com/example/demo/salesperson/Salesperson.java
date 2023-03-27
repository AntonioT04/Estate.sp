package com.example.demo.salesperson;


import com.example.demo.house.House;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Salesperson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @JsonIgnore
    @OneToMany(mappedBy = "salesperson")
    private Set<House> house= new HashSet<>();

    private String name;
    private String phoneNumber;

    public Salesperson(Long id, Set<House> house, String name, String phoneNumber) {
        this.id = id;
        this.house = house;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Salesperson(){

    }


    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }

    private String getPhoneNumber() {
        return phoneNumber;
    }
    public Set<House> getHouse() {
        return house;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber= phoneNumber;
    }

    @Override
    public String toString() {
        return "Salesperson{" +
                "id=" + id +
                ", house=" + house +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}