package com.example.ex.repository.filterxlsx

import org.apache.poi.ss.usermodel.Cell

interface CriteriaXLSX {
    fun meetCriteria(cell: Cell, criteria: Any): Boolean
}