package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.repository.EmployeeMonthlyRepositoryCustom
import com.example.ex.utils.Constant
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

class EmployeeMonthlyRepositoryCustomImpl: EmployeeMonthlyRepositoryCustom {
    override fun findEmployeeByMonth(month: Int): List<EmployeeMonthlyDto> {
        val path = "C:\\Users\\hoah\\Desktop\\jira\\Ex1\\EmployeeMonthlyVertec-sample.xlsx"
        FileInputStream(path).use {file ->
            val titleColumn: HashMap<Int, String> = hashMapOf()
            val xlWb = WorkbookFactory.create(file)
            val sheet = xlWb.getSheetAt(0)
            sheet.getRow(0).cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                titleColumn[index] = cell.stringCellValue

            }

            val result: MutableList<EmployeeMonthlyDto> = mutableListOf()
            for(i in 1 until sheet.lastRowNum){
                val cell = sheet.getRow(i)
                val modelHash = hashMapOf<String, Any>()
                cell.cellIterator().asSequence().toList().forEachIndexed { index, cell ->
                    val cellTitle = titleColumn[index]!!
                    when(cell.cellType){
                        CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                        CellType.BOOLEAN -> modelHash[cellTitle] = cell.booleanCellValue
                        CellType.NUMERIC -> {
                            if(DateUtil.isCellDateFormatted(cell)){
                                modelHash[cellTitle] = cell.dateCellValue
                            }else {
                                modelHash[cellTitle] = cell.numericCellValue
                            }
                        }
                        CellType.FORMULA -> {
                            when(cell.cellType){
                                CellType.STRING -> modelHash[cellTitle] = cell.stringCellValue
                                CellType.BOOLEAN -> modelHash[cellTitle] = cell.booleanCellValue
                                CellType.NUMERIC -> {
                                    if(DateUtil.isCellDateFormatted(cell)){
                                        modelHash[cellTitle] = cell.dateCellValue
                                    }else {
                                        modelHash[cellTitle] = cell.numericCellValue
                                    }
                                }
                            }
                        }
                    }
                }
                val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), EmployeeMonthlyDto::class.java)
                if(model.dateJava!!.month + 1 == month) result.add(model)
            }
            return result
        }
    }
}