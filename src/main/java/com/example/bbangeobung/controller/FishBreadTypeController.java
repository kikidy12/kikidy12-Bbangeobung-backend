package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.FishBreadTypeDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.FishBreadTypeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FishBreadType")
@RestController
@RequestMapping("/api/fishBreadType")
@RequiredArgsConstructor
public class FishBreadTypeController {

    private final FishBreadTypeService fishBreadTypeService;

    @GetMapping("/")
    @Operation(summary = "붕어빵 타입 목록 조회", description = "붕어빵 타입 목록 조회")
    public ResponseDto<List<FishBreadTypeDto.FishBreadTypeRes>> getFishBreadType() {

        return ResponseDto.of(HttpStatus.OK, "조회 성공", fishBreadTypeService.getFishBreadTypes());
    }

    @GetMapping("/{fishBreadTypeId}")
    @Operation(summary = "붕어빵 타입 단일 조회", description = "붕어빵 타입 단일 조회")
    public ResponseDto<FishBreadTypeDto.FishBreadTypeRes> getFishBreadType(@PathVariable Long fishBreadTypeId) {

        return ResponseDto.of(HttpStatus.OK, "조회 성공",
                fishBreadTypeService.getFishBreadType(fishBreadTypeId));
    }

    @Secured(value = "ROLE_ADMIN")
    @PostMapping("/")
    @Operation(summary = "붕어빵 타입 등록", description = "붕어빵 타입 등록 관리자만 가능")
    public ResponseDto<FishBreadTypeDto.FishBreadTypeRes> addFishBreadType(
            @RequestBody FishBreadTypeDto.FishBreadTypeAdd dto
    ) {

        return ResponseDto.of(HttpStatus.OK, "등록 성공", fishBreadTypeService.addFishBreadType(dto));
    }

    @Secured(value = "ROLE_ADMIN")
    @DeleteMapping("/{fishBreadTypeId}")
    @Operation(summary = "붕어빵 타입 수정", description = "붕어빵 타입 수정 관리자만 가능")
    public ResponseDto deleteFishBreadType(
            @PathVariable Long fishBreadTypeId
    ) {
        fishBreadTypeService.removeFishBreadType(fishBreadTypeId);

        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }
    @Secured(value = "ROLE_ADMIN")
    @PutMapping("/{fishBreadTypeId}")
    @Operation(summary = "붕어빵 타입 수정", description = "붕어빵 타입 수정 관리자만 가능")
    public ResponseDto<FishBreadTypeDto.FishBreadTypeRes> updateFishBreadType(
            @PathVariable Long fishBreadTypeId,
            @RequestBody FishBreadTypeDto.FishBreadTypeUpdate dto
            ) {

        System.out.println(dto.getName());

        return ResponseDto.of(HttpStatus.OK, "수정 성공",
                fishBreadTypeService.updateFishBreadType(fishBreadTypeId, dto));
    }
}
