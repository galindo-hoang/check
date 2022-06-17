package com.example.ex.repository

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMonthly
import java.sql.Date

interface EmployeeMonthlyRepositoryCustom {
    fun findEmployeeByMonthFromXLSX(month: Int): List<EmployeeMonthlyDto>
    fun deleteEmployeeByMonth(month: List<Date>)
    fun findByProjectGroup(projectGroup: String?): List<EmployeeMonthly>
}