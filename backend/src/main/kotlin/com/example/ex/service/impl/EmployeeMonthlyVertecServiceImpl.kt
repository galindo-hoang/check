package com.example.ex.service.impl

import com.example.ex.dto.*
import com.example.ex.exception.BusinessExceptionCustom
import com.example.ex.exception.TechExceptionCustom
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.repository.EmployeeRoleRepository
import com.example.ex.repository.ProjectMappingRepositoryCustom
import com.example.ex.service.EmployeeMonthlyVertecService
import com.example.ex.utils.Constant
import org.apache.poi.ss.usermodel.WorkbookFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.FileInputStream
import java.util.*

@Service
@Transactional(rollbackFor = [Exception::class])
class EmployeeMonthlyVertecServiceImpl(
    @Value("\${excel.fileMonthlyVertec}")
    val fileMonthlyVertec: String,
):
    EmployeeMonthlyVertecService {

    @Autowired private lateinit var employeeMonthlyMapperDecorator: EmployeeMonthlyMapperDecorator
    @Autowired private lateinit var projectMappingRepositoryCustom: ProjectMappingRepositoryCustom
    @Autowired private lateinit var employeeRoleRepository: EmployeeRoleRepository
    @Autowired private lateinit var employeeMonthlyRepository: EmployeeMonthlyRepository
    @Autowired private lateinit var employeeCapacityRepository: EmployeeCapacityRepository
    override fun loadAllEmployee(): MutableIterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findAll()
    }

    override fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findEmployeeMonthliesByMetaInfo(visa)
    }
    override fun saveEmployee(employeeMonthly: EmployeeMonthly) {
        employeeMonthlyRepository.save(employeeMonthly)
    }

    override fun loadEmployeeByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto): Map<EmployeeMetaInfo,Double> {
        return employeeRoleRepository.findEmployeesByHourReportCriteria(hourReportCriteria)
    }
    private fun findEmployeeByMonthFromXLSX(month: Int): List<EmployeeMonthlyDto> {
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
                        Constant.setTimeCalendar(model.dateJava!!)
                        if(Constant.getCalendar.get(Calendar.MONTH) + 1 == month) listEmployeeMonthlyDto.add(model)
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                    throw TechExceptionCustom("XLSX $fileMonthlyVertec not Exist",e)
                }
            }
            return listEmployeeMonthlyDto
        }catch (e:Exception){
            e.printStackTrace()
            throw TechExceptionCustom("${e.message}", e)
        }
    }
    override fun saveEmployeeByMonth(month: Int, year: Int): List<EmployeeMonthlyDto> {
        employeeMonthlyRepository.deleteEmployeeByMonth(month)
        val dtoList: MutableList<EmployeeMonthlyDto> = mutableListOf()
        dtoList.addAll(employeeCapacityRepository.findMonthlyMeetCriteriaFromXLSX(this.findEmployeeByMonthFromXLSX(month)))
        employeeMonthlyRepository.saveAll(
            dtoList.map {
                employeeMonthlyMapperDecorator.dtoToEntity(it)
            }
        )
        return dtoList
    }

    override fun mappingProjectGroup(): List<EmployeeMonthly> {
        val list = projectMappingRepositoryCustom.fetchAllFromXLSX()
        val transformMapping = list.associate { it.projectCode to it.projectGroup }
        val monthlyVertec = employeeMonthlyRepository.findByProjectGroup(null)
        return monthlyVertec.filter {
            if(transformMapping.containsKey(it.project)){
                it.projectGroup = transformMapping[it.project]
                employeeMonthlyRepository.save(it)
            }
            !transformMapping.containsKey(it.project)
        }
    }

    override fun fillWhoDoWhatByMonth(month: Int, year: Int): List<WhoDoWhat> {
        val label: Pair<HashMap<String, Int>?, HashMap<String, Int>?> = employeeMonthlyRepository.readingLabelRowAndCol()
        if(label.second == null || label.first == null){
            throw BusinessExceptionCustom("label of ProjectGroup or Visa empty")
        }else {
            val whoDoWhatList = employeeMonthlyRepository.findHoursByMonthYearGroupByVisaProjectGroup(month, year)
            employeeMonthlyRepository.fillDataIntoXLSX(
                month,
                year,
                label as Pair<HashMap<String, Int>, HashMap<String, Int>>,
                whoDoWhatList
            )
            return whoDoWhatList
        }
    }

    override fun filterCriteriaChart(criteriaChart: CriteriaChart): List<EmployeeMonthlyChart> =
        employeeMonthlyRepository.findEmployeeByCriteriaChart(criteriaChart)

    override fun getListProjectGroup(): List<String> = employeeMonthlyRepository.getListProjectGroups()
}