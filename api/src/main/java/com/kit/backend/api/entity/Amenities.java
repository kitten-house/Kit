package com.kit.backend.api.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "amenities")
public class Amenities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "wi_fi")
    private boolean wi_fi;
    @Column(name = "air_conditioning")
    private boolean air_conditioning;
    @Column(name = "washing_machine")
    private boolean washing_machine;
    @Column(name = "heating")
    private boolean heating;
    @Column(name = "pool")
    private boolean pool;
    @Column(name = "indoor_fireplace")
    private boolean indoor_fireplace;

    public Amenities() {
    }

    public Amenities(int id, boolean wi_fi, boolean air_conditioning, boolean washing_machine, boolean heating, boolean pool, boolean indoor_fireplace) {
        this.id = id;
        this.wi_fi = wi_fi;
        this.air_conditioning = air_conditioning;
        this.washing_machine = washing_machine;
        this.heating = heating;
        this.pool = pool;
        this.indoor_fireplace = indoor_fireplace;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isWi_fi() {
        return wi_fi;
    }

    public void setWi_fi(boolean wi_fi) {
        this.wi_fi = wi_fi;
    }

    public boolean isAir_conditioning() {
        return air_conditioning;
    }

    public void setAir_conditioning(boolean air_conditioning) {
        this.air_conditioning = air_conditioning;
    }

    public boolean isWashing_machine() {
        return washing_machine;
    }

    public void setWashing_machine(boolean washing_machine) {
        this.washing_machine = washing_machine;
    }

    public boolean isHeating() {
        return heating;
    }

    public void setHeating(boolean heating) {
        this.heating = heating;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isIndoor_fireplace() {
        return indoor_fireplace;
    }

    public void setIndoor_fireplace(boolean indoor_fireplace) {
        this.indoor_fireplace = indoor_fireplace;
    }

    @Override
    public String toString() {
        return "Amenities{" +
            "id=" + id +
            ", wi_fi=" + wi_fi +
            ", air_conditioning=" + air_conditioning +
            ", washing_machine=" + washing_machine +
            ", heating=" + heating +
            ", pool=" + pool +
            ", indoor_fireplace=" + indoor_fireplace +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amenities amenities = (Amenities) o;
        return id == amenities.id && wi_fi == amenities.wi_fi && air_conditioning == amenities.air_conditioning && washing_machine == amenities.washing_machine && heating == amenities.heating && pool == amenities.pool && indoor_fireplace == amenities.indoor_fireplace;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, wi_fi, air_conditioning, washing_machine, heating, pool, indoor_fireplace);
    }
}
