package com.example.ex.service.impl

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.model.*
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.EmployeeMetaInfoRepository
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.repository.EmployeeRoleRepository
import com.example.ex.service.EmployeeMonthlyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class EmployeeMonthlyServiceImpl:
    EmployeeMonthlyService {

    @Autowired private lateinit var employeeMonthlyMapperDecorator: EmployeeMonthlyMapperDecorator
    @Autowired private lateinit var employeeMetaInfoRepository: EmployeeMetaInfoRepository
    @Autowired private lateinit var employeeRoleRepository: EmployeeRoleRepository
    @Autowired private lateinit var employeeMonthlyRepository: EmployeeMonthlyRepository
    @Autowired private lateinit var employeeCapacityRepository: EmployeeCapacityRepository
    override fun loadAllEmployee(): MutableIterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findAll()
    }

    override fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findEmployeeMonthliesByMetaInfo(visa)
    }

    override fun saveEmployee(employeeMonthly: EmployeeMonthly) {
        employeeMonthlyRepository.save(employeeMonthly)
    }

    override fun loadEmployeeByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto): Map<EmployeeMetaInfo,Double> {
        return employeeRoleRepository.findEmployeesByHourReportCriteria(hourReportCriteria)
    }

    override fun loadEmployeeByMonth(month: Int): List<EmployeeMonthlyDto> {
        var employeeMonthlyDtos = employeeMonthlyRepository.findEmployeeByMonth(month)
        println(employeeMonthlyDtos)
        val listDate = employeeMonthlyDtos.map { it.dateJava }
        val visaCapacity = employeeCapacityRepository.findVisaByListDate(listDate as List<Date>)

        return employeeMonthlyDtos.filter { visaCapacity.contains(it.visa) }
    }
}