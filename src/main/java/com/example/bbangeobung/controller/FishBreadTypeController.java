package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.dto.FishBreadTypeDto;
import com.example.bbangeobung.service.FishBreadTypeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "FishBreadType")
@RestController
@RequestMapping("/api/fishBreadType")
@RequiredArgsConstructor
public class FishBreadTypeController {

    private final FishBreadTypeService fishBreadTypeService;

    @GetMapping("/")
    public ResponseDto<List<FishBreadTypeDto.FishBreadTypeRes>> getFishBreadType() {

        return ResponseDto.of(HttpStatus.OK, "조회 성공", fishBreadTypeService.getFishBreadTypes());
    }

    @GetMapping("/{fishBreadTypeId}")
    public ResponseDto<FishBreadTypeDto.FishBreadTypeRes> getFishBreadType(@PathVariable Long fishBreadTypeId) {

        return ResponseDto.of(HttpStatus.OK, "조회 성공",
                fishBreadTypeService.getFishBreadType(fishBreadTypeId));
    }


    @PostMapping("/")
    public ResponseDto<FishBreadTypeDto.FishBreadTypeRes> addFishBreadType(
            @RequestBody FishBreadTypeDto.FishBreadTypeAdd dto
    ) {

        return ResponseDto.of(HttpStatus.OK, "조회 성공", fishBreadTypeService.addFishBreadType(dto));
    }

    @DeleteMapping("/{fishBreadTypeId}")
    public ResponseDto deleteFishBreadType(
            @PathVariable Long fishBreadTypeId
    ) {
        fishBreadTypeService.removeFishBreadType(fishBreadTypeId);

        return ResponseDto.of(HttpStatus.OK, "삭제 성공");
    }

    @PutMapping("/{fishBreadTypeId}")
    public ResponseDto<FishBreadTypeDto.FishBreadTypeRes> updateFishBreadType(
            @PathVariable Long fishBreadTypeId,
            @RequestBody FishBreadTypeDto.FishBreadTypeUpdate dto
            ) {

        System.out.println(dto.getName());

        return ResponseDto.of(HttpStatus.OK, "수정 성공",
                fishBreadTypeService.updateFishBreadType(fishBreadTypeId, dto));
    }
}
