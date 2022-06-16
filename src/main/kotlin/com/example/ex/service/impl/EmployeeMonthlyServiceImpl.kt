package com.example.ex.service.impl

import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.repository.EmployeeRoleRepository
import com.example.ex.repository.ProjectMappingRepositoryCustom
import com.example.ex.service.EmployeeMonthlyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class EmployeeMonthlyServiceImpl:
    EmployeeMonthlyService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    @Autowired private lateinit var employeeMonthlyMapperDecorator: EmployeeMonthlyMapperDecorator
    @Autowired private lateinit var projectMappingRepositoryCustom: ProjectMappingRepositoryCustom
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

    @Transactional
    override fun saveEmployeeByMonth(month: Int) {
        var employeeMonthlyDtos = employeeMonthlyRepository.findEmployeeByMonth(month)
        val dtoList = employeeCapacityRepository.findMonthlyMeetCriteria(employeeMonthlyDtos)
        employeeMonthlyRepository.deleteEmployeeByMonth(
            dtoList.map {
                Date.valueOf(
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.dateJava)
                )
            }
        )
        dtoList.forEach {
            val a = employeeMonthlyMapperDecorator.dtoToEntity(it)
            a.metaInfo = entityManager.getReference(EmployeeMetaInfo::class.java,it.visa)
            entityManager.persist(a)
        }
    }

    @Transactional
    override fun mappingProjectGroup(): List<EmployeeMonthly> {
        val list = projectMappingRepositoryCustom.fetchAll()
        val transformMapping = list.associate { it.projectCode to it.projectGroup }
        val monthlyVertec = employeeMonthlyRepository.findByProjectGroup(null)
        return monthlyVertec.filter {
            if(transformMapping.containsKey(it.project)){
                it.projectGroup = transformMapping[it.project]
                entityManager.persist(it)
            }
            !transformMapping.containsKey(it.project)
        }
    }
}