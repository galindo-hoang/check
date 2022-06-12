package com.example.ex.service.impl

import com.example.ex.dto.VertecDto
import com.example.ex.repository.VertecRepository
import com.example.ex.service.VertecService
import com.example.ex.utils.Constant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VertecServiceImpl(
    @Autowired private val vertecRepository: VertecRepository
): VertecService {

    override fun loadVertecByMonthYear(month: Int, year: Int): MutableList<VertecDto> {
        val data = hashMapOf<String,Any>()
        var monthYear = ""
        if(month<10) monthYear += "0$month"
        else monthYear = month.toString()

        monthYear += "." + (year%100).toString()

        data[Constant.MONTH_YEAR] = monthYear
        return vertecRepository.findAllVertecByMonthYear(data)
    }

    override fun loadVertecIntoDB(month: Int, year: Int) {
    }
}