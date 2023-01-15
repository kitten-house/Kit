package com.kit.backend.api.controller.api;

import com.kit.backend.api.entity.House;
import com.kit.backend.api.entity.Reservation;
import com.kit.backend.api.service.Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final Service service;

    public ApiController(Service service) {
        this.service = service;
    }

    @PostMapping("/save_house")
    public House addHouse(House house) {
        return service.saveHouse(house);
    }

    @PostMapping("/findHouse")
    public ResponseEntity<House> findById(int id) {
        Optional<House> optional = service.findHouseBy(id);
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().body(optional.get());
    }

//    public List<House> searchHouse (House house){
//        return service.filteredHouse(house);
//    }

    @GetMapping("/houses")
    public ResponseEntity<List<House>> only20House() {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        return ResponseEntity.ok().headers(responseHeaders).body(service.only20House());
    }

    @GetMapping("/allHouse")
    public List<House> allHouse() {
        return service.findAll();
    }

    @GetMapping("/myHouses")
    public List<House> findByUserId(int id) {
        return service.findByUserId(id);
    }

    @PostMapping("/reservation")
    public ResponseEntity<Reservation> findBy (long id){
        Optional<Reservation> optional = service.findReservationBy(id);
        if (optional.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok().body(optional.get());
    }

    @PostMapping("/allMyReservation")
    public List<Reservation> findByUserId(long id){
        return service.findReservationByUser(id);
    }

    @PostMapping("/allHouseReservation")
    public List<Reservation> findByHouseId (int id){
        return service.findReservationByHouse(id);
    }

    @PostMapping("/addReservation")
    public Reservation addReservation (Reservation reservation){
        return service.saveReservation(reservation);
    }
}
