package com.kit.backend.api.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

import org.springframework.lang.*;

@Entity
@Table(name = "reservations")
@Data
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private long id;

    @Column(name = "user_id")
    @NonNull
    private long userId;

    @Column(name = "check_in_date")
    @NonNull
    private Date checkIn;
    @Column(name = "check_out_date")
    @NonNull
    private Date checkOut;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "house_id")
    @NonNull
    private House house;

    public Reservation() {

    }
}
