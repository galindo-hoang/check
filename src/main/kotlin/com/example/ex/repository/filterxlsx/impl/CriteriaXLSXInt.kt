package com.example.ex.repository.filterxlsx.impl

import com.example.ex.repository.filterxlsx.CriteriaXLSX
import org.apache.poi.ss.usermodel.Cell

class CriteriaXLSXInt: CriteriaXLSX {
    override fun meetCriteria(cell: Cell, criteria: Any): Boolean {
        return cell.numericCellValue.toInt() == criteria.toString().toInt()
    }

}