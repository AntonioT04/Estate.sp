package com.example.demo.house;


import com.example.demo.customer.Customer;
import com.example.demo.customer.CustomerRepository;
import com.example.demo.salesperson.Salesperson;
import com.example.demo.salesperson.SalespersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path= "api/v1/house")
public class HouseController {
    @Autowired
    HouseRepository houseRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    SalespersonRepository salespersonRepository;

    private final HouseService houseService;
    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService   = houseService;
    }
    @GetMapping
    public List<House> getHouses(){
        return houseService.getHouses();
    }

    @GetMapping(path = "{model}")
    public Optional<House> getHouse(@PathVariable("model") String model){
        return houseService.getHouse(model);
    }

    @PostMapping
    public void registerNewHouse(@RequestBody  House house){
        houseService.addNewHouse(house);
    }

    @PutMapping("/{houseId}/customers/{customerId}")
    House regCustomerToHouse(
            @PathVariable Long houseId,
            @PathVariable Long customerId
    ){
        House house = houseRepository.findById(houseId).get();
        Customer customer =customerRepository.findById(customerId).get();
        house.regCustomer(customer);
        return houseRepository.save(house);
    }

    @PutMapping("/{houseId}/salesperson/{salespersonId}")
    House assignSalespersonToHouse(
            @PathVariable Long houseId,
            @PathVariable Long salespersonId
    ){
        House house = houseRepository.findById(houseId).get();
        Salesperson salesperson =salespersonRepository.findById(salespersonId).get();
        house.assignSalesperson(salesperson);
        return houseRepository.save(house);
    }

    @DeleteMapping(path = "{houseId}")
    public void deleteHouse(@PathVariable("houseId")Long houseId){
        houseService.deleteHouse(houseId);
    }


    @PutMapping(path= "{houseId}")
    public void updateStudent(
            @PathVariable("houseId") Long houseId,
            @RequestParam(required = false)String price,
            @RequestParam(required = false)String model){
        houseService.updateHouse(houseId,price,model);
    }


    @GetMapping({"/showHouses", "/","/list" })
    public ModelAndView showHouses(){
        ModelAndView mav = new ModelAndView("list-houses");
        List<House> list = houseRepository.findAll();
        mav.addObject("houses", list);
        return mav;
    }

}
