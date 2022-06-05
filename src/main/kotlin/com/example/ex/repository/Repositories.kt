package com.example.ex.repository

import com.example.ex.model.Capacity
import com.example.ex.model.EmployMetaInfo
import com.example.ex.model.EmployRole
import com.example.ex.model.EmployeeMonthly
import org.springframework.data.repository.CrudRepository

interface EmployMetaInfoRepository : CrudRepository<EmployMetaInfo, String>{
    fun findEmployMetaInfoByVisa(visa: String): Iterable<EmployMetaInfo>
}
interface EmployRoleRepository : CrudRepository<EmployRole, Long>{
    fun findEmployRolesByAbbreviation(visa: String): Iterable<EmployRole>
    fun findEmployRolesBySupervisor(visa: String): Iterable<EmployRole>
}
interface EmployeeMonthlyRepository : CrudRepository<EmployeeMonthly, String>{
    fun findEmployeeMonthliesByMetaInfo(visa: String): Iterable<EmployeeMonthly>
}
interface EmployeeCapacityRepository : CrudRepository<Capacity, String>{
    fun findCapacityByVisa(visa: String): Capacity
}