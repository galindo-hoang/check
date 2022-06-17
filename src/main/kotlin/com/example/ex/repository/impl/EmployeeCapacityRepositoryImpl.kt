package com.example.ex.repository.impl

import com.example.ex.dto.CapacityDto
import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.QCapacity.Companion.capacity
import com.example.ex.repository.EmployeeCapacityRepositoryCustom
import com.example.ex.utils.Constant.convertXLSXToHashMap
import com.example.ex.utils.Constant.getJpaQuery
import com.example.ex.utils.Constant.getTitleXLSX
import com.example.ex.utils.Constant.gson
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import java.io.File
import java.io.FileInputStream
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class EmployeeCapacityRepositoryImpl(
    @Value("\${excel.fileCapacity}")
    val filepath: String,
    @PersistenceContext private val entityManager: EntityManager
): EmployeeCapacityRepositoryCustom {

    override fun findVisaByMonth(month: Int): List<String> {

        val query = getJpaQuery(entityManager).from(capacity)
        query.select(capacity.visa)
        return query.fetch().toList() as List<String>
    }

    override fun findMonthlyMeetCriteriaFromXLSX(list: List<EmployeeMonthlyDto>): List<EmployeeMonthlyDto> {
        val clone: MutableList<EmployeeMonthlyDto> = list as MutableList<EmployeeMonthlyDto>
        val result: MutableList<EmployeeMonthlyDto> = mutableListOf()
        if(File(filepath).isFile){
            FileInputStream(filepath).use { file ->
                val xlWb = WorkbookFactory.create(file)
                val sheet = xlWb.getSheetAt(0)
                val titleColumn = getTitleXLSX(sheet)
                for(i in 1 .. sheet.lastRowNum){
                    val modelHash = convertXLSXToHashMap(sheet.getRow(i),titleColumn)
                    val model = gson.fromJson(gson.toJson(modelHash),CapacityDto::class.java)
                    val list = clone.filter { model.startDate!! < it.dateJava && (model.endDate == null || model.endDate!! > it.dateJava) && it.visa == model.visa }
                    result.addAll(list)
                    clone.removeAll(list)
                }
            }
        }else println("file not exits")
        return result
    }
}