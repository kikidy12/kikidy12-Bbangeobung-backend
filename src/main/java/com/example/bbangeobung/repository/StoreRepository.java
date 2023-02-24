package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("select s from Store s where s.id = :id")
    @EntityGraph(attributePaths = {
            "user", "infoFishBreadTypes",
            "infoFishBreadTypes.fishBreadType"
    })
    Optional<Store> findByIdJPQL(Long id);

    @Query("select s from Store s")
    @EntityGraph(attributePaths = {
            "user", "infoFishBreadTypes",
            "infoFishBreadTypes.fishBreadType"
    })
    List<Store> findAllJPQL();
}
