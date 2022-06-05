package com.example.ex.service.impl

import com.example.ex.model.EmployeeMonthly
import com.example.ex.repository.EmployeeMonthlyRepository
import com.example.ex.service.EmployeeMonthlyService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeMonthlyServiceImpl(@Autowired private val employeeMonthlyRepository: EmployeeMonthlyRepository):
    EmployeeMonthlyService {
    override fun loadAllEmployee(): MutableIterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findAll()
    }

    override fun loadEmployeeByVisa(visa: String): Iterable<EmployeeMonthly> {
        return employeeMonthlyRepository.findEmployeeMonthliesByMetaInfo(visa)
    }

    override fun saveEmployee(employeeMonthly: EmployeeMonthly) {
        employeeMonthlyRepository.save(employeeMonthly)
    }
}