package com.example.ex.service.impl

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.mapper.EmployeeMapper
import com.example.ex.mapper.EmployeeMetaInfoMapper
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.repository.EmployMetaInfoRepository
import com.example.ex.service.EmployeeInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EmployeeInfoServiceImpl(
    @Autowired private val employMetaInfoRepository: EmployMetaInfoRepository,
    @Autowired private val employeeMapper: EmployeeMapper,
): EmployeeInfoService {

    override fun loadAllEmployee(): List<EmployeeMetaInfoDto> {
        return employMetaInfoRepository.findAll().map { employeeMapper.entityToDto(it) }
    }

    override fun loadEmployeeByVisa(visa: String): List<EmployeeMetaInfoDto> {
        return employMetaInfoRepository.findEmployMetaInfoByVisa(visa)
            .map { employeeMapper.entityToDto(it) }
    }

    override fun saveEmployee(employee: EmployeeMetaInfo) {
        employMetaInfoRepository.save(employee)
    }
}