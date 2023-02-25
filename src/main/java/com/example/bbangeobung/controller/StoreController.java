package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.StoreDto;
import com.example.bbangeobung.entity.Store;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "Store")
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;

    @GetMapping("/")
    @SecurityRequirements()
    public ResponseDto<List<StoreDto.StoreRes>> getStores(
    ) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeService.getStores());
    }
    @GetMapping("/{storeId}")
    @SecurityRequirements()
    public ResponseDto<StoreDto.StoreRes> getStore(@PathVariable Long storeId) {
        return ResponseDto.of(HttpStatus.OK, "조회 성공", storeService.getStore(storeId));
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseDto<StoreDto.StoreRes> addStore(
            @ModelAttribute StoreDto.StoreAdd dto,
            @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails
    ) {
        return ResponseDto.of(HttpStatus.OK, "등록 성공", storeService.addStore(dto, userDetails.getUser()));
    }
}