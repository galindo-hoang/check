package com.example.ex.repository

import com.example.ex.model.Capacity
import com.example.ex.model.EmployMetaInfo
import com.example.ex.model.EmployRole
import com.example.ex.model.EmployeeMonthly
import org.springframework.data.repository.CrudRepository

interface EmployMetaInfoRepository : CrudRepository<EmployMetaInfo, String>
interface EmployRoleRepository : CrudRepository<EmployRole, Long>
interface EmployeeMonthlyRepository : CrudRepository<EmployeeMonthly, Long>
interface EmployeeCapacityRepository : CrudRepository<Capacity, Long>