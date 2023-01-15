package com.kit.backend.api.controller.api;

import com.kit.backend.api.entity.Address;
import com.kit.backend.api.entity.House;
import com.kit.backend.api.service.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private Service service;

    @PostMapping("/save_house")
    public House addHouse(House house) {
        return service.saveHouse(house);
    }

    @PostMapping("/findHouse")
    public House findById(int id) {
        return service.findHouseBy(id);
    }

//    public List<House> searchHouse (House house){
//        return service.filteredHouse(house);
//    }

    @GetMapping("/")
    public List<House> only20House() {
        return service.only20House();
    }

    @GetMapping("/allHouse")
    public List<House> allHouse() {
        return service.findAll();
    }

    @GetMapping("/myHouses")
    public List<House> findByUserId(int id) {
        return service.findByUserId(id);
    }
}
