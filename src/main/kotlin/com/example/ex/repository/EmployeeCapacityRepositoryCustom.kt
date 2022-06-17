package com.example.ex.repository

import com.example.ex.dto.EmployeeMonthlyDto

interface EmployeeCapacityRepositoryCustom {
    fun findVisaByMonth(month: Int): List<String>
    fun findMonthlyMeetCriteriaFromXLSX(list: List<EmployeeMonthlyDto>): List<EmployeeMonthlyDto>
}