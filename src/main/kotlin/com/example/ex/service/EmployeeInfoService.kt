package com.example.ex.service

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo

interface EmployeeInfoService {
    fun loadAllEmployee(): List<EmployeeMetaInfoDto>
    fun loadEmployeeByVisa(visa: String): List<EmployeeMetaInfoDto>
    fun saveEmployee(employee: EmployeeMetaInfo)
}