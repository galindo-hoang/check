package com.example.ex.controller

import com.example.ex.dto.EmployeeMonthlyDto
import com.example.ex.exception.ErrorMessage
import com.example.ex.exception.FileNotFoundExceptionCustom
import com.example.ex.mapper.EmployeeMonthlyMapperDecorator
import com.example.ex.service.EmployeeMonthlyVertecService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/filtering")
class FilteringController(
    @Autowired private val employeeMonthlyVertecService: EmployeeMonthlyVertecService,
    @Autowired private val employeeMonthlyMapperDecorator: EmployeeMonthlyMapperDecorator,
): BaseController() {

    @RequestMapping(value = ["/loadIntoDB"], method = [RequestMethod.GET])
    fun loadVertec(
        @RequestParam("month", required = false, defaultValue = "05") month: Int,
        @RequestParam("year", required = false, defaultValue = "22") year: Int,
    ): List<EmployeeMonthlyDto> {
        return employeeMonthlyVertecService.saveEmployeeByMonth(month,year%100)
    }

    @GetMapping(value = ["/mapping"])
    fun mappingProject(): List<EmployeeMonthlyDto> {
        return employeeMonthlyVertecService.mappingProjectGroup().map { employeeMonthlyMapperDecorator.entityToDto(it) }
    }
}