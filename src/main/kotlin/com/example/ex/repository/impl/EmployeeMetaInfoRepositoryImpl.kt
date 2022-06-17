package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.repository.EmployeeMetaInfoRepositoryCustom
import com.example.ex.utils.Constant
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import java.io.File
import java.io.FileInputStream

class EmployeeMetaInfoRepositoryImpl(
    @Value("\${excel.fileMetaInfo}")
    val filepath: String,
): EmployeeMetaInfoRepositoryCustom {
    override fun findEmployeeByListVisa(listVisa: List<String>):List<EmployeeMetaInfo> {
        return listOf()
    }

    override fun findAllFromXLSX(): List<EmployeeMetaInfoDto> {
        val result: MutableList<EmployeeMetaInfoDto> = mutableListOf()
        if(File(filepath).exists()){
            FileInputStream(filepath).use { file ->
                val xlWb = WorkbookFactory.create(file)
                val sheet = xlWb.getSheetAt(0)
                val titleColumn = Constant.getTitleXLSX(sheet)
                for(i in 1 .. sheet.lastRowNum){
                    val modelHash = Constant.convertXLSXToHashMap(sheet.getRow(i), titleColumn)
                    val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), EmployeeMetaInfoDto::class.java)
                    result.add(model)
                }
            }
        }else println("File not exist")
        return result
    }
}