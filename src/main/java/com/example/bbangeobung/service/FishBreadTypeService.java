package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.FishBreadTypeDto;
import com.example.bbangeobung.entity.FishBreadType;
import com.example.bbangeobung.repository.FishBreadTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FishBreadTypeService {

    private final FishBreadTypeRepository fishBreadTypeRepository;

    @Transactional(readOnly = true)
    public FishBreadTypeDto.FishBreadTypeRes getFishBreadType(Long id) {
        FishBreadType fishBreadType = fishBreadTypeRepository.findById(id).orElseThrow(
                () -> new CustomClientException("없는 붕어빵 타입입니다.")
        );
        return FishBreadTypeDto.FishBreadTypeRes.builder()
                .id(fishBreadType.getId())
                .name(fishBreadType.getName())
                .build();
    }
    @Transactional(readOnly = true)

    public List<FishBreadTypeDto.FishBreadTypeRes> getFishBreadTypes() {
        List<FishBreadType> fishBreadTypes = fishBreadTypeRepository.findAll();

        return fishBreadTypes.stream().map(fishBreadType ->
                FishBreadTypeDto.FishBreadTypeRes.builder()
                        .id(fishBreadType.getId())
                        .name(fishBreadType.getName())
                        .build()).toList();
    }


    @Transactional
    public FishBreadTypeDto.FishBreadTypeRes addFishBreadType(FishBreadTypeDto.FishBreadTypeAdd dto) {

        if (fishBreadTypeRepository.findByName(dto.getName()).isPresent()) {
            throw new CustomClientException("이미 등록된 붕어빵 종류입니다.");
        }

        FishBreadType fishBreadType = FishBreadType.builder().name(dto.getName()).build();

        fishBreadType = fishBreadTypeRepository.save(fishBreadType);

        return FishBreadTypeDto.FishBreadTypeRes.builder()
                .id(fishBreadType.getId())
                .name(fishBreadType.getName())
                .build();
    }

    @Transactional
    public void removeFishBreadType(Long id) {
        FishBreadType fishBreadType = fishBreadTypeRepository.findById(id).orElseThrow(
                () -> new CustomClientException("없는 붕어빵 타입입니다.")
        );

        fishBreadTypeRepository.delete(fishBreadType);
    }

    @Transactional
    public FishBreadTypeDto.FishBreadTypeRes updateFishBreadType(Long id, FishBreadTypeDto.FishBreadTypeUpdate dto) {
        FishBreadType fishBreadType = fishBreadTypeRepository.findById(id).orElseThrow(
                () -> new CustomClientException("없는 붕어빵 타입입니다.")
        );

        fishBreadType.update(dto.getName());

        fishBreadType = fishBreadTypeRepository.save(fishBreadType);

        return FishBreadTypeDto.FishBreadTypeRes.builder()
                .id(fishBreadType.getId())
                .name(fishBreadType.getName())
                .build();
    }
}
