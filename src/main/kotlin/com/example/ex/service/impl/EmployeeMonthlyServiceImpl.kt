package com.example.ex.service.impl

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.mapper.EmployeeMetaInfoMapper
import com.example.ex.model.*
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.service.EmployeeMonthlyService
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class EmployeeMonthlyServiceImpl(@Autowired private val employeeMonthlyRepository: EmployeeMonthlyRepository):
    EmployeeMonthlyService {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private val employeeMonthly = QEmployeeMonthly.employeeMonthly
    private val employeeRole = QEmployeeRole.employeeRole
    private val employeeMetaInfo = QEmployeeMetaInfo.employeeMetaInfo
    @Autowired private lateinit var employeeMetaInfoMapper: EmployeeMetaInfoMapper
    override fun loadAllEmployee(): MutableIterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findAll()
    }

    override fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findEmployeeMonthliesByMetaInfo(visa)
    }

    override fun saveEmployee(employeeMonthly: EmployeeMonthly) {
        employeeMonthlyRepository.save(employeeMonthly)
    }

    override fun loadEmployeeByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto): MutableList<EmployeeHourReportDto> {
        val result = mutableListOf<EmployeeHourReportDto>()
        val data = mutableListOf<Pair<String,Double>>()
        hourReportCriteria.levels.forEach {
            val splits = it.split(".")
            data.add(Pair("Level "+splits[0],("0."+splits[1]).toDouble()))
        }
        val jpaQuery = JPAQueryFactory(entityManager)
        val subQuery = mutableListOf<String>()
        data.forEach { (k, v) ->
            jpaQuery.from(employeeRole).where(employeeRole.level.eq(k).and(employeeRole.subLevel.eq(v))).select(employeeRole.abbreviation.visa).fetch().forEach { subQuery.add(it) }
        }

        jpaQuery
            .from(employeeMonthly)
            .join(employeeMetaInfo)
            .on(employeeMonthly.metaInfo.visa.eq(employeeMetaInfo.visa))
            .where(employeeMonthly.date.goe(hourReportCriteria.startMonth).and(employeeMonthly.date.loe(hourReportCriteria.endMonth)))
            .where(employeeMetaInfo.visa.`in`(subQuery))
            .where(employeeMonthly.subProject.`in`(hourReportCriteria.projectCodes))
            .groupBy(employeeMetaInfo.visa)
            .select(employeeMetaInfo,employeeMonthly.hours.sum())
            .fetch().map {
                result.add(employeeMetaInfoMapper.entityReportHourToDto(it[employeeMetaInfo]!!,
                    it[employeeMonthly.hours.sum()]!!
                ))
            }

        return  result
    }
}