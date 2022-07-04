package com.example.ex.service.impl

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.dto.WhoDoWhat
import com.example.ex.exception.ErrorMessage
import com.example.ex.exception.FileNotFoundExceptionCustom
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeMonthly
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.repository.EmployeeRoleRepository
import com.example.ex.repository.ProjectMappingRepositoryCustom
import com.example.ex.service.EmployeeMonthlyVertecService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

@Service
class EmployeeMonthlyVertecServiceImpl:
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

    @Transactional
    override fun saveEmployeeByMonth(month: Int, year: Int): List<EmployeeMonthlyDto> {
        val dtoList: MutableList<EmployeeMonthlyDto> = mutableListOf()
        dtoList.addAll(employeeCapacityRepository.findMonthlyMeetCriteriaFromXLSX(employeeMonthlyRepository.findEmployeeByMonthFromXLSX(month)))
        val listEmployeeMonthly = mutableListOf<EmployeeMonthly>()
        dtoList
            .map { Date.valueOf(SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(it.dateJava))}
            .distinct()
            .forEach { listEmployeeMonthly.addAll(employeeMonthlyRepository.findEmployeeByMonth(it)) }
        employeeMonthlyRepository.deleteAll(listEmployeeMonthly.map { it })
        employeeMonthlyRepository.saveAll(
            dtoList.map {
                employeeMonthlyMapperDecorator.dtoToEntity(it)
            }
        )
        return dtoList
    }

    @Transactional
    override fun mappingProjectGroup(): List<EmployeeMonthly> {
        val list = projectMappingRepositoryCustom.fetchAllFromXLSX()
        val transformMapping = list.associate { it.projectCode to it.projectGroup }
        val monthlyVertec = employeeMonthlyRepository.findByProjectGroup(null)
        return monthlyVertec.filter {
            if(transformMapping.containsKey(it.project)){
                it.projectGroup = transformMapping[it.project]
                employeeMonthlyRepository.saveAndFlush(it)
            }
            !transformMapping.containsKey(it.project)
        }
    }

    override fun fillWhoDoWhatByMonth(month: Int, year: Int): List<WhoDoWhat> {
        val label: Pair<HashMap<String, Int>?, HashMap<String, Int>?> = employeeMonthlyRepository.readingLabelRowAndCol()
        if(label.second == null || label.first == null){
            throw FileNotFoundExceptionCustom("label of ProjectGroup or Visa empty",HttpStatus.NOT_FOUND)
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
}