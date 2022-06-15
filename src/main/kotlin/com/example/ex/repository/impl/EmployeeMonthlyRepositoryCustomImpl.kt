package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.repository.EmployeeMonthlyRepositoryCustom
import com.example.ex.utils.Constant
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import java.io.File
import java.io.FileInputStream
import java.util.*

class EmployeeMonthlyRepositoryCustomImpl(
    @Value("\${excel.fileMonthlyVertec}")
    val filepath: String,
): EmployeeMonthlyRepositoryCustom {
    override fun findEmployeeByMonth(month: Int): List<EmployeeMonthlyDto> {
        val result: MutableList<EmployeeMonthlyDto> = mutableListOf()
        if(File(filepath).exists()){
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
            }
        }else println("File not exist")
        return result
    }
}