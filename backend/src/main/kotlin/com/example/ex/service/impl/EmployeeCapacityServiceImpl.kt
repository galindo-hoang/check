package com.example.ex.service.impl

import com.example.ex.model.Capacity
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.service.EmployeeCapacityService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class EmployeeCapacityServiceImpl(
    @Autowired private val employeeCapacityRepository: EmployeeCapacityRepository,
): EmployeeCapacityService {
    override fun loadAllEmployee(): MutableIterable<Capacity> {
        return employeeCapacityRepository.findAll()
    }

    override fun loadEmployeeByVisa(visa: String): Capacity {
        return employeeCapacityRepository.findCapacityByVisa(visa)
    }
    override fun saveEmployee(employeeCapacity: Capacity) {
        employeeCapacityRepository.save(employeeCapacity)
    }
}