package com.example.ex.service.impl

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.mapper.EmployeeMapper
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.repository.EmployeeMetaInfoRepository
import com.example.ex.service.EmployeeInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeInfoServiceImpl(
    @Autowired private val employeeMetaInfoRepository: EmployeeMetaInfoRepository,
    @Autowired private val employeeMapper: EmployeeMapper,
): EmployeeInfoService {

    override fun loadAllEmployee(): List<EmployeeMetaInfoDto> {
        return employeeMetaInfoRepository.findAll().map { employeeMapper.entityToDto(it) }
    }

    override fun loadEmployeeByVisa(visa: String): EmployeeMetaInfoDto {
        return employeeMapper.entityToDto(employeeMetaInfoRepository.findEmployMetaInfoByVisa(visa))
    }

    override fun saveEmployee(employee: EmployeeMetaInfo) {
        employeeMetaInfoRepository.save(employee)
    }
}