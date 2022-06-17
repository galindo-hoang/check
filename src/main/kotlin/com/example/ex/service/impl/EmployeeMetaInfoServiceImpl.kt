package com.example.ex.service.impl

import com.example.ex.dto.EmployeeMetaInfoDto
import com.example.ex.mapper.MetaInfoMapperDecorator
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.repository.EmployeeMetaInfoRepository
import com.example.ex.service.EmployeeMetaInfoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class EmployeeMetaInfoServiceImpl(
    private val employeeMetaInfoRepository: EmployeeMetaInfoRepository,
    private val metaInfoMapperDecorator: MetaInfoMapperDecorator,
): EmployeeMetaInfoService {

    @PostConstruct
    private fun postConstruct(){
        employeeMetaInfoRepository.saveAll(
            this.readAllFromXLSX().map { metaInfoMapperDecorator.dtoToEntity(it) }
        )
    }

    override fun loadAllEmployee(): List<EmployeeMetaInfoDto> {
        return employeeMetaInfoRepository.findAll().map { metaInfoMapperDecorator.entityToDto(it) }
    }

    override fun loadEmployeeByVisa(visa: String): EmployeeMetaInfoDto {
        return metaInfoMapperDecorator.entityToDto(employeeMetaInfoRepository.findEmployMetaInfoByVisa(visa))
    }

    override fun saveEmployee(employee: EmployeeMetaInfo) {
        employeeMetaInfoRepository.save(employee)
    }

    override fun saveListEmployee(employees: List<EmployeeMetaInfo>) {
        employeeMetaInfoRepository.saveAll(employees)
    }

    override fun readAllFromXLSX(): List<EmployeeMetaInfoDto> {
        return employeeMetaInfoRepository.findAllFromXLSX()
    }
}