package com.poscodx.pofect.domain.capacity.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CombinedCapacityRowSpanDto {

    @NotBlank
    private Long id;

    @NotBlank
    @Size(max = 2)
    private String gcsCompCode; // 1.연결결산법인구분

    @NotBlank
    @Size(max = 1)
    private String millCd; // 2.공정계획박판Mill구분

    @NotBlank
    @Size(max = 8)
    private String ordRcpTapWekCd; // 3.주문투입출강주코드

    @NotBlank
    @Size(max = 2)
    private String processCd; // 4.박판공정계획공정구분

    @NotBlank
    @Size(max = 1)
    private String firmPsFacTp;   // 5.확정통과공장구분

    private Long faAdjustmentWgt; // 6.공장결정조정능력

    private Long progressQty;  // 7.주문진행량

    @Size(max = 8)
    private String userId; // 8.박판공정계획사용자ID

    private Long planQty; // 5.계획량

    private RowSpanInfo rowSpan; // rowspan 정보

    public CombinedCapacityRowSpanDto updateRowSpan(int rowSpan) {
        this.rowSpan = new RowSpanInfo(rowSpan);
        return this;
    }

    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RowSpanInfo {
        private Integer processCd;
    }
}
