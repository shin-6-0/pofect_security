package com.poscodx.pofect.domain.capacity.service;

import com.poscodx.pofect.common.exception.CustomException;
import com.poscodx.pofect.common.exception.ErrorCode;
import com.poscodx.pofect.domain.capacity.dto.CapacityInfoDto;
import com.poscodx.pofect.domain.capacity.dto.CombinedCapacityDto;
import com.poscodx.pofect.domain.capacity.entity.CapacityInfo;
import com.poscodx.pofect.domain.capacity.dto.CombinedCapacityRowSpanDto;
import com.poscodx.pofect.domain.capacity.repository.CapacityRepository;
import com.poscodx.pofect.domain.passstandard.service.ConfirmFactoryStandardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CapacityServiceImpl implements CapacityService {

    private final CapacityRepository capacityRepository;
    private final ConfirmFactoryStandardService confirmFactoryStandardService;

    @Override
    public List<CapacityInfoDto> getList() {
        return capacityRepository.findAll().stream()
                .map(CapacityInfoDto::toDto)
                .collect(Collectors.toList());
    }

    // join data
    @Override
    public List<CombinedCapacityDto> getCapacityList() {
        return capacityRepository.findCombinedCapacity();
    }


    @Override
    public List<CombinedCapacityDto> findCombinedCapacityByWeek(String week) {
        return capacityRepository.findCombinedCapacityByWeek(week);
    }


    // rowspan 추가
//    @Override
//    public List<CombinedCapacityRowSpanDto> addRowSpanValues(List<CombinedCapacityDto> combinedCapacityList) {
//        List<CombinedCapacityRowSpanDto> resultList = new ArrayList<>();
//        Map<String, Integer> processCdCountMap = new HashMap<>();
//
//        for (CombinedCapacityDto dto : combinedCapacityList) {
//            CombinedCapacityRowSpanDto rowSpanDto = new CombinedCapacityRowSpanDto();
//            BeanUtils.copyProperties(dto, rowSpanDto);
//            int rowCount = processCdCountMap.getOrDefault(dto.getProcessCd(), 0);
//
//            if (rowCount == 0) {
//                rowSpanDto.updateRowSpan(dto.getProcessCd(), rowCount);
//            }
//
//            processCdCountMap.put(dto.getProcessCd(), rowCount + 1);
//            resultList.add(rowSpanDto);
//        }
//
//        return resultList;
//    }

    @Override
    // rowspan 추가
    public List<CombinedCapacityDto> calculateRowSpan(List<CombinedCapacityDto> capacityData) {
        // 각 processCd 별로 firmPsFacTp가 가장 작은 값을 저장하는 맵
        Map<String, Integer> processCdCountMap = new HashMap<>();
        Map<String, Integer> processCdMinFacMap = new HashMap<>();

        for (CombinedCapacityDto dto : capacityData) {
            String processCd = dto.getProcessCd();
            // processCd가 맨 처음 나온 case
            if (!processCdCountMap.containsKey(processCd)) {
                processCdCountMap.put(processCd, 1);
            } else {
                int count = processCdCountMap.get(processCd);
                processCdCountMap.put(processCd, count + 1);
            }
            int count = processCdCountMap.get(processCd);

            // firmPsFacTp가 가장 작은 값
            if (dto.getFirmPsFacTp() != null) {
                int currentFirmPsFacTp = Integer.parseInt(dto.getFirmPsFacTp());
                Integer minFirmPsFacTp = processCdMinFacMap.get(processCd);
                if (minFirmPsFacTp == null || currentFirmPsFacTp < minFirmPsFacTp) {
                    processCdMinFacMap.put(processCd, currentFirmPsFacTp);
                }
            }

        }
        for (CombinedCapacityDto rowSpanDto : capacityData) {
            String processCd = rowSpanDto.getProcessCd();
            if (processCdMinFacMap.get(processCd) == Integer.parseInt(rowSpanDto.getFirmPsFacTp())) {
                // RowSpanInfo 객체 생성
                CombinedCapacityDto.RowSpanInfo rowSpanInfo = new CombinedCapacityDto.RowSpanInfo(processCdCountMap.get(processCd));
                if (rowSpanDto.getRowSpan() == null) {
                    rowSpanDto.setRowSpan(rowSpanInfo);
                }
            }
        }
        return capacityData;
    }


    @Transactional
    @Override
    public void insert(String week) {
        // 데이터 삽입하기 전 중복 체크
        if (capacityRepository.existsByOrdRcpTapWekCd(week)) {
            // 이미 week 데이터가 존재하면 예외
            throw new CustomException(ErrorCode.BAD_REQUEST);
        }

        // 중복이 없으면 insert
        capacityRepository.insertIntoCapacityInfo(week);
    }

    @Override
    public List<CombinedCapacityRowSpanDto> getCombinedCapacityWithRowSpan(String week) {
        return null;
    }

    @Override
    public List<CapacityInfoDto.FactoryCapacityDto> getFactoryCapacityList(String processCode, String week) {
        List<CapacityInfoDto.FactoryCapacityDto> result = new ArrayList<>();

        List<CapacityInfo> list = null;

        if("0".equals(week)) list = capacityRepository.findAllByProcessCdOrderByFirmPsFacTpAsc(processCode);
        else list =  capacityRepository.findAllByProcessCdAndOrdRcpTapWekCdOrderByFirmPsFacTpAsc(processCode, week);

        for(CapacityInfo capacityInfo : list) {
            // 공장 이름 GET 후 매핑
            String factoryName = confirmFactoryStandardService.getFactoryName(capacityInfo.getProcessCd(), capacityInfo.getFirmPsFacTp());

            CapacityInfoDto.FactoryCapacityDto dto =
                    CapacityInfoDto.FactoryCapacityDto.builder()
                            .id(capacityInfo.getId())
                            .processCd(capacityInfo.getProcessCd())
                            .firmPsFacTp(capacityInfo.getFirmPsFacTp())
                            .faAdjustmentWgt(capacityInfo.getFaAdjustmentWgt())
                            .progressQty(capacityInfo.getProgressQty())
                            .factoryName(factoryName)
                            .build();

            result.add(dto);
        }

        return result;
    }

    @Transactional
    @Override
    public void updateQty(Long id, Long qty) {
        CapacityInfo capacityInfo = capacityRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POSTS_NOT_FOUND));

        capacityInfo.updateProgressQty(qty);
    }
}