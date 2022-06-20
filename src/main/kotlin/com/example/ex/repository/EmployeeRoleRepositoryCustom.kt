package com.example.ex.repository

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeRole

interface EmployeeRoleRepositoryCustom {
    fun findEmployeesByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto):Map<EmployeeMetaInfo,Double>
    fun findAllFromXLSX(): List<EmployeeRoleDto>

    fun saveAllCustom(list: List<EmployeeRoleDto>)
}