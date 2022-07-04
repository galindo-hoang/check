package com.example.ex.controller

import com.example.ex.dto.*
import com.example.ex.exception.ErrorMessage
import com.example.ex.exception.FileNotFoundExceptionCustom
import com.example.ex.mapper.EmployeeMapper
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.model.Capacity
import com.example.ex.model.EmployeeRole
import com.example.ex.model.EmployeeMonthly
import com.example.ex.service.*
import com.sun.jdi.request.ExceptionRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.sql.Date

@RestController
@CrossOrigin
class HtmlController(
    @Autowired private val employeeMonthlyVertecService: EmployeeMonthlyVertecService,
    @Autowired private val employeeMapper: EmployeeMapper,
) {

//    @RequestMapping(value = ["/test"], method = [RequestMethod.GET])
//    fun test(
//        @RequestParam("levels", required = false, defaultValue = "") levels: String,
//        @RequestParam("start", required = false, defaultValue = "2022-1-1") start: String,
//        @RequestParam("end", required = false, defaultValue = "2023-1-1") end: String,
//        @RequestParam("projects", required = false, defaultValue = "") projects: String,
//
//    ): MutableList<EmployeeHourReportDto> {
//        val result = mutableListOf<EmployeeHourReportDto>()
//        val hourReportCriteriaDto = HourReportCriteriaDto()
//        if(levels.trim().trimStart() != "" && levels.trim().trimStart().contains(".")){
//            hourReportCriteriaDto.levels = levels.trim().trimStart().split(" ")
//        }
//        if(projects.trim().trimStart() != "" && projects.trim().trimStart().contains("-")){
//            hourReportCriteriaDto.projectCodes = projects.trim().trimStart().split(" ")
//        }
//        hourReportCriteriaDto.startMonth = Date.valueOf(start)
//        hourReportCriteriaDto.endMonth = Date.valueOf(end)
//        val data = employeeMonthlyVertecService.loadEmployeeByHourReportCriteria(hourReportCriteriaDto)
//        data.forEach { (k, v) ->
//            result.add(
//                employeeMapper.entityReportHourToDto(k,v)
//            )
//        }
//        return result
//    }


    @RequestMapping(value = ["/writeExcel"], method = [RequestMethod.GET])
    fun writeXLSX(
        @RequestParam("month") month: Int,
        @RequestParam("year") year: Int,
        @RequestParam("pg", required = false, defaultValue = "") projectGroup: String,
    ): List<WhoDoWhat> {
        return employeeMonthlyVertecService.fillWhoDoWhatByMonth(month,year)
    }
}