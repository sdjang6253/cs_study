package com.example.demo.controller

import com.example.demo.dto.CreateTodoRequest
import com.example.demo.dto.TodoResponse
import com.example.demo.dto.UpdateTodoRequest
import com.example.demo.service.TodoApiService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/todos")
class TodoApiController(
    private val todoApiService: TodoApiService
) {

    @GetMapping
    fun getAllTodos(): List<TodoResponse> {
        return todoApiService.getAllTodos()
    }

    @GetMapping("/{id}")
    fun getTodoById(@PathVariable id: Long): ResponseEntity<TodoResponse> {
        return try {
            val todo = todoApiService.getTodoById(id)
            ResponseEntity.ok(todo)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createTodo(@Valid @RequestBody request: CreateTodoRequest): ResponseEntity<TodoResponse> {
        val todo = todoApiService.createTodo(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(todo)
    }

    @PutMapping("/{id}")
    fun updateTodo(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return try {
            val todo = todoApiService.updateTodo(id, request)
            ResponseEntity.ok(todo)
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteTodo(@PathVariable id: Long): ResponseEntity<Void> {
        return try {
            todoApiService.deleteTodo(id)
            ResponseEntity.noContent().build()
        } catch (e: NoSuchElementException) {
            ResponseEntity.notFound().build()
        }
    }
}
