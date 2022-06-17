package com.example.ex.repository.impl

import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.repository.EmployeeMetaInfoRepositoryCustom

class EmployeeMetaInforepositoryImpl: EmployeeMetaInfoRepositoryCustom {
    override fun findEmployeeByListVisa(listVisa: List<String>):List<EmployeeMetaInfo> {
        return listOf()
    }
}