package com.example.ex.utils

import com.google.gson.Gson
import com.querydsl.jpa.impl.JPAQueryFactory
import kotlinx.serialization.json.*
import org.apache.poi.ss.usermodel.*
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


    val format = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    val gson = Gson()

    fun convertXLSXToHashMap(curRow: Row, titleColumn: HashMap<Int,String>): HashMap<String, Any>{

        val modelHash = hashMapOf<String, Any>()
        curRow.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
            val cellTitle = titleColumn[index]!!
            when(cell.cellType){
                CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                CellType.BOOLEAN -> modelHash[cellTitle] = cell.booleanCellValue
                CellType.NUMERIC -> {
                    if(DateUtil.isCellDateFormatted(cell)){
                        modelHash[cellTitle] = cell.dateCellValue
//                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format()
                    }else modelHash[cellTitle] = cell.numericCellValue
                }
                CellType.FORMULA -> {
                    when(cell.cachedFormulaResultType){
                        CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                        CellType.BOOLEAN -> modelHash[cellTitle] = cell.booleanCellValue
                        CellType.NUMERIC -> {
                            if(DateUtil.isCellDateFormatted(cell)){
                                modelHash[cellTitle] = cell.dateCellValue
                            }else modelHash[cellTitle] = cell.numericCellValue
                        }
                        else -> {}
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