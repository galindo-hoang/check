package com.example.ex.service.impl

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.mapper.EmployeeMetaInfoMapper
import com.example.ex.model.*
import com.example.ex.repository.EmployRoleRepositoryImpl
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.service.EmployeeMonthlyService
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.types.dsl.StringExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext
import javax.persistence.criteria.CriteriaBuilder

@Service
class EmployeeMonthlyServiceImpl(@Autowired private val employeeMonthlyRepository: EmployeeMonthlyRepository):
    EmployeeMonthlyService {

    @Autowired private lateinit var employeeMetaInfoMapper: EmployeeMetaInfoMapper
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