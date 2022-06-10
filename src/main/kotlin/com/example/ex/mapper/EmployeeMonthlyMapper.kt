package com.example.ex.mapper

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import org.mapstruct.DecoratedWith
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring")
@DecoratedWith(EmployeeMonthlyMapperDecorator::class)
interface EmployeeMonthlyMapper {

    fun entityToDto(employeeMonthly: EmployeeMonthly): EmployeeMonthlyDto

    @Mapping(source = "employeeMonthlyDto.division", target = "division")
    fun dtoToEntity(employeeMonthlyDto: EmployeeMonthlyDto, metaInfo: EmployeeMetaInfo): EmployeeMonthly
}
