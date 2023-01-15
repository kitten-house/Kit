package com.kit.backend.api.repo;

import com.kit.backend.api.entity.Reservation;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM reservations WHERE reservations.house_id = :id",nativeQuery = true)
    List<Reservation> findByHouse(@Param("id") int idHouse);

    @Query(value = "SELECT * FROM reservations WHERE reservations.user_id = :id",nativeQuery = true)
    List<Reservation> findByUser(@Param("id") long idUser);

    @Query(value = "SELECT * FROM reservations WHERE reservations.id = :id",nativeQuery = true)
    Optional<Reservation> findBy(@Param("id") long id);

   // Reservation addReservation (Reservation reservation);

}
