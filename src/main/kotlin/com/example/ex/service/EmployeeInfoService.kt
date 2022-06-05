package com.example.ex.service

import com.example.ex.model.EmployMetaInfo

interface EmployeeInfoService {
    fun loadAllEmployee(): MutableIterable<EmployMetaInfo>
    fun loadEmployeeByVisa(visa: String): MutableIterable<EmployMetaInfo>
    fun saveEmployee(employee: EmployMetaInfo)
}