package com.example.ex.service.impl

import com.example.ex.dto.EmployeeRoleDto
import com.example.ex.mapper.EmployeeRoleMapper
import com.example.ex.model.EmployeeRole
import com.example.ex.model.QEmployeeRole.Companion.employeeRole
import com.example.ex.repository.EmployeeRoleRepository
import com.example.ex.service.EmployeeRoleService
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class EmployeeRoleServiceImpl(
    private val employeeRoleRepository: EmployeeRoleRepository,
    private val employeeRoleMapper: EmployeeRoleMapper,
): EmployeeRoleService {
    @PersistenceContext
    private lateinit var entityManager: EntityManager


    @PostConstruct
    private fun postConstruct(){
        employeeRoleRepository.saveAll(
            this.readAllFromXLSX().map { employeeRoleMapper.dtoToEntity(it) }
        )
    }

    override fun loadAllEmployee(): MutableIterable<EmployeeRole> {
        return employeeRoleRepository.findAll()
    }

    override fun loadEmployeeBySupervisor(visa: String): Iterable<EmployeeRole> {
        val jpaQuery = JPAQueryFactory(entityManager)
        return jpaQuery
            .from(employeeRole)
            .where(employeeRole.supervisor.visa.eq(visa))
            .fetch() as Iterable<EmployeeRole>
    }

    override fun loadEmployeeByAbbreviation(visa: String): Iterable<EmployeeRole> {
        val jpaQuery = JPAQueryFactory(entityManager)
        return jpaQuery
            .from(employeeRole)
            .where(employeeRole.abbreviation.visa.eq(visa))
            .fetch() as Iterable<EmployeeRole>
    }

    override fun saveEmployee(employeeRole: EmployeeRole) {
        employeeRoleRepository.save(employeeRole)
    }

    override fun saveListEmployee(list: List<EmployeeRole>) {
        employeeRoleRepository.saveAll(list)
    }

    override fun readAllFromXLSX(): List<EmployeeRoleDto> {
        return employeeRoleRepository.findAllFromXLSX()
    }
}