package com.example.ex.service

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo
import javax.annotation.PostConstruct

interface EmployeeMetaInfoService {

    fun loadAllEmployee(): List<EmployeeMetaInfoDto>
    fun loadEmployeeByVisa(visa: String): EmployeeMetaInfoDto
    fun saveEmployee(employee: EmployeeMetaInfo)
    fun saveListEmployee(employees: List<EmployeeMetaInfo>)
    fun readAllFromXLSX(): List<EmployeeMetaInfoDto>
}