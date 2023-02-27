package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.StoreDto;
import com.example.bbangeobung.entity.*;
import com.example.bbangeobung.repository.FishBreadTypeRepository;
import com.example.bbangeobung.repository.StoreInfoFishBreadTypeRepository;
import com.example.bbangeobung.repository.StoreRepository;
import com.example.bbangeobung.util.S3Uploader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final S3Uploader s3Uploader;
    private final StoreRepository storeRepository;
    private final FishBreadTypeRepository fishBreadTypeRepository;


    public StoreDto.StoreRes getStore(Long id) {

        Store store = storeRepository.findByIdJPQL(id).orElseThrow(
                () -> new CustomClientException("없는 store입니다.")
        );

        return StoreDto.StoreRes
                .builder()
                .id(store.getId())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .content(store.getContent())
                .imageURL(store.getImageURL())
                .itemList(store.makeItemMapDto())
                .build();
    }

    public List<StoreDto.StoreRes> getStores() {

        List<Store> stores = storeRepository.findAllJPQL();

        return stores.stream().map(store ->
                StoreDto.StoreRes
                        .builder()
                        .id(store.getId())
                        .latitude(store.getLatitude())
                        .longitude(store.getLongitude())
                        .content(store.getContent())
                        .imageURL(store.getImageURL())
                        .itemList(store.makeItemMapDto())
                        .build()
        ).toList();
    }

    @Transactional
    public StoreDto.StoreRes addStore(StoreDto.StoreAdd dto, User user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
            List<StoreDto.ItemAddDto> itemList = objectMapper.readValue(dto.getJsonList(), new TypeReference<>() {});
//            String imageUrl = s3Uploader.upload(dto.getImageFile());

            Set<Long> fishBreadTypeIdSet = itemList.stream().map(StoreDto.ItemAddDto::getFishBreadTypeId).collect(Collectors.toSet());

            List<FishBreadType> typeList = fishBreadTypeRepository.findByIdIn(fishBreadTypeIdSet);

            List<StoreInfoFishBreadType> infoList = new ArrayList<>();


            for (FishBreadType fishBreadType : typeList) {
                int price = itemList.stream()
                        .filter(v -> v.getFishBreadTypeId().equals(fishBreadType.getId()))
                        .map(StoreDto.ItemAddDto::getPrice).findFirst().orElse(0);
                StoreInfoFishBreadType info = StoreInfoFishBreadType.builder().price(price).fishBreadType(fishBreadType).build();
                infoList.add(info);
            }


            Store store = Store
                    .builder()
//                    .latitude(dto.getLatitude())
//                    .longitude(dto.getLongitude())
//                    .content(dto.getContent())
//                    .imageURL(imageUrl)
                    .user(user)
                    .infoFishBreadTypes(new HashSet<>())
                    .build();

            store = storeRepository.save(store);

            for (StoreInfoFishBreadType info : infoList) {
                info.setStore(store);
            }


            return StoreDto.StoreRes
                    .builder()
                    .id(store.getId())
                    .latitude(store.getLatitude())
                    .longitude(store.getLongitude())
                    .content(store.getContent())
                    .imageURL(store.getImageURL())
                    .itemList(store.makeItemMapDto())
                    .build();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void deleteStore(Long id, User user) {
        Store store = storeRepository.findByIdJPQL(id).orElseThrow(
                () -> new CustomClientException("없는 store입니다.")
        );

        if (!user.getRole().equals(UserRoleEnum.ADMIN) && !user.getId().equals(store.getUser().getId())) {
            throw new CustomClientException("삭제할 권한이 없습니다.");
        }

        storeRepository.delete(store);
    }
}
