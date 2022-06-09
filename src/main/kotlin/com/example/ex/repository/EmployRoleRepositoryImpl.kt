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
        val subQuery = getJpaQuery(entityManager).from(employeeRole)

        if(hourReportCriteria.levels.isNotEmpty()){
            subQuery
                .where(employeeRole.level.stringValue().append(".").append(employeeRole.subLevel.stringValue())
                    .`in`(hourReportCriteria.levels))
        }

        val query = getJpaQuery(entityManager)
                .from(employeeMonthly)
                .join(employeeMetaInfo)
                .on(employeeMonthly.metaInfo.visa.eq(employeeMetaInfo.visa))
        if(hourReportCriteria.projectCodes.isNotEmpty()){
            print("asd")
            query.where(employeeMonthly.subProject.`in`(hourReportCriteria.projectCodes))
        }

        query
            .where(employeeMonthly.date.goe(hourReportCriteria.startMonth)
                .and(employeeMonthly.date.loe(hourReportCriteria.endMonth)))
            .where(
                subQuery
                .where(employeeMetaInfo.visa.eq(employeeRole.abbreviation.visa))
                .exists()
            )
            .groupBy(employeeMetaInfo.visa)
            .select(employeeMetaInfo,employeeMonthly.hours.sum())
            .fetch().map {
                result[it[employeeMetaInfo]!!] = it[employeeMonthly.hours.sum()]!!
            }
        println(result.size)
        return result
    }
}