package com.example.ex.repository.filterxlsx.impl

import com.example.ex.repository.filterxlsx.CriteriaXLSX
import java.util.*

class CriteriaXLSXDate: CriteriaXLSX {
    override fun meetCriteriaDate(startDate: Date, endDate: Date?, criteria: Date): Boolean {
        return (startDate < criteria && (endDate == null || endDate > criteria))
    }
}