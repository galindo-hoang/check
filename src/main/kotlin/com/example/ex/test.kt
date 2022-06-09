package com.example.ex

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*
import org.apache.poi.ss.usermodel.*
import java.io.FileInputStream

@Serializable
class Place(
){

    @SerialName("place_id") val placeId: String = ""
    val name: String = ""
    val types: List<Int> = TODO()
}



fun main() {
    val filepath = "C:\\Users\\huy\\elca\\Ex1\\src\\main\\kotlin\\com\\example\\ex\\ProjectData-sample.xlsx"
    val xlWb = WorkbookFactory.create(FileInputStream(filepath))
    val b = xlWb.getSheet("ALL-DU-Vertec").getRow(47918)
    val hash: HashMap<String,Int> = hashMapOf()
    xlWb.getSheet("ALL-DU-Vertec").getRow(1).cellIterator().asSequence().toList().forEachIndexed{ i,v ->
        hash[v.stringCellValue] = i
    }
    println(hash)
//    for(i in 0 until  b.lastCellNum){
//        println("$i - ${b.getCell(i).cellType}")
//        when(b.getCell(i).cellType){
//            CellType.STRING -> {
//                println("string - "+ b.getCell(i).stringCellValue)
//            }
//            CellType.NUMERIC -> {
//                if(DateUtil.isCellDateFormatted(b.getCell(i))){
//                    println("date - "+ b.getCell(i).dateCellValue)
//                }else{
//                    println("numeric - "+ b.getCell(i))
//                }
//            }
//            CellType.FORMULA -> {
//                when(b.getCell(i).cachedFormulaResultType){
//                    CellType.STRING -> {
//                        println("string -- "+ b.getCell(i).stringCellValue)
//                    }
//                    CellType.BOOLEAN -> {
//                        println("Boolean - "+ b.getCell(i).booleanCellValue)
//                    }
//                    CellType.NUMERIC -> {
//                        println("Numeric -- "+ b.getCell(i).numericCellValue)
//                    }
//                }
//            }
//        }
//    }
}