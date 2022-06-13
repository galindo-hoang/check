package com.example.ex.repository.impl

import com.example.ex.dto.VertecDto
import com.example.ex.repository.VertecRepositoryCustom
import com.example.ex.repository.filterxlsx.CriteriaFactory
import com.example.ex.repository.filterxlsx.CriteriaXLSX
import com.example.ex.utils.Constant
import com.example.ex.utils.Constant.toJsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.DateUtil
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.*

class VertecRepositoryImpl(
    @Value("\${excel.file}")
    val filepath: String
): VertecRepositoryCustom {
    override fun findAllVertecByMonthYear(data: HashMap<String, Any>): MutableList<VertecDto> {
        val titleColumn: HashMap<Int, String> = hashMapOf()
        val xlWb = WorkbookFactory.create(FileInputStream(filepath))
        val sheet = xlWb.getSheet("ALL-DU-Vertec")
        val filter = hashMapOf<Int,CriteriaXLSX>()

        sheet.getRow(1).cellIterator().asSequence().toList().forEachIndexed{ i,c ->
            titleColumn[i] = c.stringCellValue
            if(data.containsKey(c.stringCellValue)){
                filter[i] = CriteriaFactory.getCriteria(c.stringCellValue)
            }
        }


        val listData: MutableList<VertecDto> = mutableListOf()
        sheet.rowIterator().asSequence().toList().forEachIndexed{ i,r ->
            val model: HashMap<String, Any> = hashMapOf()
            if(i>=2){
                for(index in r.firstCellNum until  sheet.getRow(1).lastCellNum){
                    val cell = r.getCell(index)

                    val titleCell = titleColumn[index]!!
                    if(cell == null && filter.containsKey(index)) {
                        model.clear()
                        break
                    }
                    if(cell != null){
                        if(!filter.containsKey(index) || (filter.containsKey(index) && filter[index]!!.meetCriteria(cell,data[titleCell]!!))){
                            when (cell.cellType) {
                                CellType.STRING -> model[titleCell] = cell.stringCellValue

                                CellType.NUMERIC -> {
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        model[titleCell] =
                                            SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(cell.dateCellValue)
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
                        }else {
                            model.clear()
                            break
                        }
                    }
                }
                if(model.isNotEmpty()) {
                    listData.add(Constant.format.decodeFromJsonElement(model.toJsonObject()))
                }
            }
        }
        return listData
    }
}