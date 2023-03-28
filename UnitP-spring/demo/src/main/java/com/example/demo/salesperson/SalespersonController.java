package com.example.demo.salesperson;


import com.example.demo.house.House;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/v1/salesperson")
public class SalespersonController {
    @Autowired
    SalespersonRepository salespersonRepository;





    private final SalespersonService salespersonService;
    @Autowired
    public SalespersonController(SalespersonService salespersonService) {
        this.salespersonService   = salespersonService;
    }
    @GetMapping
    public List<Salesperson> getSalespersons(){
        return salespersonService.getSalespersons();
    }

    @GetMapping(path = "{model}")
    public Optional<Salesperson> getSalesperson(@PathVariable("model") Long model){
        return salespersonService.getSalesperson(model);
    }

    @PostMapping
    public void registerNewSalesperson(@RequestBody Salesperson salesperson){
        salespersonService.addNewSalesperson(salesperson);
    }





    @DeleteMapping(path = "{salespersonId}")
    public void deleteSalesperson(@PathVariable("salespersonId")Long salespersonId){
        salespersonService.deleteSalesperson(salespersonId);
    }


    @PutMapping(path= "{salespersonId}")
    public void updateStudent(
            @PathVariable("salespersonId") Long salespersonId,
            @RequestParam(required = false)String brand,
            @RequestParam(required = false)String model){
        salespersonService.updateSalesperson(salespersonId,brand,model);
    }

}