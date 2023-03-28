package com.example.demo.house;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HouseRepository
        extends JpaRepository<House, Long > {
    @Query("SELECT h FROM House h WHERE h.seller = ?1")


    Optional<House> findHouseBySeller(String seller);
}
