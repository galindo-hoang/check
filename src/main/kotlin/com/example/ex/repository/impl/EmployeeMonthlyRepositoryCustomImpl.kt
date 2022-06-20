package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.EmployeeMonthly
import com.example.ex.model.QEmployeeMonthly.Companion.employeeMonthly
import com.example.ex.repository.EmployeeMonthlyRepositoryCustom
import com.example.ex.utils.Constant
import com.example.ex.utils.Constant.getJpaQuery
import com.querydsl.jpa.impl.JPADeleteClause
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import java.io.File
import java.io.FileInputStream
import java.sql.Date
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class EmployeeMonthlyRepositoryCustomImpl(
    @Value("\${excel.fileMonthlyVertec}")
    val filepath: String,
    @PersistenceContext
    private val entityManager: EntityManager
): EmployeeMonthlyRepositoryCustom {
    override fun findEmployeeByMonthFromXLSX(month: Int): List<EmployeeMonthlyDto> {
        val result: MutableList<EmployeeMonthlyDto> = mutableListOf()
        if(File(filepath).exists()){
            FileInputStream(filepath).use { file ->
                val xlWb = WorkbookFactory.create(file)
                val sheet = xlWb.getSheetAt(0)
                val titleColumn = Constant.getTitleXLSX(sheet)
                for(i in 1 .. sheet.lastRowNum){
                    val modelHash = Constant.convertXLSXToHashMap(sheet.getRow(i), titleColumn)
                    val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), EmployeeMonthlyDto::class.java)
                    if(model.dateJava!!.month + 1 == month) result.add(model)
                }
            }
        }else println("File not exist")
        return result
    }

    override fun deleteEmployeeByMonth(month: List<Date>) {
        val delete = JPADeleteClause(entityManager, employeeMonthly)
        for(i in month){
            delete.where(employeeMonthly.date.eq(i)).execute()
        }
    }

    override fun findByProjectGroup(projectGroup: String?): List<EmployeeMonthly> {
        val query = getJpaQuery(entityManager).from(employeeMonthly)
        if(projectGroup == null){
            query.where(employeeMonthly.projectGroup.isNull)
        }else query.where(employeeMonthly.projectGroup.eq(projectGroup))
        return query.fetch().toList() as List<EmployeeMonthly>
    }

    override fun mappingProjectGroup(employeeMonthly: EmployeeMonthly) {
        entityManager.merge(employeeMonthly)
    }
}