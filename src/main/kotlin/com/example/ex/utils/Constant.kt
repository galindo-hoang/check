package com.example.ex.utils

import com.google.gson.Gson
import com.querydsl.jpa.impl.JPAQueryFactory
import kotlinx.serialization.json.*
import org.apache.poi.ss.usermodel.*
import javax.persistence.EntityManager
import kotlin.collections.HashMap

object Constant {

    const val MONTH_YEAR = "Month"
    const val SUB_PROJECT = "Subproject"
    const val VISA = "Visa"


    const val YEAR = "year"
    const val DATE = "Date"
    const val GRP_PRJ = "GRP-PRJ"
    const val P = "P"
    const val CODE = "Code"
    const val HRS = "Hrs"
    const val IS_VN = "Vn ?"
    const val PROJECT = "Project"


    fun getJpaQuery(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }


    val format = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    val gson = Gson()
    const val FORMAT_STRING_DATE = "yyyy-MM-dd"
    private fun Any?.toJsonElement(): JsonElement {
        return when (this) {
            is Number -> JsonPrimitive(this)
            is Boolean -> JsonPrimitive(this)
            is String -> JsonPrimitive(this)
            is Array<*> -> this.toJsonArray()
            is List<*> -> this.toJsonArray()
            is Map<*, *> -> this.toJsonObject()
            is JsonElement -> this
            else -> JsonNull
        }
    }

    private fun Array<*>.toJsonArray(): JsonArray {
        val array = mutableListOf<JsonElement>()
        this.forEach { array.add(it.toJsonElement()) }
        return JsonArray(array)
    }

    private fun List<*>.toJsonArray(): JsonArray {
        val array = mutableListOf<JsonElement>()
        this.forEach { array.add(it.toJsonElement()) }
        return JsonArray(array)
    }

    fun Map<*, *>.toJsonObject(): JsonObject {
        val map = mutableMapOf<String, JsonElement>()
        this.forEach {
            if (it.key is String) {
                map[it.key as String] = it.value.toJsonElement()
            }
        }
        return JsonObject(map)
    }

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
                    when(cell.cellType){
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