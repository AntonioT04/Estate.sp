package com.example.demo.house;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class HouseService {

    private final HouseRepository houseRepository;

    @Autowired
    public HouseService(HouseRepository houseRepository) {
        this.houseRepository = houseRepository;
    }

    public List<House> getHouses(){
        return houseRepository.findAll();
    }

    public Optional<House> getHouse(String seller){
        return houseRepository.findHouseBySeller(seller);
    }

    public void addNewHouse(House house) {
        Optional<House> houseOptional= houseRepository
                .findHouseBySeller(house.getSeller());
        if (houseOptional.isPresent()){
            throw new IllegalStateException("house added already");
        }
        houseRepository.save(house);
    }

    public void deleteHouse(Long houseId) {
        houseRepository.findById(houseId);
        boolean exists = houseRepository.existsById(houseId);
        if (!exists){
            throw new IllegalStateException(
                    "House with id " + houseId+ " does not exists");
        }
        houseRepository.deleteById(houseId);
    }

    @Transactional
    public void updateHouse(Long houseId,
                              String price,
                              String seller) {
        House house = houseRepository.findById(houseId)
                .orElseThrow(() -> new IllegalStateException(
                        "student with id "+houseId + " does not exist"));
        if (price != null &&
                seller.length() > 0 &&
                !Objects.equals(house.getSeller(),seller)){
            house.setSeller(seller);
        }
        if (seller != null &&
                seller.length() > 0 &&
                !Objects.equals(house.getSeller(),house)){
            Optional<House>studentOptional= houseRepository
                    .findHouseBySeller(seller);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("House already made");
            }
            house.setSeller(seller);
        }
    }
}
