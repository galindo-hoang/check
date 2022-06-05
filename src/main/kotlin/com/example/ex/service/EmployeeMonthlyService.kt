package com.example.ex.service

import com.example.ex.model.EmployeeMonthly

interface EmployeeMonthlyService {

    fun loadAllEmployee(): MutableIterable<EmployeeMonthly>
    fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly>
    fun saveEmployee(employeeMonthly: EmployeeMonthly)
}