package com.example.ex.repository.filterxlsx.impl

import com.example.ex.repository.filterxlsx.CriteriaXLSX
import org.apache.poi.ss.usermodel.Cell

class CriteriaXLSXString: CriteriaXLSX {
    override fun meetCriteria(cell: Cell, criteria: Any): Boolean {
        return if(criteria is List<*>) this.meetCriteriaInList(cell, criteria as List<Any>)
        else cell.stringCellValue == criteria.toString()
    }

    override fun meetCriteriaInList(cell: Cell, criteria: List<Any>): Boolean {
        return criteria.contains(cell.stringCellValue)
    }
}