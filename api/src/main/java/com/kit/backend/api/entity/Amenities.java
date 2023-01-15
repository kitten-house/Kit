package com.kit.backend.api.entity;


import lombok.AllArgsConstructor;
import org.springframework.lang.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "amenities")
@Data
@AllArgsConstructor
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private int id;
    @Column(name = "wi_fi")
    @Nullable
    private boolean wi_fi;
    @Column(name = "air_conditioning")
    @Nullable
    private boolean air_conditioning;
    @Column(name = "washing_machine")
    @Nullable
    private boolean washing_machine;
    @Column(name = "heating")
    @Nullable
    private boolean heating;
    @Column(name = "pool")
    @Nullable
    private boolean pool;
    @Column(name = "indoor_fireplace")
    @Nullable
    private boolean indoor_fireplace;

    public Amenities() {
    }
}
