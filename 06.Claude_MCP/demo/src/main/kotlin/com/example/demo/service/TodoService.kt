package com.example.demo.service

import com.example.demo.entity.Todo
import com.example.demo.entity.User
import com.example.demo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoService(
    private val todoRepository: TodoRepository
) {
    
    fun getAllTodosByUser(user: User): List<Todo> {
        return todoRepository.findByUser(user)
    }
    
    fun getTodoByIdAndUser(id: Long, user: User): Todo? {
        return todoRepository.findByIdAndUser(id, user)
    }
    
    fun saveTodo(todo: Todo, user: User): Todo {
        todo.user = user
        return todoRepository.save(todo)
    }
    
    fun updateTodo(id: Long, updatedTodo: Todo, user: User): Todo {
        val existingTodo = todoRepository.findByIdAndUser(id, user)
            ?: throw RuntimeException("Todo not found or access denied")
        
        existingTodo.title = updatedTodo.title
        existingTodo.description = updatedTodo.description
        
        return todoRepository.save(existingTodo)
    }
    
    fun toggleTodo(id: Long, user: User) {
        val todo = todoRepository.findByIdAndUser(id, user)
            ?: throw RuntimeException("Todo not found or access denied")
        todo.isDone = !todo.isDone
        todoRepository.save(todo)
    }
    
    fun deleteTodo(id: Long, user: User) {
        val todo = todoRepository.findByIdAndUser(id, user)
            ?: throw RuntimeException("Todo not found or access denied")
        todoRepository.delete(todo)
    }
}
