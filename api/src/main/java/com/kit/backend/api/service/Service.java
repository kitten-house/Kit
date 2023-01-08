package com.kit.backend.api.service;

import com.kit.backend.api.entity.House;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Service {
    House saveHouse(House house);
//    House filteredHouse (House house);
    List<House> only20House();
    House findHouseBy(int id);
    List <House> findAll();
    List <House> findByUserId(int id);

}
