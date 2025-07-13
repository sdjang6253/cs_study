package com.example.demo.controller

import com.example.demo.dto.LoginRequest
import com.example.demo.dto.SignupRequest
import com.example.demo.dto.UserSession
import com.example.demo.service.UserService
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class UserController(
    private val userService: UserService
) {

    // 회원가입 폼 페이지
    @GetMapping("/signup")
    fun signupForm(model: Model): String {
        model.addAttribute("signupRequest", SignupRequest())
        return "auth/signup"
    }

    // 회원가입 처리
    @PostMapping("/signup")
    fun signup(
        @ModelAttribute signupRequest: SignupRequest,
        redirectAttributes: RedirectAttributes,
        model: Model
    ): String {
        val result = userService.signup(signupRequest)
        
        return if (result.isSuccess) {
            redirectAttributes.addFlashAttribute("message", "회원가입이 완료되었습니다. 로그인해주세요.")
            "redirect:/login"
        } else {
            model.addAttribute("error", result.exceptionOrNull()?.message)
            model.addAttribute("signupRequest", signupRequest)
            "auth/signup"
        }
    }

    // 로그인 폼 페이지
    @GetMapping("/login")
    fun loginForm(model: Model): String {
        model.addAttribute("loginRequest", LoginRequest())
        return "auth/login"
    }

    // 로그인 처리
    @PostMapping("/login")
    fun login(
        @ModelAttribute loginRequest: LoginRequest,
        session: HttpSession,
        redirectAttributes: RedirectAttributes,
        model: Model
    ): String {
        val result = userService.login(loginRequest)
        
        return if (result.isSuccess) {
            val userSession = result.getOrNull()!!
            session.setAttribute("user", userSession)
            redirectAttributes.addFlashAttribute("message", "${userSession.nickname}님, 환영합니다!")
            "redirect:/todos"
        } else {
            model.addAttribute("error", result.exceptionOrNull()?.message)
            model.addAttribute("loginRequest", loginRequest)
            "auth/login"
        }
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    fun logout(session: HttpSession): String {
        session.invalidate()
        return "redirect:/login"
    }
}
