package com.example.demo.config

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        ex: RuntimeException,
        redirectAttributes: RedirectAttributes
    ): String {
        redirectAttributes.addFlashAttribute("error", ex.message ?: "알 수 없는 오류가 발생했습니다.")
        return "redirect:/todos"
    }
}
