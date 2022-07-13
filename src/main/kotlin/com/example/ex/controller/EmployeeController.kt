package com.example.ex.controller

import com.example.ex.model.Capacity
import com.example.ex.model.EmployeeMonthly
import com.example.ex.model.EmployeeRole
import com.example.ex.service.EmployeeCapacityService
import com.example.ex.service.EmployeeMetaInfoService
import com.example.ex.service.EmployeeMonthlyVertecService
import com.example.ex.service.EmployeeRoleService

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/employee")
class EmployeeController(
    @Autowired private val employeeMetaInfoService: EmployeeMetaInfoService,
    @Autowired private val employeeRoleService: EmployeeRoleService,
    @Autowired private val employeeCapacityService: EmployeeCapacityService,
    @Autowired private val employeeMonthlyVertecService: EmployeeMonthlyVertecService,
): BaseController() {
    @GetMapping("/Info")
    fun viewInfo(@RequestParam("visa", required = false, defaultValue = "") visa:String): Any {
        return if(visa == "") employeeMetaInfoService.loadAllEmployee()
        else employeeMetaInfoService.loadEmployeeByVisa(visa)
    }

    @GetMapping("/Role")
    fun viewRole(@RequestParam("supervisors", required = false, defaultValue = "") supervisors:String): Iterable<EmployeeRole> {
        return if(supervisors == "") employeeRoleService.loadAllEmployee()
        else employeeRoleService.loadEmployeeBySupervisor(supervisors)
    }
    @GetMapping("/Capacity")
    fun viewCapacity(): MutableIterable<Capacity> = employeeCapacityService.loadAllEmployee()

    @GetMapping("/Monthly")
    fun viewMonthly(): MutableIterable<EmployeeMonthly> = employeeMonthlyVertecService.loadAllEmployee()
}