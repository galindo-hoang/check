package com.example.ex.mapper

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMonthly
import org.springframework.beans.factory.annotation.Autowired


abstract class EmployeeMonthlyMapperDecorator: EmployeeMonthlyMapper{

    @Autowired
    lateinit var delegate: EmployeeMonthlyMapper

     fun entityToDtoDecorator(employeeMonthly: EmployeeMonthly): EmployeeMonthlyDto {
        val result = delegate.entityToDto(employeeMonthly)
        result.visa = employeeMonthly.metaInfo.visa
        return result
    }

}