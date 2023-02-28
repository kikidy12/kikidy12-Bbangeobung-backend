package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.FishBreadType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface FishBreadTypeRepository extends JpaRepository<FishBreadType, Long> {

    List<FishBreadType> findByIdIn(Set<Long> ids);

    Optional<FishBreadType> findByName(String nane);
}
