package com.poscodx.pofect.domain.main.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.poscodx.pofect.domain.main.entity.FactoryOrderInfo;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FactoryOrderInfoResDto {

    /* 가통 설계 response dto */
    @Getter
    @Setter
    @AllArgsConstructor
    public static class possibleFactoryDto {
        private Integer success;  // 가통설계 성공한 주문 개수
        private Integer fail;  // 가통설계 실패한 주문 개수
    }

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 2)
    private String gcsCompCode;  // 1.연결결산법인구분

    @NotBlank
    @Size(max = 1)
    private String millCd;  // 2.공정계획박판Mill구분

    @NotBlank
    @Size(max = 13)
    private String orderHeadLineNo;  // 3.OrderHeadLineNumber

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime creationDate;  // 4.생성일자

    @Size(max = 1)
    private String osMainStatusCd;  // 5.주문진도상태구분

    @Size(max = 1)
    private String faConfirmFlag;  // 6.공장결정확정구분

    @Size(max = 16)
    private String posbPassFacCdN;  // 7.가능통과공정코드

    @Size(max = 8)
    private String cfirmPassOpCd;  // 8.확정통과공정코드

    @Size(max = 2)
    private String ordPdtItpCdN;  // 9.주문품종코드

    @Size(max = 4)
    private String ordPdtItdsCdN;  // 10.주문품명코드

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime adjustConsBktStartDttm;  // 11.주문ATP능력사용조정일

    @Size(max = 5)
    private String customerNumber;  // 12.고객사코드

    @Size(max = 360)
    private String customerName;  // 13.고객사명

    @Size(max = 8)
    private String ordThwTapWekCd;  // 14.주문투입출강주코드

    @Size(max = 2)
    private String orderType;  // 15.OrderType

    private Integer orderLineQty;  // 16.OrderLine 주문량

    private Double orderThick;  // 17.제품주문두께

    private Double orderWidth;  // 18.제품주문폭

    @Size(max = 5)
    private String orderLength;  // 19.주문길이

    @Size(max = 6)
    private String orderUsageCdN;  /// 20.주문용도지정코드

    @Size(max = 1)
    private String orderEdgeCode;  // 21.제품주문Edge구분

    @Size(max = 1)
    private String stockCode;  // 22.제품재고판매구분

    @Size(max = 150)
    private String salesPerson;  // 23.제품경매영업담당자명

    @Size(max = 3)
    private String salesCodeN;  // 24.판매특기구분

    @Size(max = 1)
    private String salCusManDblTp;  // 25.판매고객사대표산업구분

    @Size(max = 1)
    private String salCusLocLClsTp;  // 26.판매고객사지역대분류구분

    private Integer prodStdPackTolMin;  // 27.주문제품정포장하한중량

    private Integer prodStdPackTolMax;  // 28.주문제품정포장상한중량

    @Size(max = 30)
    private String specificationCdN;  // 29.제품규격약호

    @Size(max = 2)
    private String surfaceFinishCd;  // 30.제품표면마무리지정코드

    @Size(max = 3)
    private String postTreatmentMethodCdN;  // 31.주문제품후처리방법지정코드

    @Size(max = 2)
    private String oilingMethodCd;  // 32.제품도유방법지정코드

    @Size(max = 16)
    private String planningItemCodeN;  // 33.PlanningItem코드

    @Size(max = 14)
    private String smSteelGrdN;  // 34.출강목표번호

    @Size(max = 6)
    private String moltenSteelCharCdN;  // 35.품질설계용강특성코드

    @Size(max = 4)
    private String tsAim;  // 36.품질설계목표TS

    private Double unitWeight;  // 37.제품칫수계산단중

    @Size(max = 1)
    private String hrSpComposite;  // 38.품질설계열연SkinPass합성지정구분

    @Size(max = 1)
    private String surfaceGrd;  // 39.품질설계표면등급구분

    @Size(max = 1)
    private String shapeGrd;  // 40.품질설계형상등급구분

    @Size(max = 13)
    private String poscoProdGrdN;  // 41.제품사내보증번호

    private Double hrProdThkAim;  // 42.품질설계열연목표두께

    private Double hrProdWthAim;  // 43.품질설계열연목표폭

    private Double hrRollUnitWgtMax;  // 44.열연공장압연가능재료상한중량

    @Size(max = 3)
    private String sm2ndRfnCd;  // 45.품질설계제강2차정련코드

    @Size(max = 1)
    private String skinpassFlag;  // 46.제품SkinPass지정여부

    @Size(max = 5)
    private String packingType;  // 47.제품포장방법코드

    private Integer facAllocWgt;  // 48.소내공장결정중량

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime faAllocDate;  // 49.생산가능공장결정일자

    @Size(max = 200)
    private String errorMessage;  // 50.ErrorMessage내용

    @Size(max = 5)
    private String msgcode;  // 51.박판공정계획Message코드

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime lastUpdateDate;  // 52. 최종수정일자

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDateTime posbPassFacUpdateDate;  // 53. 가통설계일자

    @Size(max = 50)
    private String posbPassFacProcess;  // 54. 가통설계결과 - 경유공정

    @Size(max = 50)
    private String posbPassFacEs;  // 55. 가통설계결과 - 필수재

    @Size(max = 50)
    private String posbPassFacSize;  // 56. 가통설계결과 - 사이즈

    public static FactoryOrderInfoResDto toDto(FactoryOrderInfo factoryOrderInfo) {
        return FactoryOrderInfoResDto.builder()
                .id(factoryOrderInfo.getId())
                .gcsCompCode(factoryOrderInfo.getGcsCompCode())
                .millCd(factoryOrderInfo.getMillCd())
                .orderHeadLineNo(factoryOrderInfo.getOrderHeadLineNo())
                .creationDate(factoryOrderInfo.getCreationDate())
                .osMainStatusCd(factoryOrderInfo.getOsMainStatusCd())
                .faConfirmFlag(factoryOrderInfo.getFaConfirmFlag())
                .posbPassFacCdN(factoryOrderInfo.getPosbPassFacCdN())
                .cfirmPassOpCd(factoryOrderInfo.getCfirmPassOpCd())
                .ordPdtItpCdN(factoryOrderInfo.getOrdPdtItpCdN())
                .ordPdtItdsCdN(factoryOrderInfo.getOrdPdtItdsCdN())
                .adjustConsBktStartDttm(factoryOrderInfo.getAdjustConsBktStartDttm())
                .customerNumber(factoryOrderInfo.getCustomerNumber())
                .customerName(factoryOrderInfo.getCustomerName())
                .ordThwTapWekCd(factoryOrderInfo.getOrdThwTapWekCd())
                .orderType(factoryOrderInfo.getOrderType())
                .orderLineQty(factoryOrderInfo.getOrderLineQty())
                .orderThick(factoryOrderInfo.getOrderThick())
                .orderWidth(factoryOrderInfo.getOrderWidth())
                .orderLength(factoryOrderInfo.getOrderLength())
                .orderUsageCdN(factoryOrderInfo.getOrderUsageCdN())
                .orderEdgeCode(factoryOrderInfo.getOrderEdgeCode())
                .stockCode(factoryOrderInfo.getStockCode())
                .salesPerson(factoryOrderInfo.getSalesPerson())
                .salesCodeN(factoryOrderInfo.getSalesCodeN())
                .salCusManDblTp(factoryOrderInfo.getSalCusManDblTp())
                .salCusLocLClsTp(factoryOrderInfo.getSalCusLocLClsTp())
                .prodStdPackTolMin(factoryOrderInfo.getProdStdPackTolMin())
                .prodStdPackTolMax(factoryOrderInfo.getProdStdPackTolMax())
                .specificationCdN(factoryOrderInfo.getSpecificationCdN())
                .surfaceFinishCd(factoryOrderInfo.getSurfaceFinishCd())
                .postTreatmentMethodCdN(factoryOrderInfo.getPostTreatmentMethodCdN())
                .oilingMethodCd(factoryOrderInfo.getOilingMethodCd())
                .planningItemCodeN(factoryOrderInfo.getPlanningItemCodeN())
                .smSteelGrdN(factoryOrderInfo.getSmSteelGrdN())
                .moltenSteelCharCdN(factoryOrderInfo.getMoltenSteelCharCdN())
                .tsAim(factoryOrderInfo.getTsAim())
                .unitWeight(factoryOrderInfo.getUnitWeight())
                .hrSpComposite(factoryOrderInfo.getHrSpComposite())
                .surfaceGrd(factoryOrderInfo.getSurfaceGrd())
                .shapeGrd(factoryOrderInfo.getShapeGrd())
                .poscoProdGrdN(factoryOrderInfo.getPoscoProdGrdN())
                .hrProdThkAim(factoryOrderInfo.getHrProdThkAim())
                .hrProdWthAim(factoryOrderInfo.getHrProdWthAim())
                .hrRollUnitWgtMax(factoryOrderInfo.getHrRollUnitWgtMax())
                .sm2ndRfnCd(factoryOrderInfo.getSm2ndRfnCd())
                .skinpassFlag(factoryOrderInfo.getSkinpassFlag())
                .packingType(factoryOrderInfo.getPackingType())
                .facAllocWgt(factoryOrderInfo.getFacAllocWgt())
                .faAllocDate(factoryOrderInfo.getFaAllocDate())
                .errorMessage(factoryOrderInfo.getErrorMessage())
                .msgcode(factoryOrderInfo.getMsgcode())
                .lastUpdateDate(factoryOrderInfo.getLastUpdateDate())
                .posbPassFacUpdateDate(factoryOrderInfo.getPosbPassFacUpdateDate())
                .posbPassFacProcess(factoryOrderInfo.getPosbPassFacProcess())
                .posbPassFacEs(factoryOrderInfo.getPosbPassFacEs())
                .posbPassFacSize(factoryOrderInfo.getPosbPassFacSize())
                .build();
    }
}
