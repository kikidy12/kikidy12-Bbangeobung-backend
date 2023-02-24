package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.entity.Store;
import com.example.bbangeobung.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;


    public Store getStore(Long id) {

        return storeRepository.findById(id).orElseThrow(
                () -> new CustomClientException("없는 store입니다.")
        );
    }

}
