package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreLikeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "StoreLike")
@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreLikeController {

    private final StoreLikeService storeLikeService;

    @PostMapping ("/like/{storeId}")
    @Operation(summary = "상점 좋아요 등록/취소", description = "상점 좋아요 등록/취소 좋아요 등록은 true, 취소는 false")
    public ResponseDto<Boolean> likeStore(@PathVariable Long storeId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Boolean isLike = storeLikeService.likeStore(storeId, userDetails.getUser());

        return ResponseDto.of(HttpStatus.OK, isLike? "좋아요 성공" : "좋아요 취소",isLike);
    }
}
