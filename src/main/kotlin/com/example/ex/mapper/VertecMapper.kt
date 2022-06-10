package com.example.ex.mapper

import com.example.ex.dto.VertecDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.Vertec
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface VertecMapper {

    @Mapping(source = "vertec.visa.visa", target = "visaVertec")
    fun entityToDto(vertec: Vertec): VertecDto

    @Mapping(source = "employeeMetaInfo", target = "visa")
    fun dtoToEntity(vertecDto: VertecDto, employeeMetaInfo: EmployeeMetaInfo): Vertec
}