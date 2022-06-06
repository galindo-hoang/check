package com.example.ex

import com.example.ex.dto.EmployeeHourReportDto
import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.Capacity
import com.example.ex.model.EmployeeMetaInfo
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

    ) {

    @RequestMapping(value = ["/employeeInfo"], method = [RequestMethod.GET])
    fun viewInfo(@RequestParam("visa", required = false, defaultValue = "") visa:String): Iterable<EmployeeMetaInfoDto> {
        return if(visa == "") employeeInfoService.loadAllEmployee()
        else employeeInfoService.loadEmployeeByVisa(visa)
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
    fun test(): MutableList<EmployeeHourReportDto> {
        return employeeMonthlyService.loadEmployeeByHourReportCriteria(
            HourReportCriteriaDto(
                levels = listOf("1.2","1.4"),
                startMonth = Date.valueOf("2022-01-01"),
                endMonth = Date.valueOf("2023-01-01"),
                listOf("543123-101","18020-101","19513-101")
            )
        )
    }
}