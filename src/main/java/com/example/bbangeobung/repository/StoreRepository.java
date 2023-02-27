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

    @Query("select distinct s from Store s " +
            "left join fetch s.infoFishBreadTypes sifbt " +
            "left join fetch sifbt.fishBreadType fbt " +
            "where (:typeId = 0L or exists (" +
            "select fbt2 from Store s2 " +
            "inner join s2.infoFishBreadTypes sifbt2 " +
            "inner join sifbt2.fishBreadType fbt2 " +
            "where s.id = s2.id and fbt2.id = :typeId)) " +
            "order by case when :typeId = 0L THEN s.id ELSE COALESCE(sifbt.price, 0) END ASC")
    @EntityGraph(attributePaths = {
            "user"
    })
    List<Store> findAllJPQL(Long typeId);
}
