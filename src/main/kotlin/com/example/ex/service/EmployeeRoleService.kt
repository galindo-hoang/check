package com.example.ex.service

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.model.EmployeeRole

interface EmployeeRoleService {
    fun loadAllEmployee(): MutableIterable<EmployeeRole>
    fun loadEmployeeBySupervisor(visa: String): Iterable<EmployeeRole>
    fun loadEmployeeByAbbreviation(visa: String): Iterable<EmployeeRole>
    fun saveEmployee(employeeMonthly: EmployeeRole)
    fun saveListEmployee(list: List<EmployeeRole>)
    fun readAllFromXLSX(): List<EmployeeRoleDto>
}