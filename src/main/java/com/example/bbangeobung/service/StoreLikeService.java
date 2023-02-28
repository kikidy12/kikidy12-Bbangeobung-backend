package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.entity.Store;
import com.example.bbangeobung.entity.StoreLikeUsers;
import com.example.bbangeobung.entity.User;
import com.example.bbangeobung.repository.StoreLikeUsersRepository;
import com.example.bbangeobung.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreLikeService {

    private final StoreRepository storeRepository;
    private final StoreLikeUsersRepository storeLikeUsersRepository;

    @Transactional
    public Boolean likeStore(Long storeId, User user) {

        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new CustomClientException("가게가 없습니다.")
        );

        Optional<StoreLikeUsers> storeLikeUsers = storeLikeUsersRepository.findByUserAndStore(user, store);

        if(!storeLikeUsers.isPresent()) {
            storeLikeUsersRepository.save(new StoreLikeUsers(user, store));
            return true;
        }

        storeLikeUsersRepository.deleteById(storeLikeUsers.get().getId());
        return false;
    }
}
