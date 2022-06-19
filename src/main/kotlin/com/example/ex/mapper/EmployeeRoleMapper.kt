package com.example.ex.mapper

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeRole
import org.mapstruct.DecoratedWith
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
@DecoratedWith(EmployeeRoleMapperDecorator::class)
interface EmployeeRoleMapper {

    @Mapping(source = "employeeRole.supervisor.visa", target = "supervisorss")
    @Mapping(source = "employeeRole.abbreviation.visa", target = "abbreviationss")
    fun entityToDto(employeeRole: EmployeeRole): EmployeeRoleDto

    fun dtoToEntity(employeeRoleDto: EmployeeRoleDto): EmployeeRole
}