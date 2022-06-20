package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.utils.Constant.getJpaQuery
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.mapper.EmployeeRoleMapperDecorator
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeRole
import com.example.ex.model.QEmployeeMetaInfo.Companion.employeeMetaInfo
import com.example.ex.model.QEmployeeMonthly.Companion.employeeMonthly
import com.example.ex.model.QEmployeeRole.Companion.employeeRole
import com.example.ex.repository.EmployeeRoleRepositoryCustom
import com.example.ex.utils.Constant
import com.querydsl.jpa.impl.JPAQueryFactory
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.io.FileInputStream
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Repository
class EmployeeRoleRepositoryImpl(
    @Value("\${excel.fileRole}")
    val filepath: String,
): EmployeeRoleRepositoryCustom {
    @PersistenceContext
    private lateinit var entityManager: EntityManager
    @Autowired private lateinit var employeeRoleMapperDecorator: EmployeeRoleMapperDecorator

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
            query.where(employeeMonthly.subProject.`in`(hourReportCriteria.projectCodes))
        }

        query
            .where(employeeMonthly.date.goe(hourReportCriteria.startMonth)
                .and(employeeMonthly.date.loe(hourReportCriteria.endMonth)))
            .where(
                subQuery
                .where(employeeMonthly.metaInfo.visa.eq(employeeRole.abbreviation.visa))
                .exists()
            )
            .groupBy(employeeMetaInfo.visa)
            .select(employeeMetaInfo,employeeMonthly.hours.sum())
            .fetch().map {
                result[it[employeeMetaInfo]!!] = it[employeeMonthly.hours.sum()]!!
            }
        return result
    }

    override fun findAllFromXLSX(): List<EmployeeRoleDto> {
        val result: MutableList<EmployeeRoleDto> = mutableListOf()
        if(File(filepath).exists()){
            FileInputStream(filepath).use { file ->
                val xlWb = WorkbookFactory.create(file)
                val sheet = xlWb.getSheetAt(0)
                val titleColumn = Constant.getTitleXLSX(sheet)
                for(i in 1 .. sheet.lastRowNum){
                    val modelHash = Constant.convertXLSXToHashMap(sheet.getRow(i), titleColumn)
                    val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), EmployeeRoleDto::class.java)
                    result.add(model)
                }
            }
        }else println("File not exist")
        return result
    }

    @Transactional
    override fun saveAllCustom(list: List<EmployeeRoleDto>) {
        list.forEach {
            val entity = employeeRoleMapperDecorator.dtoToEntity(it)
            entity.abbreviation = entityManager.getReference(EmployeeMetaInfo::class.java,it.abbreviationss)
            entity.supervisor = entityManager.getReference(EmployeeMetaInfo::class.java,it.supervisorss)
            entityManager.persist(entity)
        }
    }

    override fun findEmployeeByAbbreviation(visa: String): Iterable<EmployeeRole> =
        JPAQueryFactory(entityManager)
            .from(employeeRole)
            .where(employeeRole.abbreviation.visa.eq(visa))
            .fetch() as Iterable<EmployeeRole>

    override fun findEmployeeBySupervisor(visa: String): Iterable<EmployeeRole> =
        getJpaQuery(entityManager)
            .from(employeeRole)
            .where(employeeRole.supervisor.visa.eq(visa))
            .fetch() as Iterable<EmployeeRole>
}