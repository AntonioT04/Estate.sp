package com.example.demo.salesperson;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SalespersonRepository
     extends JpaRepository<Salesperson, Long >
{
    @Query("SELECT s FROM Salesperson s WHERE s.name = ?1")

    Optional<Salesperson> findSalespersonByName(String name);
}