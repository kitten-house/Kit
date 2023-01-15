package com.kit.backend.api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "house")
@Data
@AllArgsConstructor
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private int id;
    @Column(name = "id_user")
    @NonNull
    private long id_user;
    @Column(name = "property_type")
    @NonNull
    private String property_type;
    @Column(name = "price")
    @NonNull
    private int price;
    @Column(name = "house_area")
    @NonNull
    private double house_area;
    @Column(name = "rooms")
    @NonNull
    private int room;
    @Column(name = "description")
    @Nullable
    private String description;
    @Column(name = "photo")
    @NonNull
    private String photo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amenities_id")
    @Nullable
    private Amenities amenities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    @NonNull
    private Address address;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    @Nullable
    private List<Reservation> reservations;

    public House() {
    }

    public void addReservationToHouse(Reservation reservation){
        if (reservations==null){
            reservations = new ArrayList<Reservation>();
        }
        reservations.add(reservation);
       reservation.setHouse(this);

    }
}