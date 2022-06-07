package com.example.ex.repository

import com.example.ex.Utils.getJpaQuery
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.QEmployeeMetaInfo.Companion.employeeMetaInfo
import com.example.ex.model.QEmployeeMonthly.Companion.employeeMonthly
import com.example.ex.model.QEmployeeRole.Companion.employeeRole
import org.springframework.stereotype.Repository
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class EmployRoleRepositoryImpl: EmployRoleRepositoryCustom {
    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun findEmployeesByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto):Map<EmployeeMetaInfo,Double> {
        val result = mutableMapOf<EmployeeMetaInfo,Double>()
        getJpaQuery(entityManager)
            .from(employeeMonthly)
            .join(employeeMetaInfo)
            .on(employeeMonthly.metaInfo.visa.eq(employeeMetaInfo.visa))
            .where(employeeMonthly.date.goe(hourReportCriteria.startMonth)
                .and(employeeMonthly.date.loe(hourReportCriteria.endMonth)
                    .and(employeeMonthly.subProject.`in`(hourReportCriteria.projectCodes))))
            .where(employeeMetaInfo.visa.`in`(
                getJpaQuery(entityManager)
                    .from(employeeRole)
                    .where(
                        employeeRole.level.append(employeeRole.subLevel.stringValue())
                            .`in`(hourReportCriteria.levels))
                    .select(employeeRole.abbreviation.visa)
                    .fetch()
                ))
            .groupBy(employeeMetaInfo.visa)
            .select(employeeMetaInfo,employeeMonthly.hours.sum())
            .fetch().map {
                result[it[employeeMetaInfo]!!] = it[employeeMonthly.hours.sum()]!!
            }
        return result
    }
}