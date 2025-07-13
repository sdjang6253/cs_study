package com.example.demo.controller

import com.example.demo.dto.UserSession
import com.example.demo.entity.Todo
import com.example.demo.service.TodoService
import com.example.demo.service.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping("/todos")
class TodoController(
    private val todoService: TodoService,
    private val userService: UserService
) {

    // 세션에서 현재 사용자 가져오기
    private fun getCurrentUser(session: HttpSession): Pair<Boolean, Any> {
        val userSession = session.getAttribute("user") as? UserSession
        return if (userSession != null) {
            val user = userService.findById(userSession.id)
            if (user != null) {
                Pair(true, user)
            } else {
                Pair(false, "redirect:/login")
            }
        } else {
            Pair(false, "redirect:/login")
        }
    }

    // Todo 목록 조회
    @GetMapping
    fun listTodos(model: Model, session: HttpSession): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        val todos = todoService.getAllTodosByUser(user)
        
        model.addAttribute("todos", todos)
        model.addAttribute("currentUser", user)
        return "todo/list"
    }

    // Todo 상세 조회
    @GetMapping("/{id}")
    fun detailTodo(@PathVariable id: Long, model: Model, session: HttpSession): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        val todo = todoService.getTodoByIdAndUser(id, user)
            ?: throw RuntimeException("Todo not found or access denied")
        
        model.addAttribute("todo", todo)
        model.addAttribute("currentUser", user)
        return "todo/detail"
    }

    // Todo 등록 폼 페이지
    @GetMapping("/create")
    fun createTodoForm(model: Model, session: HttpSession): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        model.addAttribute("todo", Todo())
        model.addAttribute("currentUser", user)
        return "todo/create"
    }

    // Todo 등록 처리
    @PostMapping("/create")
    fun createTodo(
        @ModelAttribute todo: Todo, 
        redirectAttributes: RedirectAttributes,
        session: HttpSession
    ): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        todoService.saveTodo(todo, user)
        redirectAttributes.addFlashAttribute("message", "Todo가 성공적으로 등록되었습니다.")
        return "redirect:/todos"
    }

    // Todo 수정 폼 페이지
    @GetMapping("/{id}/edit")
    fun editTodoForm(@PathVariable id: Long, model: Model, session: HttpSession): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        val todo = todoService.getTodoByIdAndUser(id, user)
            ?: throw RuntimeException("Todo not found or access denied")
        
        model.addAttribute("todo", todo)
        model.addAttribute("currentUser", user)
        return "todo/edit"
    }

    // Todo 수정 처리
    @PostMapping("/{id}/edit")
    fun editTodo(
        @PathVariable id: Long, 
        @ModelAttribute todo: Todo, 
        redirectAttributes: RedirectAttributes,
        session: HttpSession
    ): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        todoService.updateTodo(id, todo, user)
        redirectAttributes.addFlashAttribute("message", "Todo가 성공적으로 수정되었습니다.")
        return "redirect:/todos"
    }

    // Todo 완료 상태 토글
    @PostMapping("/{id}/toggle")
    fun toggleTodo(@PathVariable id: Long, session: HttpSession): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        todoService.toggleTodo(id, user)
        return "redirect:/todos"
    }

    // Todo 삭제 처리
    @PostMapping("/{id}/delete")
    fun deleteTodo(
        @PathVariable id: Long, 
        redirectAttributes: RedirectAttributes,
        session: HttpSession
    ): String {
        val (isValid, result) = getCurrentUser(session)
        if (!isValid) return result as String
        
        val user = result as com.example.demo.entity.User
        todoService.deleteTodo(id, user)
        redirectAttributes.addFlashAttribute("message", "Todo가 성공적으로 삭제되었습니다.")
        return "redirect:/todos"
    }
}
