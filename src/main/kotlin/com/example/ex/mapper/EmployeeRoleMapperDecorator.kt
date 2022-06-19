package com.example.ex.mapper

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.model.EmployeeMetaInfo
import com.example.ex.model.EmployeeRole
import org.mapstruct.Mapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext


abstract class EmployeeRoleMapperDecorator: EmployeeRoleMapper {
    @Autowired private lateinit var delegate: EmployeeRoleMapper
    @PersistenceContext private lateinit var entityManager: EntityManager

    @Transactional
    override fun dtoToEntity(employeeRoleDto: EmployeeRoleDto): EmployeeRole {
        val result = delegate.dtoToEntity(employeeRoleDto)
        result.abbreviation = entityManager.getReference(EmployeeMetaInfo::class.java,employeeRoleDto.abbreviationss)
        result.supervisor = entityManager.getReference(EmployeeMetaInfo::class.java,employeeRoleDto.supervisorss)
        entityManager.persist(result)
        return result
    }


}