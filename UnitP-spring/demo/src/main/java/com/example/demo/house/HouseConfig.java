package com.example.demo.house;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class HouseConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            HouseRepository repository){
        return args -> {
            House house1 = new House(

                    "100,000",
                    "Antonio Thornton"

            );

            House house2 = new House(
                    "250,000",
                    "Brittany Cohen"

            );

            repository.saveAll(
                    List.of(house1,house2)
            );
        };
    }
}