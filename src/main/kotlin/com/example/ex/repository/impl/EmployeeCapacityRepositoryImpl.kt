package com.example.ex.repository.impl

import com.example.ex.dto.CapacityDto
import com.example.ex.model.QCapacity.Companion.capacity
import com.example.ex.repository.EmployeeCapacityRepositoryCustom
import com.example.ex.utils.Constant
import com.example.ex.utils.Constant.getJpaQuery
import com.example.ex.utils.Constant.toJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class EmployeeCapacityRepositoryImpl(
    @PersistenceContext private val entityManager: EntityManager
): EmployeeCapacityRepositoryCustom {

    override fun findVisaByMonth(month: Int): List<String> {

        val query = getJpaQuery(entityManager).from(capacity)
//        when(month){
//            1 -> query.where(capacity.month.jan.eq(1))
//            2 -> query.where(capacity.month.feb.eq(1))
//            3 -> query.where(capacity.month.mar.eq(1))
//            4 -> query.where(capacity.month.apr.eq(1))
//            5 -> query.where(capacity.month.mar.eq(1))
//            6 -> query.where(capacity.month.jun.eq(1))
//            7 -> query.where(capacity.month.jul.eq(1))
//            8 -> query.where(capacity.month.aug.eq(1))
//            9 -> query.where(capacity.month.sep.eq(1))
//            10 -> query.where(capacity.month.oct.eq(1))
//            11 -> query.where(capacity.month.nov.eq(1))
//            12 -> query.where(capacity.month.dec.eq(1))
//        }
        query.select(capacity.visa)
        return query.fetch().toList() as List<String>
    }

    override fun findVisaByListDate(dates: List<Date>): List<String> {
        val path = "C:\\Users\\hoah\\Desktop\\jira\\Ex1\\Extra.xlsx"
        FileInputStream(path).use {file ->
            val result: MutableList<String> = mutableListOf()
            val titleColumn: HashMap<Int, String> = hashMapOf()
            val xlWb = WorkbookFactory.create(file)
            val sheet = xlWb.getSheetAt(0)
            sheet.getRow(0).cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                titleColumn[index] = cell.stringCellValue

            }

            for(i in 1 until sheet.lastRowNum){
                val cell = sheet.getRow(i)
                val modelHash = hashMapOf<String, Any>()
                cell.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                    val cellTitle = titleColumn[index]!!
                    when(cell.cellType){
                        CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                        CellType.BOOLEAN -> modelHash[cellTitle] = cell.stringCellValue
                        CellType.NUMERIC -> {
                            if(DateUtil.isCellDateFormatted(cell)){
                                modelHash[cellTitle] = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cell.dateCellValue)
                            }else modelHash[cellTitle] = cell.numericCellValue
                        }
                        CellType.FORMULA -> {
                            when(cell.cellType){
                                CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                                CellType.BOOLEAN -> modelHash[cellTitle] = cell.stringCellValue
                                CellType.NUMERIC -> {
                                    if(DateUtil.isCellDateFormatted(cell)){
                                        modelHash[cellTitle] = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cell.dateCellValue)
                                    }else modelHash[cellTitle] = cell.numericCellValue
                                }
                            }
                        }
                    }
                }
                val model: CapacityDto = Constant.format.decodeFromJsonElement(modelHash.toJsonObject())

                dates.forEach {
                    if(SimpleDateFormat("yyyy-MM-dd").parse(model.startDate) < it && (model.endDate == "" || SimpleDateFormat("yyyy-MM-dd").parse(model.endDate) > it)){
                        result.add(model.visa)
                        return@forEach
                    }
                }
            }
            return result
        }
    }
}