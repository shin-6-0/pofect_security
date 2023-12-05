package com.poscodx.pofect.domain.processstandard.repository;

import com.poscodx.pofect.domain.processstandard.entity.ProcessStandard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStandardRepository extends JpaRepository<ProcessStandard, Long> {

}
