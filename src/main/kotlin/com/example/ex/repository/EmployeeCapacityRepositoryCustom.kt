package com.example.ex.repository

import java.util.*

interface EmployeeCapacityRepositoryCustom {
    fun findVisaByMonth(month: Int): List<String>
    fun findVisaByListDate(dates: List<Date>): List<String>
}