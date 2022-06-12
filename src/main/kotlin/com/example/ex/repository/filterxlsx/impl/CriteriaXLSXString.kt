package com.example.ex.repository.filterxlsx.impl

import com.example.ex.repository.filterxlsx.CriteriaXLSX
import org.apache.poi.ss.usermodel.Cell

class CriteriaXLSXString: CriteriaXLSX {
    override fun meetCriteria(cell: Cell, criteria: Any): Boolean {
        val a = cell.stringCellValue == criteria.toString()
        print(a)
        return a
    }
}