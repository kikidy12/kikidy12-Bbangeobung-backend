package com.example.bbangeobung.service;

import com.example.bbangeobung.common.CustomClientException;
import com.example.bbangeobung.dto.StoreReportListResponseDto;
import com.example.bbangeobung.dto.StoreReportResponseDto;
import com.example.bbangeobung.entity.*;
import com.example.bbangeobung.repository.StoreReportRepository;
import com.example.bbangeobung.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreReportService {
    private final StoreReportRepository storeReportRepository;
    private final StoreRepository storeRepository;

    public StoreReportListResponseDto getStoreReports() {

        StoreReportListResponseDto responseDto = new StoreReportListResponseDto();

        List<StoreReport> reports = storeReportRepository.findAll();

        for(StoreReport report : reports) {
            responseDto.addReports(new StoreReportResponseDto(report));
        }

        return responseDto;
    }


    public StoreReportResponseDto createStoreReport(Long storeId,String reason, User user) {

        Store store = storeRepository.findById(storeId).orElseThrow(
                () -> new CustomClientException("리뷰가 존재하지 않습니다.")
        );

        StoreReport report = new StoreReport(reason, user, store);
        storeReportRepository.save(report);

        return new StoreReportResponseDto(report);

    }
}
