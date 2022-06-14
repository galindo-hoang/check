package com.example.ex.repository.filterxlsx

import org.apache.poi.ss.usermodel.Cell
import java.util.Date

interface CriteriaXLSX {
    fun meetCriteria(cell: Cell, criteria: Any): Boolean =  false
    fun meetCriteriaInList(cell: Cell, criteria: List<Any>): Boolean = false

    fun meetCriteriaDate(startDate: Date, endDate: Date?, criteria: Date): Boolean = false
}