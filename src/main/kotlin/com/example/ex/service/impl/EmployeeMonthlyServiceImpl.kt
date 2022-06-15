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
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class EmployeeMonthlyServiceImpl:
    EmployeeMonthlyService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
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

    override fun saveEmployeeByMonth(month: Int): List<EmployeeMonthlyDto> {
        var employeeMonthlyDtos = employeeMonthlyRepository.findEmployeeByMonth(month)
        val dtoList = employeeCapacityRepository.findMonthlyMeetCriteria(employeeMonthlyDtos)
        val modelList = dtoList.map {
            employeeMonthlyMapperDecorator.dtoToEntity()
        }
        entityManager.getReference(EmployeeMetaInfo::class.java,)
    }
}