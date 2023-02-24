package com.example.bbangeobung.controller;

import com.example.bbangeobung.service.StoreService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Store")
@RestController("/api/store")
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
}
