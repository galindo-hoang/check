package com.example.ex.repository

import com.example.ex.model.Capacity
import org.springframework.data.repository.CrudRepository


interface EmployeeCapacityRepository : CrudRepository<Capacity, String> {
    fun findCapacityByVisa(visa: String): Capacity
}