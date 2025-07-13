package com.example.demo.repository

import com.example.demo.entity.Todo
import com.example.demo.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TodoRepository : JpaRepository<Todo, Long> {
    fun findByUser(user: User): List<Todo>
    fun findByIdAndUser(id: Long, user: User): Todo?
    fun deleteByUser(user: User)
}
