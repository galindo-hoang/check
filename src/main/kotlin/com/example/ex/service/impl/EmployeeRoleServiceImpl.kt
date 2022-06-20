package com.example.ex.service.impl

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.model.EmployeeRole
import com.example.ex.repository.EmployeeRoleRepository
import com.example.ex.service.EmployeeRoleService
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class EmployeeRoleServiceImpl(
    private val employeeRoleRepository: EmployeeRoleRepository,
): EmployeeRoleService {

    @PostConstruct
    fun postConstruct(){
        employeeRoleRepository.saveAllCustom(
            this.readAllFromXLSX()
        )
    }

    override fun loadAllEmployee(): MutableIterable<EmployeeRole> {
        return employeeRoleRepository.findAll()
    }

    override fun loadEmployeeBySupervisor(visa: String): Iterable<EmployeeRole> {
        return employeeRoleRepository.findEmployeeBySupervisor(visa)
    }

    override fun loadEmployeeByAbbreviation(visa: String): Iterable<EmployeeRole> {
        return employeeRoleRepository.findEmployeeByAbbreviation(visa)
    }

    override fun saveEmployee(employeeRole: EmployeeRole) {
        employeeRoleRepository.save(employeeRole)
    }

    override fun saveListEmployee(list: List<EmployeeRole>) {
        employeeRoleRepository.saveAll(list)
    }

    override fun readAllFromXLSX(): List<EmployeeRoleDto> {
        return employeeRoleRepository.findAllFromXLSX()
    }
}