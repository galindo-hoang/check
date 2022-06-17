package com.example.ex.repository

import com.example.ex.model.EmployeeMetaInfo
import org.springframework.data.repository.CrudRepository


interface EmployeeMetaInfoRepository : CrudRepository<EmployeeMetaInfo, String>, EmployeeMetaInfoRepositoryCustom {
    fun findEmployMetaInfoByVisa(visa: String): EmployeeMetaInfo
}