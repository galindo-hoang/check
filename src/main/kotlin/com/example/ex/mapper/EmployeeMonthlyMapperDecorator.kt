package com.example.ex.mapper

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import org.springframework.beans.factory.annotation.Autowired
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


abstract class EmployeeMonthlyMapperDecorator: EmployeeMonthlyMapper{

    @Autowired
    lateinit var delegate: EmployeeMonthlyMapper
    @PersistenceContext
    lateinit var entityManager:EntityManager

    override fun dtoToEntity(employeeMonthlyDto: EmployeeMonthlyDto): EmployeeMonthly {
        val employeeMonthly = delegate.dtoToEntity(employeeMonthlyDto)
        employeeMonthly.metaInfo = entityManager.getReference(EmployeeMetaInfo::class.java,employeeMonthlyDto.visa)
        employeeMonthly.date = Date.valueOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(employeeMonthlyDto.dateJava))
        entityManager.persist(employeeMonthly)
        return employeeMonthly
    }

    override fun entityToDto(employeeMonthly: EmployeeMonthly): EmployeeMonthlyDto {
        val employeeMonthlyDto = delegate.entityToDto(employeeMonthly)
        employeeMonthlyDto.visa = employeeMonthly.metaInfo?.visa!!
        return employeeMonthlyDto
    }

}