package com.example.bbangeobung.controller.v2;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.StoreDto;
import com.example.bbangeobung.dto.V2StoreDto;
import com.example.bbangeobung.entity.StoreItemNameEnum;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "V2/Store")
@RestController
@RequestMapping("/api/v2/store")
@RequiredArgsConstructor
public class StoreV2Controller {
    private final StoreService storeService;

    @GetMapping("/")
    @Operation(summary = "상점 목록 조회", description = "상점 목록 조회시 아이템이름으로 조회\n" +
            "존재하는 붕어빵 집을 조회하며 itemName이 null이나 빈 입력일경우에는 전체조회를 한다\n" +
            "정렬순서는 기본적으로 storeId 이고\n" +
            "itemName이 null이나 빈 입력일경우에는\n" +
            "키워드에 걸리는 아이템의 가격순으로 정렬한다")
    @SecurityRequirements()
    public ResponseDto<List<V2StoreDto.StoreRes>> getStores(
            @RequestParam(required = false) StoreItemNameEnum itemName
    ) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeService.getStoreByItemName(itemName == null ? null : itemName.getName()));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "상점 조회", description = "상점 조회")
    @SecurityRequirements()
    public ResponseDto<V2StoreDto.StoreRes> getStore(@PathVariable Long storeId) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeService.getV2Store(storeId));
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "상점 등록", description = "상점 등록 token필요")
    public ResponseDto<V2StoreDto.StoreRes> addStore(
            @ModelAttribute V2StoreDto.StoreAdd dto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseDto.of(HttpStatus.OK, "등록 성공", storeService.addV2Store(dto, userDetails.getUser()));
    }
}