package com.example.ex

import com.example.ex.model.Capacity
import com.example.ex.model.EmployMetaInfo
import com.example.ex.model.EmployRole
import com.example.ex.model.EmployeeMonthly
import com.example.ex.repository.EmployMetaInfoRepository
import com.example.ex.repository.EmployRoleRepository
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.EmployeeMonthlyRepository
import com.opencsv.bean.CsvToBeanBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.FileReader
import javax.annotation.PostConstruct

@RestController
@CrossOrigin
class HtmlController(
    @Autowired private val employMetaInfoRepository: EmployMetaInfoRepository,
    @Autowired private val employRoleRepository: EmployRoleRepository,
    @Autowired private val employeeMonthlyRepository: EmployeeMonthlyRepository,
    @Autowired private val employeeCapacityRepository: EmployeeCapacityRepository,

    ) {

    @PostConstruct
    fun insert(){
        val employMetaInfo = EmployMetaInfo()
        employMetaInfo.visa = "123"
        employMetaInfoRepository.save(employMetaInfo)
    }

    @RequestMapping(value = ["/employeeInfo"], method = [RequestMethod.GET])
    fun viewInfo(): MutableIterable<EmployMetaInfo> = employMetaInfoRepository.findAll()
    @RequestMapping(value = ["/employeeRole"], method = [RequestMethod.GET])
    fun viewRole(): MutableIterable<EmployRole> = employRoleRepository.findAll()
    @RequestMapping(value = ["/employeeCapacity"], method = [RequestMethod.GET])
    fun viewCapacity(): MutableIterable<Capacity> = employeeCapacityRepository.findAll()
    @RequestMapping(value = ["/employeeMonthly"], method = [RequestMethod.GET])
    fun viewMonthly(): MutableIterable<EmployeeMonthly> = employeeMonthlyRepository.findAll()
}