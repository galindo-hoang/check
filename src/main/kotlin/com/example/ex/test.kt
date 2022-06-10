package com.example.ex

import com.example.ex.Utils.format
import com.example.ex.Utils.toJsonObject
import com.example.ex.model.Vertec
import kotlinx.serialization.json.decodeFromJsonElement
import org.apache.poi.ss.usermodel.*
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

fun main() {
    val filepath = "C:\\Users\\hoah\\Desktop\\jira\\Ex1\\src\\main\\kotlin\\com\\example\\ex\\ProjectData-sample.xlsx"
    val xlWb = WorkbookFactory.create(FileInputStream(filepath))
    val sheet = xlWb.getSheet("ALL-DU-Vertec")
    val hash: HashMap<Int,String> = hashMapOf()
    sheet.getRow(1).cellIterator().asSequence().toList().forEachIndexed{ i,c ->
        hash[i] = c.stringCellValue.toString()
    }
    val listData: MutableList<Vertec> = mutableListOf()
    sheet.rowIterator().asSequence().toList().forEachIndexed{ i,r ->
        val model: HashMap<String,Any> = hashMapOf()
        if(i >=2 && r.getCell(0).stringCellValue.split(".")[0] == "05" && r.getCell(0).stringCellValue.split(".")[1] == "2"){
            r.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                val titleCell = hash[index]!!
                if(cell != null){
                    when (cell.cellType) {
                        CellType.STRING -> model[titleCell] = cell.stringCellValue

                        CellType.NUMERIC -> {
                            if (DateUtil.isCellDateFormatted(cell)) {
                                model[titleCell] =
                                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cell.dateCellValue)
                                println("---" + model[titleCell])
                            } else {
                                if (!titleCell.contains("Hrs")) model[titleCell] = cell.numericCellValue.toInt()
                                else model[titleCell] = cell.numericCellValue
                            }
                        }

                        CellType.FORMULA -> {
                            when (cell.cachedFormulaResultType) {
                                CellType.STRING -> model[titleCell] = cell.stringCellValue
                                CellType.BOOLEAN -> model[titleCell] = cell.booleanCellValue
                                CellType.NUMERIC -> {
                                    if (!titleCell.contains("Hrs")) model[titleCell] = cell.numericCellValue.toInt()
                                    else model[titleCell] = cell.numericCellValue
                                }
                                else -> {}
                            }
                        }
                        else -> {}
                    }
                }
                listData.add(format.decodeFromJsonElement(model.toJsonObject()))
            }
        }
    }
}
