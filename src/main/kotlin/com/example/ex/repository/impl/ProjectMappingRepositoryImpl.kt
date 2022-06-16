package com.example.ex.repository.impl

import com.example.ex.dto.ProjectMappingDto
import com.example.ex.repository.ProjectMappingRepositoryCustom
import com.example.ex.utils.Constant
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File
import java.io.FileInputStream

@Component
class ProjectMappingRepositoryImpl(
    @Value("\${excel.fileProjectMapping}")
    val filepath: String,
): ProjectMappingRepositoryCustom {
    override fun fetchAll(): List<ProjectMappingDto> {
        val file = File(filepath)
        val result = mutableListOf<ProjectMappingDto>()
        if(file.exists() && file.isFile){
            FileInputStream(file).use { it ->
                val xlWb = WorkbookFactory.create(it)
                val sheet = xlWb.getSheetAt(0)
                val titleColumn = Constant.getTitleXLSX(sheet)
                for(i in 1 .. sheet.lastRowNum){
                    val modelHash = Constant.convertXLSXToHashMap(sheet.getRow(i), titleColumn)
                    val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), ProjectMappingDto::class.java)
                    result.add(model)
                }

            }
        }
        return result
    }
}