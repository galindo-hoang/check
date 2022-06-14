package com.example.ex.repository

import com.example.ex.dto.EmployeeMonthlyDto
import java.util.Date

interface EmployeeMonthlyRepositoryCustom {
    fun findEmployeeByMonth(month: Int): List<EmployeeMonthlyDto>
}