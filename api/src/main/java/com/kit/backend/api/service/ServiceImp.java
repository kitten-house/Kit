package com.kit.backend.api.service;

import com.kit.backend.api.entity.Amenities;
import com.kit.backend.api.entity.House;
import com.kit.backend.api.repo.HousesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImp implements Service {

    private final HousesRepo housesRepo;

    public ServiceImp(HousesRepo housesRepo) {
        this.housesRepo = housesRepo;
    }

    public House saveHouse(House house){
        return housesRepo.save(house);
    }
    public House findHouseBy(int id){
        return housesRepo.findHouseBy(id);
    }

    @Override
    public List<House> findAll() {
        return housesRepo.findAllHouses();
    }

    @Override
    public List<House> findByUserId(int id) {
        return housesRepo.findHouseByUserId(id);
    }

    ;



    // options = ["heating", "wi-fi"]
//    @Override
//    public House filteredHouse(List<String> options) {
//        Amenities a;
//        String query = "";
//        if (a.isWi_fi()) {
//            query += "amenities.wi_fi = true";
//        }
//
//        String amenities = String.join(",", options); // "heating, wi-fi"
//
//        return housesRepo.filter(query);
//    }

    @Override
    public List <House> only20House() {
        return housesRepo.findFist20House();
    }

}
