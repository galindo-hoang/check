package com.example.ex.service.impl

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.HourReportCriteriaDto
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
    override fun loadAllEmployee(): MutableIterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findAll()
    }

    override fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findEmployeeMonthliesByMetaInfo(visa)
    }

    override fun saveEmployee(employeeMonthly: EmployeeMonthly) {
        employeeMonthlyRepository.save(employeeMonthly)
    }

    override fun loadEmployeeByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto): MutableList<EmployeeMetaInfo> {
        val result = mutableListOf<EmployeeMetaInfo>()
        val data = mutableListOf<Pair<String,Double>>()
        hourReportCriteria.levels.forEach {
            val splits = it.split(".")
            data.add(Pair("Level "+splits[0],("0."+splits[1]).toDouble()))
        }
        val jpaQuery = JPAQueryFactory(entityManager)
        data.forEach { (k, v) ->
            val a = jpaQuery
                .from(employeeRole)
                .join(employeeMonthly)
                .on(employeeRole.abbreviation.visa.eq(employeeMonthly.metaInfo.visa))
                .join(employeeMetaInfo)
                .on(employeeRole.abbreviation.visa.eq(employeeMetaInfo.visa))
                .groupBy(employeeRole.abbreviation.visa,employeeRole.level,employeeRole.subLevel,employeeMonthly.date)
                .having(employeeRole.level.eq(k).and(employeeRole.subLevel.eq(v).and(employeeMonthly.date.goe(hourReportCriteria.startMonth).and(employeeMonthly.date.loe(hourReportCriteria.endMonth)))))
                .select(employeeMetaInfo,employeeMonthly.hours.sum())
                .fetch()
            a.map {
                println(it.toArray()[0])
                println(it.toArray()[1])
            }
        }
        return  result
    }
}