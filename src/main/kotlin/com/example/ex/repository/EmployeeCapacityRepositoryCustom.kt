package com.example.ex.repository

interface EmployeeCapacityRepositoryCustom {
    fun findVisaByMonth(month: Int): List<String>
}