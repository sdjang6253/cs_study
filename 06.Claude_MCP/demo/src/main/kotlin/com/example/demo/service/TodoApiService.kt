package com.example.demo.service

import com.example.demo.dto.CreateTodoRequest
import com.example.demo.dto.TodoResponse
import com.example.demo.dto.UpdateTodoRequest
import com.example.demo.entity.Todo
import com.example.demo.repository.TodoRepository
import org.springframework.stereotype.Service

@Service
class TodoApiService(
    private val todoRepository: TodoRepository
) {
    
    fun getAllTodos(): List<TodoResponse> {
        return todoRepository.findAll().map { TodoResponse.from(it) }
    }
    
    fun getTodoById(id: Long): TodoResponse {
        val todo = todoRepository.findById(id)
            .orElseThrow { NoSuchElementException("Todo not found with id: $id") }
        return TodoResponse.from(todo)
    }
    
    fun createTodo(request: CreateTodoRequest): TodoResponse {
        val todo = Todo(
            title = request.title,
            description = request.description,
            isDone = false
        )
        val savedTodo = todoRepository.save(todo)
        return TodoResponse.from(savedTodo)
    }
    
    fun updateTodo(id: Long, request: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findById(id)
            .orElseThrow { NoSuchElementException("Todo not found with id: $id") }
        
        todo.title = request.title
        todo.description = request.description
        todo.isDone = request.isDone
        
        val updatedTodo = todoRepository.save(todo)
        return TodoResponse.from(updatedTodo)
    }
    
    fun deleteTodo(id: Long) {
        if (!todoRepository.existsById(id)) {
            throw NoSuchElementException("Todo not found with id: $id")
        }
        todoRepository.deleteById(id)
    }
}
