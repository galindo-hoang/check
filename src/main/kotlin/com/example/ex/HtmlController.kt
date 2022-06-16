package com.example.ex

import com.example.ex.dto.*
import com.example.ex.mapper.EmployeeMapper
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.model.Capacity
import com.example.ex.model.EmployeeRole
import com.example.ex.model.EmployeeMonthly
import com.example.ex.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

@RestController
@CrossOrigin
class HtmlController(
    @Autowired private val employeeInfoService: EmployeeInfoService,
    @Autowired private val employeeRoleService: EmployeeRoleService,
    @Autowired private val employeeMonthlyService: EmployeeMonthlyService,
    @Autowired private val employeeCapacityService: EmployeeCapacityService,
    @Autowired private val employeeMapper: EmployeeMapper,
    @Autowired private val employeeMonthlyMapperDecorator: EmployeeMonthlyMapperDecorator,


) {

    @RequestMapping(value = ["/employeeInfo"], method = [RequestMethod.GET])
    fun viewInfo(@RequestParam("visa", required = false, defaultValue = " ") visa:String): EmployeeMetaInfoDto {
        return employeeInfoService.loadEmployeeByVisa(visa)
    }
    @RequestMapping(value = ["/employeeRole"], method = [RequestMethod.GET])
    fun viewRole(@RequestParam("supervisors", required = false, defaultValue = "") supervisors:String): Iterable<EmployeeRole> {
        return if(supervisors == "") employeeRoleService.loadAllEmployee()
        else employeeRoleService.loadEmployeeBySupervisor(supervisors)
    }
    @RequestMapping(value = ["/employeeCapacity"], method = [RequestMethod.GET])
    fun viewCapacity(): MutableIterable<Capacity> = employeeCapacityService.loadAllEmployee()
    @RequestMapping(value = ["/employeeMonthly"], method = [RequestMethod.GET])
    fun viewMonthly(): MutableIterable<EmployeeMonthly> = employeeMonthlyService.loadAllEmployee()

    @RequestMapping(value = ["/test"], method = [RequestMethod.GET])
    fun test(
        @RequestParam("levels", required = false, defaultValue = "") levels: String,
        @RequestParam("start", required = false, defaultValue = "2022-1-1") start: String,
        @RequestParam("end", required = false, defaultValue = "2023-1-1") end: String,
        @RequestParam("projects", required = false, defaultValue = "") projects: String,

    ): MutableList<EmployeeHourReportDto> {
        val result = mutableListOf<EmployeeHourReportDto>()
        val hourReportCriteriaDto = HourReportCriteriaDto()
        if(levels.trim().trimStart() != "" && levels.trim().trimStart().contains(".")){
            hourReportCriteriaDto.levels = levels.trim().trimStart().split(" ")
        }
        if(projects.trim().trimStart() != "" && projects.trim().trimStart().contains("-")){
            hourReportCriteriaDto.projectCodes = projects.trim().trimStart().split(" ")
        }
        hourReportCriteriaDto.startMonth = Date.valueOf(start)
        hourReportCriteriaDto.endMonth = Date.valueOf(end)
        val data = employeeMonthlyService.loadEmployeeByHourReportCriteria(hourReportCriteriaDto)
        data.forEach { (k, v) ->
            result.add(
                employeeMapper.entityReportHourToDto(k,v)
            )
        }
        println(hourReportCriteriaDto.levels)
        println(hourReportCriteriaDto.projectCodes)
        println(result.size)
        return result
    }


    @RequestMapping(value = ["/loadVertecIntoDB"], method = [RequestMethod.GET])
    fun loadVertec(
        @RequestParam("month", required = false, defaultValue = "05") month: Int,
        @RequestParam("year", required = false, defaultValue = "22") year: Int,
    ): List<EmployeeMonthlyDto> {
        employeeMonthlyService.saveEmployeeByMonth(month)
        return listOf()
    }


    @RequestMapping(value = ["/mapping"], method = [RequestMethod.GET])
    fun mappingProject(): List<EmployeeMonthlyDto> {
        return employeeMonthlyService.mappingProjectGroup().map { employeeMonthlyMapperDecorator.entityToDto(it) }
    }
}