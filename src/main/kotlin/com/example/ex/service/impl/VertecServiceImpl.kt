package com.example.ex.service.impl

import com.example.ex.dto.VertecDto
import com.example.ex.repository.VertecRepository
import com.example.ex.service.VertecService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VertecServiceImpl(
    @Autowired private val vertecRepository: VertecRepository
): VertecService {

    override fun loadVertecByMonthYear(month: Int, year: Int): MutableList<VertecDto> {
        return vertecRepository.findAllVertecByMonthYear(month,year)
    }

    override fun loadVertecIntoDB(month: Int, year: Int) {
    }
}