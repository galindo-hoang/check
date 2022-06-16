package com.example.ex.repository

import com.example.ex.dto.ProjectMappingDto
import org.springframework.stereotype.Repository

@Repository
interface ProjectMappingRepositoryCustom {
    fun fetchAll(): List<ProjectMappingDto>
}