package com.example.ex.utils

import com.google.gson.Gson
import com.querydsl.jpa.impl.JPAQueryFactory
import kotlinx.serialization.json.*
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.usermodel.Cell.*
import java.util.*
import javax.persistence.EntityManager
import kotlin.collections.HashMap


object Constant {

    const val MONTH_YEAR = "Month"
    const val VISA = "Visa"
    const val P = "P"
    const val PROJECT = "Project"


    fun getJpaQuery(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }

    private val CALENDAR: Calendar = Calendar.getInstance(TimeZone.getDefault())
    val getCalendar: Calendar get() = CALENDAR
    fun setTimeCalendar(value: Date) {
        CALENDAR.time = value
    }
    val gson = Gson()

    fun convertXLSXToHashMap(curRow: Row, titleColumn: HashMap<Int,String>): HashMap<String, Any?> {

        val modelHash = hashMapOf<String, Any?>()
        curRow.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
            val cellTitle = titleColumn[index]!!
            when(cell.cellType){
                CELL_TYPE_STRING -> modelHash[cellTitle] = cell.stringCellValue
                CELL_TYPE_BOOLEAN -> modelHash[cellTitle] = cell.booleanCellValue
                CELL_TYPE_NUMERIC -> {
                    if(DateUtil.isCellDateFormatted(cell)){
                        modelHash[cellTitle] = cell.dateCellValue
//                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format()
                    }else modelHash[cellTitle] = cell.numericCellValue
                }
                CELL_TYPE_FORMULA -> {
                    when(cell.cachedFormulaResultType){
                        CELL_TYPE_STRING -> modelHash[cellTitle] = cell.stringCellValue
                        CELL_TYPE_BOOLEAN -> modelHash[cellTitle] = cell.booleanCellValue
                        CELL_TYPE_NUMERIC -> {
                            if(DateUtil.isCellDateFormatted(cell)){
                                modelHash[cellTitle] = cell.dateCellValue
                            }else modelHash[cellTitle] = cell.numericCellValue
                        }
                        else -> {modelHash[cellTitle] = null}
                    }
                }
                else -> {}
            }
        }
        return modelHash
    }

    fun getTitleXLSX(sheet: Sheet): HashMap<Int, String> {
        val titleColumn: HashMap<Int, String> = hashMapOf()
        sheet.getRow(0).cellIterator().asSequence().toList().forEachIndexed { index, cell ->
            titleColumn[index] = cell.stringCellValue
        }
        return titleColumn
    }
}