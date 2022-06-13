package com.example.ex.service.impl

import com.example.ex.dto.VertecDto
import com.example.ex.repository.EmployeeCapacityRepository
import com.example.ex.repository.VertecRepository
import com.example.ex.service.VertecService
import com.example.ex.utils.Constant
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VertecServiceImpl(
    @Autowired private val vertecRepository: VertecRepository,
    @Autowired private val capacityRepository: EmployeeCapacityRepository
): VertecService {

    override fun loadVertecByMonthYear(month: Int, year: Int): MutableList<VertecDto> {
        val dataCriteria = hashMapOf<String,Any>()
        var monthYear = ""
        if(month<10) monthYear += "0$month"
        else monthYear = month.toString()
        monthYear += "." + (year%100).toString()


        dataCriteria[Constant.MONTH_YEAR] = monthYear
        dataCriteria[Constant.VISA] = capacityRepository.findVisaByMonth(month)
        return vertecRepository.findAllVertecByMonthYear(dataCriteria)
    }

    override fun loadVertecIntoDB(month: Int, year: Int) {
    }
}