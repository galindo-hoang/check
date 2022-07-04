package com.example.ex.repository

import com.example.ex.model.EmployeeMetaInfo
import org.springframework.data.jpa.repository.JpaRepository


interface EmployeeMetaInfoRepository : JpaRepository<EmployeeMetaInfo, String>, EmployeeMetaInfoRepositoryCustom {
    fun findEmployMetaInfoByVisa(visa: String): EmployeeMetaInfo
}