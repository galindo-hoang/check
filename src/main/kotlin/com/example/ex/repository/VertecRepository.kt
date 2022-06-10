package com.example.ex.repository

import com.example.ex.model.Vertec
import org.springframework.data.repository.CrudRepository

interface VertecRepository: CrudRepository<Vertec,Pair<String,String>>, VertecRepositoryCustom