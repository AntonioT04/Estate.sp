package com.example.demo.salesperson;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SalespersonService {

    private final SalespersonRepository salespersonRepository;

    @Autowired
    public SalespersonService(SalespersonRepository salespersonRepository) {
        this.salespersonRepository = salespersonRepository;
    }

    public List<Salesperson> getSalespersons(){
        return salespersonRepository.findAll();
    }

    public Optional<Salesperson> getSalesperson(Long model){
        return salespersonRepository.findById(model);
    }

    public void addNewSalesperson(Salesperson salesperson) {
        Optional<Salesperson> salespersonOptional= salespersonRepository
                .findSalespersonByName(salesperson.getName());
        if (salespersonOptional.isPresent()){
            throw new IllegalStateException("salesperson added already");
        }
        salespersonRepository.save(salesperson);
    }

    public void deleteSalesperson(Long salespersonId) {
        salespersonRepository.findById(salespersonId);
        boolean exists = salespersonRepository.existsById(salespersonId);
        if (!exists){
            throw new IllegalStateException(
                    "Salesperson with id " + salespersonId+ " does not exists");
        }
        salespersonRepository.deleteById(salespersonId);
    }

    @Transactional
    public void updateSalesperson(Long salespersonId,
                               String brand,
                               String model) {
        Salesperson salesperson = salespersonRepository.findById(salespersonId)
                .orElseThrow(() -> new IllegalStateException(
                        "salesperson with id "+salespersonId + " does not exist"));
        if (brand != null &&
                model.length() > 0 &&
                !Objects.equals(salesperson.getName(),model)){
            salesperson.getName();
        }
        if (model != null &&
                model.length() > 0 &&
                !Objects.equals(salesperson.getName(),salesperson)){
            Optional<Salesperson>studentOptional= salespersonRepository
                    .findSalespersonByName(model);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Salesperson already made");
            }
            salesperson.setName(model);
        }
    }
}