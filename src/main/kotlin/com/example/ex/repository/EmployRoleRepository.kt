package com.example.ex.repository

import com.example.ex.model.EmployeeRole
import org.springframework.data.repository.CrudRepository

interface EmployRoleRepository : CrudRepository<EmployeeRole, Long>, EmployRoleRepositoryCustom
