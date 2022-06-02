package com.example.ex.repository

import com.example.ex.model.EmployMetaInfo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface EmRepository : CrudRepository<EmployMetaInfo, String> {}

//interface UserRepository : CrudRepository<User, Long> {
//    fun findByLogin(login: String): User?
//}