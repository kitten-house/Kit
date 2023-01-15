package com.kit.backend.api.entity;


import lombok.AllArgsConstructor;
import org.springframework.lang.*;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table (name = "address")
@Data
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private int id;
    @Column(name = "country")
    @NonNull
    private String country;
    @Column(name = "city")
    @NonNull
    private String city;
    @Column(name = "street")
    @NonNull
    private String street;
    @Column(name = "house_number")
    @NonNull
    private String house_number;
    @Column(name = "apartment")
    @Nullable
    private String apartment;

    public Address() {
    }


}
