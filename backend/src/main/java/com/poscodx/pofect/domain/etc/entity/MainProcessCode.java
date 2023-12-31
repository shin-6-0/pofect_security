package com.poscodx.pofect.domain.etc.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
// Main 공정 코드 관리 (FC080)
public class MainProcessCode extends BaseEntity {

    @Column(name = "GCS_COMP_CODE", length = 2)
    @NotNull
    private String gcsCompCode;  // 1.연결결산법인구분

    @Column(name = "MILL_CD", length = 1)
    @NotNull
    private String millCd;  // 2.공정계획박판Mill구분

    @Column(name = "PROCESS_CD", length = 2)
    @NotNull
    private String processCd;  // 3.박판공정계획공정구분

    @Column(name = "CD_EXPL", length = 680)
    private String cdExpl;  // 4.코드설명

    @Column(name = "USER_ID", length = 8)
    private String userId;  // 5.박판공정계획사용자ID

}
