package com.example.ex.repository

import com.example.ex.model.EmployeeMonthly
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeMonthlyRepository : JpaRepository<EmployeeMonthly, Long>, EmployeeMonthlyRepositoryCustom {
    fun findEmployeeMonthliesByMetaInfo(visa: String): Iterable<EmployeeMonthly>
}