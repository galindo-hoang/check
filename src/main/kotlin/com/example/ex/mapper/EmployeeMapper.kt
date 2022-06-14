package com.example.ex.mapper

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface EmployeeMapper {
    @Mapping(target = "isMgr", source = "employeeMetaInfo.ismgr", defaultValue = "")
    @Mapping(target = "group", source = "employeeMetaInfo.groupsa", defaultValue = "")
    fun entityReportHourToDto(employeeMetaInfo: EmployeeMetaInfo,consolidatedHours: Double): EmployeeHourReportDto
    fun entityToDto(employeeMetaInfo: EmployeeMetaInfo): EmployeeMetaInfoDto
}