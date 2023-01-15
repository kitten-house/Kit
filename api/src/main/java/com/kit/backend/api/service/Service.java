package com.kit.backend.api.service;

import com.kit.backend.api.entity.House;
import com.kit.backend.api.entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface Service {
    House saveHouse(House house);
//    House filteredHouse (House house);
    List<House> only20House();
    Optional<House> findHouseBy(int id);
    List <House> findAll();
    List <House> findByUserId(int id);

    Optional<Reservation> findReservationBy(long id);
    List<Reservation> findReservationByHouse(int houseId);
    List<Reservation> findReservationByUser(long userId);
    Reservation saveReservation(Reservation reservation);

}
