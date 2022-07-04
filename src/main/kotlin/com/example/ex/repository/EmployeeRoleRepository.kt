package com.example.ex.repository

import com.example.ex.model.EmployeeRole
import org.springframework.data.jpa.repository.JpaRepository

interface EmployeeRoleRepository : JpaRepository<EmployeeRole, Long>, EmployeeRoleRepositoryCustom
