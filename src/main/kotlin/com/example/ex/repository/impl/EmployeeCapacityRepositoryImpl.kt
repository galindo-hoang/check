package com.example.ex.repository.impl

import com.example.ex.model.QCapacity.Companion.capacity
import com.example.ex.repository.EmployeeCapacityRepositoryCustom
import com.example.ex.utils.Constant
import com.example.ex.utils.Constant.getJpaQuery
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class EmployeeCapacityRepositoryImpl(
    @PersistenceContext private val entityManager: EntityManager
): EmployeeCapacityRepositoryCustom {
    override fun findVisaByMonth(month: Int): List<String> {
        val query = getJpaQuery(entityManager).from(capacity)
        when(month){
            1 -> query.where(capacity.month.jan.eq(1))
            2 -> query.where(capacity.month.feb.eq(1))
            3 -> query.where(capacity.month.mar.eq(1))
            4 -> query.where(capacity.month.apr.eq(1))
            5 -> query.where(capacity.month.mar.eq(1))
            6 -> query.where(capacity.month.jun.eq(1))
            7 -> query.where(capacity.month.jul.eq(1))
            8 -> query.where(capacity.month.aug.eq(1))
            9 -> query.where(capacity.month.sep.eq(1))
            10 -> query.where(capacity.month.oct.eq(1))
            11 -> query.where(capacity.month.nov.eq(1))
            12 -> query.where(capacity.month.dec.eq(1))
        }
        query.select(capacity.visa)
        return query.fetch().toList() as List<String>
    }
}