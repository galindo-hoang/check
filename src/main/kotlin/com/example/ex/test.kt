package com.example.ex

import com.example.ex.Utils.toJsonObject
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileInputStream

@Serializable
class Place(
){

    @SerialName("place_id") val placeId: String = ""
    val name: String = ""
    val types: List<Int> = TODO()
}



fun main() {
    val filepath = "/home/huy/Desktop/Ex1/src/main/kotlin/com/example/ex/ProjectData-sample.xlsx"
    val xlWb = HSSFWorkbook(FileInputStream(filepath))
    val b = xlWb.getSheet("ALL-DU-Vertec").getRow(47918)
    val formula = xlWb.creationHelper.createFormulaEvaluator()
    for(i in b.firstCellNum until  b.lastCellNum){
        println(b.getCell(i).stringCellValue)
        when(formula.evaluateInCell(b.getCell(i)).cellType){
            CellType.STRING -> {
                println("string - "+ b.getCell(i).stringCellValue)
            }
            CellType.BOOLEAN -> {
                println("Boolean - "+ b.getCell(i).booleanCellValue)
            }
            CellType.NUMERIC -> {
                println("numeric - "+ b.getCell(i).numericCellValue)
            }
        }
        println("-----------")
    }
}