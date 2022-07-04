package com.example.ex.repository.impl

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.dto.WhoDoWhat
import com.example.ex.exception.FileNotFoundExceptionCustom
import com.example.ex.model.EmployeeMonthly
import com.example.ex.model.QEmployeeMonthly.Companion.employeeMonthly
import com.example.ex.repository.EmployeeMonthlyRepositoryCustom
import com.example.ex.utils.Constant
import com.example.ex.utils.Constant.getCalendar
import com.example.ex.utils.Constant.getJpaQuery
import com.example.ex.utils.Constant.setTimeCalendar
import com.spire.xls.Workbook
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import java.io.BufferedOutputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.sql.Date
import java.util.*
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class EmployeeMonthlyRepositoryCustomImpl(
    @Value("\${excel.fileMonthlyVertec}")
    val fileMonthlyVertec: String,
    @Value("\${excel.fileWhoDoWhat}")
    val filepathWhoDoWhat: String,
    @PersistenceContext
    private val entityManager: EntityManager
): EmployeeMonthlyRepositoryCustom {
    override fun findEmployeeByMonthFromXLSX(month: Int): List<EmployeeMonthlyDto> {
        val listEmployeeMonthlyDto: MutableList<EmployeeMonthlyDto> = mutableListOf()
        try {
            FileInputStream(fileMonthlyVertec).use { file ->
                try {
                    val wb = WorkbookFactory.create(file)
                    val sheet = wb.getSheetAt(0)
                    val titleColumn = Constant.getTitleXLSX(sheet)
                    for(i in 1 .. sheet.lastRowNum){
                        val modelHash = Constant.convertXLSXToHashMap(sheet.getRow(i), titleColumn)
                        val model = Constant.gson.fromJson(Constant.gson.toJson(modelHash), EmployeeMonthlyDto::class.java)
                        setTimeCalendar(model.dateJava!!)
                        if(getCalendar.get(Calendar.MONTH) + 1 == month) listEmployeeMonthlyDto.add(model)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    throw FileNotFoundExceptionCustom("XLSX $fileMonthlyVertec not Exist",HttpStatus.CONFLICT)
                }
            }
            return listEmployeeMonthlyDto
        }catch (e:Exception){
            e.printStackTrace()
            throw FileNotFoundExceptionCustom("URL $fileMonthlyVertec not Found", HttpStatus.NOT_FOUND)
        }
    }

    override fun findEmployeeByMonth(month: Date): List<EmployeeMonthly> {
        return getJpaQuery(entityManager)
            .from(employeeMonthly)
            .where(employeeMonthly.date.eq(month))
            .fetch() as List<EmployeeMonthly>
    }

    override fun findByProjectGroup(projectGroup: String?): List<EmployeeMonthly> {
        val query = getJpaQuery(entityManager).from(employeeMonthly)
        if(projectGroup == null){
            query.where(employeeMonthly.projectGroup.isNull)
        }else query.where(employeeMonthly.projectGroup.eq(projectGroup))
        return query.fetch().toList() as List<EmployeeMonthly>
    }

    override fun findHoursByMonthYearGroupByVisaProjectGroup(month: Int, year: Int): List<WhoDoWhat> {
        val query = getJpaQuery(entityManager).from(employeeMonthly)
        query
            .where(
                employeeMonthly.date.lt(Date.valueOf("$year-${month+1}-1"))
                    .and(employeeMonthly.date.goe(Date.valueOf("$year-${month}-1")))
            )
            .groupBy(employeeMonthly.metaInfo.visa, employeeMonthly.projectGroup)
        return query
            .select(employeeMonthly.metaInfo.visa, employeeMonthly.projectGroup, employeeMonthly.hours.sum())
            .fetch().map {
                WhoDoWhat(
                    visa = it[employeeMonthly.metaInfo.visa]!!,
                    projectGroup = it[employeeMonthly.projectGroup] ?: "",
                    hours = it[employeeMonthly.hours.sum()]!!
                )
        }
    }

    override fun readingLabelRowAndCol(): Pair<HashMap<String, Int>?, HashMap<String, Int>?> {
        try{
            var labelProjectGroup: HashMap<String, Int>? = null
            var labelVisa: HashMap<String,Int>? = null
            FileInputStream(filepathWhoDoWhat).use { fis ->
                val wb = WorkbookFactory.create(fis)
                val sheet = wb.getSheetAt(0)
                labelProjectGroup = readLabel(sheet) { sh, iterator ->
                    sh.getRow(0).getCell(iterator)
                }
                labelVisa = readLabel(sheet) {sh, i ->
                    sh.getRow(i).getCell(0)
                }
            }
            return Pair(labelProjectGroup,labelVisa)
        }catch (e:Exception) {
            e.printStackTrace()
            throw FileNotFoundExceptionCustom("File $filepathWhoDoWhat cant read label", HttpStatus.NOT_FOUND)
        }
    }

    private fun readLabel(sheet: Sheet, func: (Sheet,Int) -> Cell?): HashMap<String,Int>{
        var isStart = false
        val resultForReadingLabel = hashMapOf<String,Int>()
        var iterator = 0
        while (true){
            val cell = func(sheet,iterator)
            if(cell != null){
                if(isStart){
                    if(cell.stringCellValue == "[END]") break
                    else resultForReadingLabel[cell.stringCellValue] = iterator
                }else{
                    if(cell.stringCellValue == "[START]") isStart = true
                }
            }
            ++iterator
        }
        return resultForReadingLabel
    }

    override fun fillDataIntoXLSX(
        month: Int,
        year: Int,
        labels: Pair<HashMap<String, Int>, HashMap<String, Int>>,
        data: List<WhoDoWhat>
    ): Boolean {
        cloneFile(filepathWhoDoWhat,"WhoDoWhat-${month}-${year}")

        val readFileWhoDoWhat = FileInputStream(filepathWhoDoWhat)
        try {
            val wb = WorkbookFactory.create(readFileWhoDoWhat)
            wb.removeSheetAt(wb.getSheetIndex("Evaluation Warning"))
            readFileWhoDoWhat.close()
            val sheet = wb.getSheet("WhoDoWhat-${month}-${year}")
            labels.first.forEach { (_, position) ->
                labels.second.values.forEach {
                    sheet.getRow(it).getCell(position).setCellValue(0.0)
                }
            }
            data.forEach {
                if (labels.first.containsKey(it.projectGroup) && labels.second.containsKey(it.visa)) {
                    sheet
                        .getRow(labels.second[it.visa]!!)
                        .getCell(labels.first[it.projectGroup]!!)
                        .setCellValue(it.hours)
                }
            }

            val writeFileWhoDoWhat = BufferedOutputStream(FileOutputStream(filepathWhoDoWhat))
            try {
                wb.write(writeFileWhoDoWhat)
                return true
            } catch (e: Exception) {
                throw FileNotFoundExceptionCustom("Cant write file $filepathWhoDoWhat", HttpStatus.NOT_FOUND)
            } finally {
                writeFileWhoDoWhat.close()
            }
        } catch (e: Exception) {
            throw FileNotFoundExceptionCustom("file $filepathWhoDoWhat cant open to write", HttpStatus.CONFLICT)
        }
        finally {
            readFileWhoDoWhat.close()
        }
    }

    private fun cloneFile(file: String, name:String){
        val workbook = Workbook()
        workbook.loadFromFile(file)
        val a = workbook.worksheets.get(0)
        val b = workbook.worksheets.add(name)
        b.copyFrom(a)
        workbook.saveToFile(file)
    }
}