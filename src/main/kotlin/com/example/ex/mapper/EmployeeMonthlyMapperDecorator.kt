package com.example.ex.mapper

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.text.SimpleDateFormat


abstract class EmployeeMonthlyMapperDecorator: EmployeeMonthlyMapper{

    @Autowired
    lateinit var delegate: EmployeeMonthlyMapper

    override fun dtoToEntity(employeeMonthlyDto: EmployeeMonthlyDto, metaInfo: EmployeeMetaInfo): EmployeeMonthly {
        val result = delegate.dtoToEntity(employeeMonthlyDto, metaInfo)
        result.date = Date.valueOf(employeeMonthlyDto.dateString)
        return result
    }

    override fun entityToDto(employeeMonthly: EmployeeMonthly): EmployeeMonthlyDto {
        val result = delegate.entityToDto(employeeMonthly)
        result.visa = employeeMonthly.metaInfo.visa
        return result
    }

}