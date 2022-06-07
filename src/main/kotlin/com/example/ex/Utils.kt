package com.example.ex

import com.example.ex.model.QEmployeeMetaInfo
import com.example.ex.model.QEmployeeMonthly
import com.example.ex.model.QEmployeeRole
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

object Utils {

    fun getJpaQuery(entityManager: EntityManager): JPAQueryFactory {
        return JPAQueryFactory(entityManager)
    }
}