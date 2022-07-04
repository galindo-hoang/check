package com.example.ex.service

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.dto.WhoDoWhat
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly

interface EmployeeMonthlyVertecService {

    fun loadAllEmployee(): MutableIterable<EmployeeMonthly>
    fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly>
    fun saveEmployee(employeeMonthly: EmployeeMonthly)
    fun loadEmployeeByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto): Map<EmployeeMetaInfo, Double>

    fun saveEmployeeByMonth(month: Int, year: Int): List<EmployeeMonthlyDto>
    fun mappingProjectGroup(): List<EmployeeMonthly>
    fun fillWhoDoWhatByMonth(month: Int, year: Int): List<WhoDoWhat>
}