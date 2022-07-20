package com.example.ex.service

import com.example.ex.model.Capacity


interface EmployeeCapacityService {
    fun loadAllEmployee(): MutableIterable<Capacity>
    fun loadEmployeeByVisa(visa: String): Capacity
    fun saveEmployee(employeeCapacity: Capacity)
}