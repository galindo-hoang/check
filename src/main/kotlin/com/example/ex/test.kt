package com.example.ex

import com.example.ex.dto.CapacityDto
import com.example.ex.utils.Constant
import com.example.ex.utils.Constant.toJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

fun main(){

    val path = "C:\\Users\\hoah\\Desktop\\jira\\Ex1\\Extra.xlsx"
    val titleColumn: HashMap<Int, String> = hashMapOf()
    val xlWb = WorkbookFactory.create(FileInputStream(path))
    val sheet = xlWb.getSheetAt(0)
    sheet.getRow(0).cellIterator().asSequence().toList().forEachIndexed { index, cell ->
        titleColumn[index] = cell.stringCellValue

    }

    val listData: MutableList<CapacityDto> = mutableListOf()
    for(i in 1 until sheet.lastRowNum){
        val cell = sheet.getRow(i)
        val model = hashMapOf<String, Any>()
        cell.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
            val cellTitle = titleColumn[index]!!
            when(cell.cellType){
                CellType.STRING -> model[cellTitle] = cell.stringCellValue
                CellType.BOOLEAN -> model[cellTitle] = cell.stringCellValue
                CellType.NUMERIC -> {
                    if(DateUtil.isCellDateFormatted(cell)){
                        model[cellTitle] = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cell.dateCellValue)
                    }else model[cellTitle] = cell.numericCellValue
                }
                CellType.FORMULA -> {
                    when(cell.cellType){
                        CellType.STRING -> model[cellTitle] = cell.stringCellValue
                        CellType.BOOLEAN -> model[cellTitle] = cell.stringCellValue
                        CellType.NUMERIC -> {
                            if(DateUtil.isCellDateFormatted(cell)){
                                model[cellTitle] = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cell.dateCellValue)
                            }else model[cellTitle] = cell.numericCellValue
                        }
                    }
                }
            }
        }
        listData.add(Constant.format.decodeFromJsonElement(model.toJsonObject()))

    }
    println(listData)
}