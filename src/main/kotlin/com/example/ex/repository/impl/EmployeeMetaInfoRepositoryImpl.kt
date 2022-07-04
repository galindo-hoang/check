package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.exception.FileNotFoundExceptionCustom
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.repository.EmployeeMetaInfoRepositoryCustom
import com.example.ex.utils.Constant
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
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
        try {
            FileInputStream(filepath).use { file ->
                val wb = WorkbookFactory.create(file)
                val sheet = wb.getSheetAt(0)
                val titleColumn = Constant.getTitleXLSX(sheet)
                for(i in 1 .. sheet.lastRowNum){
                    val modelHash = Constant.convertXLSXToHashMap(sheet.getRow(i), titleColumn)
                    val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), EmployeeMetaInfoDto::class.java)
                    result.add(model)
                }
            }
        }catch (e: Exception){
            e.printStackTrace()
            throw FileNotFoundExceptionCustom("${e.message}",HttpStatus.NOT_FOUND)
        }
        return result
    }
}