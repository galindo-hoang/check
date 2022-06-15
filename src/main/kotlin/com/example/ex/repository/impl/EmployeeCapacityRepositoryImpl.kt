package com.example.ex.repository.impl

import com.example.ex.dto.CapacityDto
import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.model.QCapacity.Companion.capacity
import com.example.ex.repository.EmployeeCapacityRepositoryCustom
import com.example.ex.utils.Constant.getJpaQuery
import com.example.ex.utils.Constant.gson
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import java.io.File
import java.io.FileInputStream
import java.util.*
import java.util.logging.Logger
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

    override fun findMonthlyMeetCriteria(list: List<EmployeeMonthlyDto>): List<EmployeeMonthlyDto> {
        val clone: MutableList<EmployeeMonthlyDto> = list as MutableList<EmployeeMonthlyDto>
        val result: MutableList<EmployeeMonthlyDto> = mutableListOf()
        if(File(filepath).isFile){
            FileInputStream(filepath).use { file ->
                val titleColumn: HashMap<Int, String> = hashMapOf()
                val xlWb = WorkbookFactory.create(file)
                val sheet = xlWb.getSheetAt(0)
                sheet.getRow(0).cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                    titleColumn[index] = cell.stringCellValue

                }

                for(i in 1 .. sheet.lastRowNum){
                    val cell = sheet.getRow(i)
                    val modelHash = hashMapOf<String, Any>()
                    cell.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                        val cellTitle = titleColumn[index]!!
                        when(cell.cellType){
                            CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                            CellType.BOOLEAN -> modelHash[cellTitle] = cell.stringCellValue
                            CellType.NUMERIC -> {
                                if(DateUtil.isCellDateFormatted(cell)){
                                    modelHash[cellTitle] = cell.dateCellValue
//                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format()
                                }else modelHash[cellTitle] = cell.numericCellValue
                            }
                            CellType.FORMULA -> {
                                when(cell.cellType){
                                    CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                                    CellType.BOOLEAN -> modelHash[cellTitle] = cell.stringCellValue
                                    CellType.NUMERIC -> {
                                        if(DateUtil.isCellDateFormatted(cell)){
                                            modelHash[cellTitle] = cell.dateCellValue
                                        }else modelHash[cellTitle] = cell.numericCellValue
                                    }
                                }
                            }
                        }
                    }
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