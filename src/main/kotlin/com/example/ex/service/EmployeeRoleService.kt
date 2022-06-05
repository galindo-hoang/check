package com.example.ex.service

import com.example.ex.model.EmployRole

interface EmployeeRoleService {
    fun loadAllEmployee(): MutableIterable<EmployRole>
    fun loadEmployeeBySupervisor(visa: String): Iterable<EmployRole>
    fun loadEmployeeByAbbreviation(visa: String): Iterable<EmployRole>
    fun saveEmployee(employeeMonthly: EmployRole)
}