package com.example.ex.mapper

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.model.EmployeeMetaInfo
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


abstract class MetaInfoMapperDecorator: MetaInfoMapper{

    @Autowired
    lateinit var delegate: MetaInfoMapper

    override fun dtoToEntity(employeeMetaInfoDto: EmployeeMetaInfoDto): EmployeeMetaInfo {
        val employeeMonthly = delegate.dtoToEntity(employeeMetaInfoDto)
        employeeMonthly.vnEntry = Date.valueOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(employeeMetaInfoDto.vnEntry))
        employeeMonthly.entrance = Date.valueOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(employeeMetaInfoDto.entrance))
        return employeeMonthly
    }

}