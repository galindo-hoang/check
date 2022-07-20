package com.example.ex.controller

import com.example.ex.dto.CriteriaChart
import com.example.ex.dto.EmployeeMonthlyChart
import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.service.EmployeeMonthlyVertecService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/filtering")
class FilteringController(
    @Autowired private val employeeMonthlyVertecService: EmployeeMonthlyVertecService,
    @Autowired private val employeeMonthlyMapperDecorator: EmployeeMonthlyMapperDecorator,
): BaseController() {

    @GetMapping("/loadIntoDB")
    fun loadVertec(
        @RequestParam("month", required = false, defaultValue = "05") month: Int,
        @RequestParam("year", required = false, defaultValue = "22") year: Int,
    ): List<EmployeeMonthlyDto> {
        return employeeMonthlyVertecService.saveEmployeeByMonth(month,year%100)
    }

    @GetMapping("/mapping")
    fun mappingProject(): List<EmployeeMonthlyDto> {
        return employeeMonthlyVertecService.mappingProjectGroup().map { employeeMonthlyMapperDecorator.entityToDto(it) }
    }

    @GetMapping("/chart")
    fun getHourProjectDate(
        criteriaChart: CriteriaChart
    ): List<EmployeeMonthlyChart>{
        return employeeMonthlyVertecService.filterCriteriaChart(criteriaChart)
    }
    @GetMapping("/projects")
    fun getProjectGroup(): List<String> = employeeMonthlyVertecService.getListProjectGroup()
}