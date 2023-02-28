package com.example.bbangeobung.repository;

import com.example.bbangeobung.entity.Store;
import com.example.bbangeobung.entity.StoreLikeUsers;
import com.example.bbangeobung.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreLikeUsersRepository extends JpaRepository<StoreLikeUsers, Long> {

    Optional<StoreLikeUsers> findByUserAndStore(User user, Store store);
}
