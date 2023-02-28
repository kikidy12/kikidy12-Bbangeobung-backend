package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.StoreDto;
import com.example.bbangeobung.dto.StoreItemDto;
import com.example.bbangeobung.dto.V2StoreDto;
import com.example.bbangeobung.entity.*;
import com.example.bbangeobung.repository.FishBreadTypeRepository;
import com.example.bbangeobung.repository.StoreRepository;
import com.example.bbangeobung.util.S3Uploader;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        int likeUserCount = ObjectUtils.defaultIfNull(store.getStoreLikeUsers(), new ArrayList<StoreLikeUsers>()).size();

        return StoreDto.StoreRes
                .builder()
                .id(store.getId())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .content(store.getContent())
                .imageURL(store.getImageURL())
                .itemList(store.makeItemMapDto())
                .likeCount(likeUserCount)
                .build();
    }


    public List<V2StoreDto.V2StoreRes> getStoreByMe(User user) {

        List<Store> stores = storeRepository.findByUserIdJPQL(user.getId());

        return getStoreRes(stores);
    }

    public V2StoreDto.V2StoreRes getV2Store(Long id) {

        Store store = storeRepository.findByIdJPQLV2(id).orElseThrow(
                () -> new CustomClientException("없는 store입니다.")
        );

        int likeUserCount = ObjectUtils.defaultIfNull(store.getStoreLikeUsers(), new ArrayList<StoreLikeUsers>()).size();

        return V2StoreDto.V2StoreRes
                .builder()
                .id(store.getId())
                .latitude(store.getLatitude())
                .longitude(store.getLongitude())
                .content(store.getContent())
                .imageURL(store.getImageURL())
                .itemList(store.makeStoreItemMapDto())
                .likeCount(likeUserCount)
                .build();
    }

    public List<StoreDto.StoreRes> getStores(Long sfId) {

        List<Store> stores = storeRepository.findAllJPQL(sfId);

        return stores.stream().map(store -> {
            int likeUserCount = ObjectUtils.defaultIfNull(store.getStoreLikeUsers(), new ArrayList<StoreLikeUsers>()).size();
            return StoreDto.StoreRes
                    .builder()
                    .id(store.getId())
                    .latitude(store.getLatitude())
                    .longitude(store.getLongitude())
                    .content(store.getContent())
                    .imageURL(store.getImageURL())
                    .itemList(store.makeItemMapDto())
                    .likeCount(likeUserCount)
                    .build();
        }).toList();
    }

    public List<V2StoreDto.V2StoreRes> getStoreByItemName(String itemName) {

        List<Store> stores = storeRepository.findAllByItemName(itemName);

        return getStoreRes(stores);
    }


    @Transactional
    public StoreDto.StoreRes addStore(StoreDto.StoreAdd dto, User user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
            List<StoreDto.ItemAddDto> itemList = objectMapper.readValue(dto.getJsonList(), new TypeReference<>() {});
            String imageUrl = s3Uploader.upload(dto.getImageFile());

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
                    .latitude(dto.getLatitude())
                    .longitude(dto.getLongitude())
                    .content(dto.getContent())
                    .imageURL(imageUrl)
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
                    .likeCount(0)
                    .build();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Transactional
    public V2StoreDto.V2StoreRes addV2Store(V2StoreDto.V2StoreAdd dto, User user) {
        try {
            ObjectMapper objectMapper = new ObjectMapper().registerModule(new SimpleModule());
            List<StoreItemDto.StoreItemAdd> addItemList = objectMapper.readValue(dto.getJsonList(), new TypeReference<>() {});

            System.out.println(addItemList);
            String imageUrl = s3Uploader.upload(dto.getImageFile());


            Store store = Store
                    .builder()
                    .latitude(dto.getLatitude())
                    .longitude(dto.getLongitude())
                    .content(dto.getContent())
                    .imageURL(imageUrl)
                    .user(user)
                    .storeItems(new HashSet<>())
                    .build();

            store = storeRepository.save(store);

            List<StoreItem> itemList = addItemList.stream().map(
                    v -> StoreItem.builder().price(v.getPrice()).name(v.getName()).build()
            ).toList();

            for (StoreItem storeItem : itemList) {
                storeItem.setStore(store);
            }


            return V2StoreDto.V2StoreRes
                    .builder()
                    .id(store.getId())
                    .latitude(store.getLatitude())
                    .longitude(store.getLongitude())
                    .content(store.getContent())
                    .imageURL(store.getImageURL())
                    .likeCount(0)
                    .itemList(store.makeStoreItemMapDto())
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



    // 가게목록 dto변환
    private List<V2StoreDto.V2StoreRes> getStoreRes(List<Store> stores) {
        return stores.stream().map(store -> {
            int likeUserCount = ObjectUtils.defaultIfNull(store.getStoreLikeUsers(), new ArrayList<StoreLikeUsers>()).size();
            return V2StoreDto.V2StoreRes
                    .builder()
                    .id(store.getId())
                    .latitude(store.getLatitude())
                    .longitude(store.getLongitude())
                    .content(store.getContent())
                    .imageURL(store.getImageURL())
                    .itemList(store.makeStoreItemMapDto())
                    .likeCount(likeUserCount)
                    .build();
        }).toList();
    }
}
