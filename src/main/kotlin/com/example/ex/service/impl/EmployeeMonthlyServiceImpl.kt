package com.example.ex.service.impl

import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.*
import com.example.ex.repository.impl.EmployRoleRepositoryImpl
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.service.EmployeeMonthlyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeMonthlyServiceImpl(@Autowired private val employeeMonthlyRepository: EmployeeMonthlyRepository):
    EmployeeMonthlyService {

    @Autowired private lateinit var employRoleRepositoryImpl: EmployRoleRepositoryImpl
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
        return employRoleRepositoryImpl.findEmployeesByHourReportCriteria(hourReportCriteria)
    }
}