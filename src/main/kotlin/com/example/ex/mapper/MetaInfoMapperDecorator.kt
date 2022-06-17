package com.example.ex.mapper

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


abstract class MetaInfoMapperDecorator: MetaInfoMapper{

    @Autowired
    lateinit var delegate: MetaInfoMapper

    override fun dtoToEntity(employeeMetaInfoDto: EmployeeMetaInfoDto): EmployeeMetaInfo {
        val result = delegate.dtoToEntity(employeeMetaInfoDto)
        result.vnEntry = Date.valueOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(employeeMetaInfoDto.vnEntry))
        result.entrance = Date.valueOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(employeeMetaInfoDto.entrance))
        return result
    }

}