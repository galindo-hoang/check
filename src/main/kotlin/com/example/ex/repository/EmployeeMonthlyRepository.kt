package com.example.ex.repository

import com.example.ex.model.EmployeeMonthly
import org.springframework.data.repository.CrudRepository

interface EmployeeMonthlyRepository : CrudRepository<EmployeeMonthly, Long>, EmployeeMonthlyRepositoryCustom {
    fun findEmployeeMonthliesByMetaInfo(visa: String): Iterable<EmployeeMonthly>
}