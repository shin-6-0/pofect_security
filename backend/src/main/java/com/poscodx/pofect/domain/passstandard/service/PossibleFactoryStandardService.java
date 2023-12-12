package com.poscodx.pofect.domain.passstandard.service;

import com.poscodx.pofect.domain.essentialstandard.dto.EssentialStandardBtiPosReqDto;
import com.poscodx.pofect.domain.passstandard.dto.PossibleFactoryStandardResDto;
import com.poscodx.pofect.domain.passstandard.dto.PossibleToConfirmResDto;
import com.poscodx.pofect.domain.passstandard.entity.PossibleFactoryStandard;

import java.util.List;

public interface PossibleFactoryStandardService {
//    List<PossibleFactoryStandardResDto> getList();

    List<PossibleFactoryStandardResDto> getGridData();

    List<PossibleToConfirmResDto> possibleToConfirm(List<EssentialStandardBtiPosReqDto> essentialStandardBtiPosReqDtoList);
}
