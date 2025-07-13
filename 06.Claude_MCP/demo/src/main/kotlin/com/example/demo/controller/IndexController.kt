package com.example.demo.controller

import com.example.demo.dto.UserSession
import jakarta.servlet.http.HttpSession
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/")
    fun index(session: HttpSession): String {
        val userSession = session.getAttribute("user") as? UserSession
        return if (userSession != null) {
            "redirect:/todos"
        } else {
            "redirect:/login"
        }
    }
}
