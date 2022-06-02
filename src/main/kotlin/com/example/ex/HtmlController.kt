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
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.io.FileReader
import javax.annotation.PostConstruct

@RestController
class HtmlController(
    @Autowired private val employMetaInfoRepository: EmployMetaInfoRepository,
    @Autowired private val employRoleRepository: EmployRoleRepository,
    @Autowired private val employeeMonthlyRepository: EmployeeMonthlyRepository,
    @Autowired private val employeeCapacityRepository: EmployeeCapacityRepository,

    ) {

    @PostConstruct fun getEmployeeInfo(){
        val fileName = "C:\\Users\\hoah\\Desktop\\Ex\\Ex\\EmployeeMetaInfo-sample.csv"
        val beans: MutableList<EmployMetaInfo?>? = CsvToBeanBuilder<Any?>(FileReader(fileName))
            .withType(EmployMetaInfo::class.java)
            .build()
            .parse() as MutableList<EmployMetaInfo?>?
        employMetaInfoRepository.saveAll(beans!!)
    }

    @PostConstruct fun getEmployeeRole(){
        val fileName = "C:\\Users\\hoah\\Desktop\\Ex\\Ex\\EmployeeRole-sample.csv"
        val beans: MutableList<EmployRole?>? = CsvToBeanBuilder<Any?>(FileReader(fileName))
            .withType(EmployRole::class.java)
            .build()
            .parse() as MutableList<EmployRole?>?
        employRoleRepository.saveAll(beans!!)
    }

    @PostConstruct fun getEmployeeMonthly(){
        val fileName = "C:\\Users\\hoah\\Desktop\\Ex\\Ex\\EmployeeMonthlyVertec-sample.csv"
        val beans: MutableList<EmployeeMonthly?>? = CsvToBeanBuilder<Any?>(FileReader(fileName))
            .withType(EmployeeMonthly::class.java)
            .build()
            .parse() as MutableList<EmployeeMonthly?>?
        employeeMonthlyRepository.saveAll(beans!!)
    }

    @PostConstruct fun getEmployeeCapacity(){
        val fileName = "C:\\Users\\hoah\\Desktop\\Ex\\Ex\\Team Capacity-2022-sample.csv"
        val beans: MutableList<Capacity?>? = CsvToBeanBuilder<Any?>(FileReader(fileName))
            .withType(Capacity::class.java)
            .build()
            .parse() as MutableList<Capacity?>?
        employeeCapacityRepository.saveAll(beans!!)
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