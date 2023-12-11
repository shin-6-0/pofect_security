package com.poscodx.pofect.domain.capacity.controller;

import com.poscodx.pofect.common.dto.ResponseDto;
import com.poscodx.pofect.common.exception.CustomException;
import com.poscodx.pofect.domain.capacity.dto.CapacityInfoDto;
import com.poscodx.pofect.domain.capacity.dto.CombinedCapacityDto;
import com.poscodx.pofect.domain.capacity.dto.CombinedCapacityRowSpanDto;
import com.poscodx.pofect.domain.capacity.dto.GrantCapacityDto;
import com.poscodx.pofect.domain.capacity.entity.CapacityInfo;
import com.poscodx.pofect.domain.capacity.service.CapacityService;
import com.poscodx.pofect.domain.main.dto.FactoryOrderInfoReqDto;
import com.poscodx.pofect.domain.main.dto.FactoryOrderInfoResDto;
import com.poscodx.pofect.domain.main.entity.FactoryOrderInfo;
import com.poscodx.pofect.domain.main.repository.FactoryOrderInfoRepository;
import com.poscodx.pofect.domain.main.service.FactoryOrderInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Api(value = "Capacity API", tags = {"투입능력관리"})
@CrossOrigin("*")
@RequestMapping("/capacity")
@RestController
@RequiredArgsConstructor
public class CapacityController {

    private final CapacityService capacityService;
    private final FactoryOrderInfoService factoryOrderInfoService;

    // 주문 별 능력 정보 (FW030)
    @GetMapping("")
    @ApiOperation(value = "능력 정보 조회", notes = "standard FW030")
    public ResponseEntity<ResponseDto> getList() {
        List<CapacityInfoDto> rs = capacityService.getList();
        return new ResponseEntity<>(new ResponseDto(rs), HttpStatus.OK);
    }

//    // JOIN
//    // 주문 별 능력 정보 (FW030)
//    // 주문별 능력 부여 관리 (FC100)
//    @GetMapping("/byWeek")
//    @ApiOperation(value = "출강주 별 능력 리스트 조회", notes = "전체 능력 데이터 조회")
//    public ResponseEntity<ResponseDto> getCapacityList() {
//        List<CombinedCapacityDto> rs = capacityService.getCapacityList();
//        return new ResponseEntity<>(new ResponseDto(rs), HttpStatus.OK);
//    }
// 출강주 별 능력 리스트 조회 (rowSpan 포함)
@GetMapping("/combined-capacity-rowspan")
@ApiOperation(value = "출강주 별 능력 리스트 조회 (rowSpan 포함)", notes = "출강주에 따른 능력 데이터 조회 (rowSpan 포함)")
public ResponseEntity<ResponseDto> getCombinedCapacityWithRowSpan(@RequestParam(required = false) String week) {
    try {
        List<CombinedCapacityRowSpanDto> rs = capacityService.getCombinedCapacityWithRowSpan(week);
        return new ResponseEntity<>(new ResponseDto(rs), HttpStatus.OK);
    } catch (CustomException e) {
        return new ResponseEntity<>(new ResponseDto("Failed to get combined capacity data with rowSpan: " + e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
    @GetMapping("/combined-capacity")
    @ApiOperation(value = "출강주 별 능력 리스트 조회", notes = "출강주에 따른 능력 데이터 조회")
    public ResponseEntity<ResponseDto> getCombinedCapacity(@RequestParam(required = false) String week) {
        List<CombinedCapacityDto> rs = capacityService.findCombinedCapacityByWeek(week);
        return new ResponseEntity<>(new ResponseDto(rs), HttpStatus.OK);
    }



    //출강주별 능력 insert
    @PostMapping("{week}")
    @ApiOperation(value = "투입능력 insert", notes = "투입능력 insert")
    public ResponseEntity<ResponseDto> createCapacity(@PathVariable String week) {
        try {
            capacityService.insert(week);
            return new ResponseEntity<>(new ResponseDto("Capacity data created successfully"), HttpStatus.OK);
        } catch (CustomException e) {

            return new ResponseEntity<>(new ResponseDto("Failed to create capacity data: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/factory/{id}")
    @ApiOperation(value = "해당 공정의 공장리스트, 능력 조회", notes = "해당 공정의 공장 리스트와 각각의 능력 정보를 조회한다.")
    public ResponseEntity<ResponseDto> getFactoryCapacity(@PathVariable(name = "id") String processCode) {
        List<CapacityInfoDto> result = capacityService.getFactoryCapacityList(processCode);
        return new ResponseEntity<>(new ResponseDto(result), HttpStatus.OK);
    }

}
