package com.example.ex.mapper

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper

@Mapper
interface EmployeeMapper {

    fun entityReportHourToDto(employeeMetaInfo: EmployeeMetaInfo, consolidatedHours: Double): EmployeeHourReportDto

    fun entityToDto(employeeMetaInfo: EmployeeMetaInfo): EmployeeMetaInfoDto
}