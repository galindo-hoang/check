package com.example.ex.repository

import com.example.ex.model.Capacity
import org.springframework.data.jpa.repository.JpaRepository


interface EmployeeCapacityRepository : JpaRepository<Capacity, Int>, EmployeeCapacityRepositoryCustom {
    fun findCapacityByVisa(visa: String): Capacity
}