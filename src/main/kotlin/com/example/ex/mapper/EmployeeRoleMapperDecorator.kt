package com.example.ex.mapper

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.model.EmployeeRole
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional


abstract class EmployeeRoleMapperDecorator: EmployeeRoleMapper {
    @Autowired private lateinit var delegate: EmployeeRoleMapper

    @Transactional
    override fun dtoToEntity(employeeRoleDto: EmployeeRoleDto): EmployeeRole {
        return delegate.dtoToEntity(employeeRoleDto)
    }


}