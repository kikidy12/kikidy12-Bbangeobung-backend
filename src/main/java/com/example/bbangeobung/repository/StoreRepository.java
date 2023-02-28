package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.Store;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {


    @Query("select s from Store s where s.user.id = :uesrId")
    @EntityGraph(attributePaths = {
            "user",
            "storeLikeUsers",
            "infoFishBreadTypes",
            "infoFishBreadTypes.fishBreadType"
    })
    List<Store> findByUserIdJPQL(Long uesrId);

    @Query("select s from Store s where s.id = :id")
    @EntityGraph(attributePaths = {
            "storeLikeUsers",
            "user", "infoFishBreadTypes",
            "infoFishBreadTypes.fishBreadType"
    })
    Optional<Store> findByIdJPQL(Long id);

    @Query("select s from Store s where s.id = :id")
    @EntityGraph(attributePaths = {
            "user", "storeItems", "storeLikeUsers"
    })
    Optional<Store> findByIdJPQLV2(Long id);

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
            "user", "storeLikeUsers"
    })
    List<Store> findAllJPQL(Long typeId);


    @Query("SELECT s FROM Store s " +
            "JOIN FETCH s.storeItems si " +
            "WHERE  :name IS NULL OR :name = '' or EXISTS (" +
            "SELECT s2.id FROM Store s2 " +
            "INNER JOIN s2.storeItems si2 " +
            "WHERE s.id = s2.id AND si2.name LIKE CONCAT('%', :name, '%')) " +
            "ORDER BY CASE WHEN :name IS NULL OR :name = '' THEN s.id " +
            "ELSE (" +
            "SELECT si3.price FROM Store s3 " +
            "INNER JOIN s3.storeItems si3 " +
            "WHERE s.id = s3.id AND si3.name LIKE CONCAT('%', :name, '%')" +
            ") END ASC")
    @EntityGraph(attributePaths = {
            "user", "storeLikeUsers"
    })
    List<Store> findAllByItemName(String name);
}
