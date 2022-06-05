package com.example.ex.service.impl

import com.example.ex.model.EmployRole
import com.example.ex.repository.EmployRoleRepository
import com.example.ex.service.EmployeeRoleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeRoleServiceImpl(@Autowired private val employeeRoleRepository: EmployRoleRepository):
    EmployeeRoleService {
    override fun loadAllEmployee(): MutableIterable<EmployRole> {
        val result = employeeRoleRepository.findAll()
        return result
    }

    override fun loadEmployeeBySupervisor(visa: String): Iterable<EmployRole> {
        val result = employeeRoleRepository.findEmployRolesBySupervisor(visa)
        return result
    }

    override fun loadEmployeeByAbbreviation(visa: String): Iterable<EmployRole> {
        val result = employeeRoleRepository.findEmployRolesByAbbreviation(visa)
        return result
    }

    override fun saveEmployee(employRole: EmployRole) {
        employeeRoleRepository.save(employRole)
    }
}