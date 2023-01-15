package com.kit.backend.api.repo;

import com.kit.backend.api.entity.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HousesRepo extends JpaRepository<House, Integer> {

//    @Query(
//        value = "" +
//            "INSERT INTO address VALUES (:h.address.id, :h.address.country,:h.address.city,:h.address.street,:h.address.house_number,:h.address.apartment);\n" +
//            "INSERT INTO house VALUES (:h.id, :h.id_user, :h.address.id, :h.property_type, :h.price, :h.house_area, :h.rooms, :h.amenities);" +
//            "", nativeQuery = true
//    )
//    House saveHouse(@Param("h") House entity);


    @Query(value = "SELECT * FROM house WHERE house.id = :id", nativeQuery = true)
    House findHouseBy(@Param("id") int id);

//    // amenities.wi_fi = true AND amenities.heating = true
//    @Query(value = "SELECT * FROM house INNER JOIN amenities ON house.amenities_id = amenities.id WHERE ?1", nativeQuery = true)
//    House filter(String additional);

    @Query(value = "SELECT house.id, price, country, city FROM house LEFT JOIN address a on house.id_address = a.id LIMIT 20", nativeQuery = true)
    List <House> findFist20House();


    @Query(value = "SELECT * FROM house",nativeQuery = true)
    List<House> findAllHouses();

    @Query(value = "SELECT * FROM house, address, amenities WHERE house.amenities_id = amenities.id AND house.id_address = address.id AND house.id_user = :id",nativeQuery = true)
    List<House> findHouseByUserId(@Param("id") int id);
}
