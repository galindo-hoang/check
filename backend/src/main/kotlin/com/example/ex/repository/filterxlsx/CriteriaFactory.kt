package com.example.ex.repository.filterxlsx

import com.example.ex.repository.filterxlsx.impl.CriteriaXLSXInt
import com.example.ex.repository.filterxlsx.impl.CriteriaXLSXString
import com.example.ex.utils.Constant

class CriteriaFactory {
    companion object{
        fun getCriteria(type: String): CriteriaXLSX{
            if(type == Constant.MONTH_YEAR || type == Constant.VISA) return CriteriaXLSXString()
            return CriteriaXLSXInt()
        }
    }
}