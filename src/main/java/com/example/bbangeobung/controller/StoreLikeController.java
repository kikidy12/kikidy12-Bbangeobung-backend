package com.example.bbangeobung.controller;

import com.example.bbangeobung.common.dto.ResponseDto;
import com.example.bbangeobung.security.UserDetailsImpl;
import com.example.bbangeobung.service.StoreLikeService;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreLikeController {

    private final StoreLikeService storeLikeService;

    @PostMapping ("/like/{storeId}")
    public ResponseDto<Boolean> likeStore(@PathVariable Long storeId, @Parameter(hidden = true) @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Boolean isLike = storeLikeService.likeStore(storeId, userDetails.getUser());

        return ResponseDto.of(HttpStatus.OK, isLike? "좋아요 성공" : "좋아요 취소",isLike);
    }
}
