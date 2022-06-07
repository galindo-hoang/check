package com.example.ex.service

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly

interface EmployeeMonthlyService {

    fun loadAllEmployee(): MutableIterable<EmployeeMonthly>
    fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly>
    fun saveEmployee(employeeMonthly: EmployeeMonthly)
    fun loadEmployeeByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto): Map<EmployeeMetaInfo, Double>
}