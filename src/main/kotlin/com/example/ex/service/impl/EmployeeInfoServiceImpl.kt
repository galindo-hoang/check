package com.example.ex.service.impl

import com.example.ex.model.EmployMetaInfo
import com.example.ex.repository.EmployMetaInfoRepository
import com.example.ex.service.EmployeeInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeInfoServiceImpl(
    @Autowired private val employMetaInfoRepository: EmployMetaInfoRepository
): EmployeeInfoService {
    override fun loadAllEmployee(): MutableIterable<EmployMetaInfo> {
        val result = employMetaInfoRepository.findAll()
        return result
    }

    override fun loadEmployeeByVisa(visa: String): MutableIterable<EmployMetaInfo> {
        val result = employMetaInfoRepository.findEmployMetaInfoByVisa(visa)
        return result as MutableIterable<EmployMetaInfo>
    }

    override fun saveEmployee(employee: EmployMetaInfo) {
        employMetaInfoRepository.save(employee)
    }
}