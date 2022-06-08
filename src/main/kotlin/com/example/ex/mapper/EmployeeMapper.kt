package com.example.ex.mapper

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.Capacity
import com.example.ex.model.EmployeeMetaInfo
import org.mapstruct.BeanMapping
import org.mapstruct.Context
import org.mapstruct.DecoratedWith
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.NullValuePropertyMappingStrategy

@Mapper(componentModel = "spring")
interface EmployeeMapper {
    @Mapping(target = "isMgr", source = "employeeMetaInfo.ismgr", defaultValue = "")
    @Mapping(target = "group", source = "employeeMetaInfo.groupsa", defaultValue = "")
    fun entityReportHourToDto(employeeMetaInfo: EmployeeMetaInfo,consolidatedHours: Double): EmployeeHourReportDto
    fun entityToDto(employeeMetaInfo: EmployeeMetaInfo): EmployeeMetaInfoDto
}