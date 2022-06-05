package com.example.ex

import com.example.ex.model.Capacity
import com.example.ex.model.EmployMetaInfo
import com.example.ex.model.EmployRole
import com.example.ex.model.EmployeeMonthly
import com.example.ex.repository.EmployMetaInfoRepository
import com.example.ex.repository.EmployRoleRepository
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.service.*
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.io.FileReader
import javax.annotation.PostConstruct

@RestController
@CrossOrigin
class HtmlController(
    @Autowired private val employeeInfoService: EmployeeInfoService,
    @Autowired private val employeeRoleService: EmployeeRoleService,
    @Autowired private val employeeMonthlyService: EmployeeMonthlyService,
    @Autowired private val employeeCapacityService: EmployeeCapacityService,

    ) {

    @RequestMapping(value = ["/employeeInfo"], method = [RequestMethod.GET])
    fun viewInfo(@RequestParam("visa", required = false, defaultValue = "") visa:String): MutableIterable<EmployMetaInfo>{
        return if(visa == "") employeeInfoService.loadAllEmployee()
        else employeeInfoService.loadEmployeeByVisa(visa)
    }
    @RequestMapping(value = ["/employeeRole"], method = [RequestMethod.GET])
    fun viewRole(): MutableIterable<EmployRole> = employeeRoleService.loadAllEmployee()
    @RequestMapping(value = ["/employeeCapacity"], method = [RequestMethod.GET])
    fun viewCapacity(): MutableIterable<Capacity> = employeeCapacityService.loadAllEmployee()
    @RequestMapping(value = ["/employeeMonthly"], method = [RequestMethod.GET])
    fun viewMonthly(): MutableIterable<EmployeeMonthly> = employeeMonthlyService.loadAllEmployee()
}