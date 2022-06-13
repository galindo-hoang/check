package com.example.ex.repository

import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.EmployeeMetaInfo

interface EmployeeRoleRepositoryCustom {
    fun findEmployeesByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto):Map<EmployeeMetaInfo,Double>

}