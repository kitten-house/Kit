package com.kit.backend.api.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "house")
public class House {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "id_user")
    private long id_user;
    @Column(name = "property_type")
    private String  property_type;
    @Column(name = "price")
    private int price;
    @Column(name = "house_area")
    private double house_area;
    @Column(name = "rooms")
    private int room;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "amenities_id")
    private Amenities amenities;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_address")
    private Address address;

    public House() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getId_user() {
        return id_user;
    }

    public void setId_user(long id_user) {
        this.id_user = id_user;
    }

    public String getProperty_type() {
        return property_type;
    }

    public void setProperty_type(String property_type) {
        this.property_type = property_type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getHouse_area() {
        return house_area;
    }

    public void setHouse_area(double house_area) {
        this.house_area = house_area;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public Amenities getAmenities() {
        return amenities;
    }

    public void setAmenities(Amenities amenities) {
        this.amenities = amenities;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public House(int id, long id_user, String property_type, int price, double house_area, int room, Amenities amenities, Address address) {
        this.id = id;
        this.id_user = id_user;
        this.property_type = property_type;
        this.price = price;
        this.house_area = house_area;
        this.room = room;
        this.amenities = amenities;
        this.address = address;
    }

    @Override
    public String toString() {
        return "House{" +
            "id=" + id +
            ", id_user=" + id_user +
            ", property_type='" + property_type + '\'' +
            ", price=" + price +
            ", house_area=" + house_area +
            ", room=" + room +
            ", amenities=" + amenities +
            ", address=" + address +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return id == house.id && id_user == house.id_user && price == house.price && Double.compare(house.house_area, house_area) == 0 && room == house.room && Objects.equals(property_type, house.property_type) && Objects.equals(amenities, house.amenities) && Objects.equals(address, house.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, id_user, property_type, price, house_area, room, amenities, address);
    }
}

