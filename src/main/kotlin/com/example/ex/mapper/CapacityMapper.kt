package com.example.ex.mapper

import com.example.ex.dto.CapacityDto
import com.example.ex.model.Capacity
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface CapacityMapper {

    @Mapping(target = "visaDto", source = "employeeCapacity.visa.visa", defaultValue = "")
    fun entityToDto(employeeCapacity: Capacity): CapacityDto

    fun dtoToEntity(employeeCapacityDto: CapacityDto): Capacity
}