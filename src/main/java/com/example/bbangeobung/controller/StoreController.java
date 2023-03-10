package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.StoreDto;
import com.example.bbangeobung.dto.V2StoreDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Store")
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/")
    @Operation(summary = "상점 목록 조회", description = "상점 목록 조회시 fishBreadType id로 조회\n" +
            "존재하는 붕어빵 집을 조회하며 id가 0일경우에는 전체조회를 한다\n" +
            "정렬순서는 기본적으로 storeId 이고\n" +
            "fishBreadTypeId가 0일경우에는\n" +
            "fishBreadTypeId에 해당하는 가격순으로 정렬한다")
    @SecurityRequirements()
    public ResponseDto<List<StoreDto.StoreRes>> getStores(
            @RequestParam Long fIshBredTypeId
    ) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeService.getStores(fIshBredTypeId));
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "상점 조회", description = "상점 조회")
    @SecurityRequirements()
    public ResponseDto<StoreDto.StoreRes> getStore(@PathVariable Long storeId) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeService.getStore(storeId));
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "상점 등록", description = "상점 등록 token필요")
    public ResponseDto<StoreDto.StoreRes> addStore(
            @ModelAttribute StoreDto.StoreAdd dto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseDto.of(HttpStatus.OK, "등록 성공", storeService.addStore(dto, userDetails.getUser()));
    }

    @DeleteMapping(value = "/{storeId}")
    @Operation(summary = "상점 삭제", description = "상점 삭제 관리자나 등록한 사람만 삭제 가능")
    public ResponseDto deleteStore(
            @PathVariable Long storeId,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
            ) {

        storeService.deleteStore(storeId, userDetails.getUser());

        return ResponseDto.of(HttpStatus.OK, "삭제성공");
    }
}