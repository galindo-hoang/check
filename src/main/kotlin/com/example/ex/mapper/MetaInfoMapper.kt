package com.example.ex.mapper

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo
import org.mapstruct.DecoratedWith
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
@DecoratedWith(MetaInfoMapperDecorator::class)
interface MetaInfoMapper {

    fun entityToDto(employeeMetaInfo: EmployeeMetaInfo): EmployeeMetaInfoDto

    fun dtoToEntity(employeeMetaInfoDto: EmployeeMetaInfoDto): EmployeeMetaInfo
}