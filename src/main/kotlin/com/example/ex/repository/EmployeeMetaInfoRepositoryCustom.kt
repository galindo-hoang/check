package com.example.ex.repository

import com.example.ex.model.EmployeeMetaInfo

interface EmployeeMetaInfoRepositoryCustom {
    fun findEmployeeByListVisa(listVisa: List<String>): List<EmployeeMetaInfo>
}