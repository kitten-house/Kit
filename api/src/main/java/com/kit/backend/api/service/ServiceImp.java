package com.kit.backend.api.service;

import com.kit.backend.api.entity.House;
import com.kit.backend.api.entity.Reservation;
import com.kit.backend.api.repo.HousesRepo;
import com.kit.backend.api.repo.ReservationRepo;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImp implements Service {

    private final ReservationRepo reservationRepo;
    private final HousesRepo housesRepo;

    public ServiceImp(ReservationRepo reservationRepo, HousesRepo housesRepo) {
        this.reservationRepo = reservationRepo;
        this.housesRepo = housesRepo;
    }

    public House saveHouse(House house) {
        return housesRepo.save(house);
    }

    public Optional<House> findHouseBy(int id) {
        return housesRepo.findBy(id);
    }

    @Override
    public List<House> findAll() {
        return housesRepo.findAllHouses();
    }

    @Override
    public List<House> findByUserId(int id) {
        return housesRepo.findHouseByUserId(id);
    }

    @Override
    public List<House> only20House() {
        return housesRepo.findAll(Pageable.ofSize(20)).getContent();
    }


    @Override
    public Optional<Reservation> findReservationBy(long id) {
        return reservationRepo.findBy(id);
    }

    @Override
    public List<Reservation> findReservationByUser(long userId) {
        return reservationRepo.findByUser(userId);
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        return reservationRepo.save(reservation);
    }

    @Override
    public List<Reservation> findReservationByHouse(int houseId) {
        return reservationRepo.findByHouse(houseId);
    }


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


}
