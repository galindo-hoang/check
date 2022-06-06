package com.example.ex.service.impl

import com.example.ex.model.EmployeeRole
import com.example.ex.model.QEmployeeRole
import com.example.ex.repository.EmployRoleRepository
import com.example.ex.service.EmployeeRoleService
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

@Service
class EmployeeRoleServiceImpl(@Autowired private val employeeRoleRepository: EmployRoleRepository):
    EmployeeRoleService {
    @PersistenceContext
    private lateinit var entityManager: EntityManager
    private val employeeRole = QEmployeeRole.employeeRole
    override fun loadAllEmployee(): MutableIterable<EmployeeRole> {
        val result = employeeRoleRepository.findAll()
        return result
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
}