package com.kit.backend.api.repo;

import com.kit.backend.api.entity.Address;
import com.kit.backend.api.entity.Amenities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ARepo extends JpaRepository<Amenities, Integer> {

//    @Transactional
//    void insert() {
//
//    }
}
