package com.example.ex.repository

import com.example.ex.dto.HourReportCriteriaDto
import com.example.ex.model.Capacity
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeRole
import com.example.ex.model.EmployeeMonthly
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

interface EmployMetaInfoRepository : CrudRepository<EmployeeMetaInfo, String>{
    fun findEmployMetaInfoByVisa(visa: String): Iterable<EmployeeMetaInfo>
}
interface EmployRoleRepository : CrudRepository<EmployeeRole, Long>, EmployRoleRepositoryCustom
interface EmployeeMonthlyRepository : CrudRepository<EmployeeMonthly, String>{
    fun findEmployeeMonthliesByMetaInfo(visa: String): Iterable<EmployeeMonthly>
}

interface EmployRoleRepositoryCustom {
    fun findEmployeesByHourReportCriteria(hourReportCriteria: HourReportCriteriaDto):Map<EmployeeMetaInfo,Double>

}
interface EmployeeCapacityRepository : CrudRepository<Capacity, String>{
    fun findCapacityByVisa(visa: String): Capacity
}