package com.example.ex.repository

import com.example.ex.dto.EmployeeMonthlyDto

interface EmployeeCapacityRepositoryCustom {
    fun findVisaByMonth(month: Int): List<String>
    fun findMonthlyMeetCriteria(list: List<EmployeeMonthlyDto>): List<EmployeeMonthlyDto>
}