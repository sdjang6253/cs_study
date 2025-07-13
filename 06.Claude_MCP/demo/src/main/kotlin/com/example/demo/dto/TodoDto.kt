package com.example.demo.dto

import com.example.demo.entity.Todo
import com.fasterxml.jackson.annotation.JsonProperty
// import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

// @Schema(description = "Todo 생성 요청")
data class CreateTodoRequest(
    @field:NotBlank(message = "Title is required")
    // @Schema(description = "Todo 제목", example = "아침일찍 일어나기", required = true)
    val title: String,
    
    // @Schema(description = "Todo 설명", example = "주중은 오전 7시, 주말은 오전 9시")
    val description: String? = null
)

// @Schema(description = "Todo 수정 요청")
data class UpdateTodoRequest(
    @field:NotBlank(message = "Title is required")
    // @Schema(description = "Todo 제목", example = "아침일찍 일어나기", required = true)
    val title: String,
    
    // @Schema(description = "Todo 설명", example = "주중은 오전 7시, 주말은 오전 9시")
    val description: String? = null,
    
    // @Schema(description = "완료 여부", example = "true")
    @JsonProperty("isDone")
    val isDone: Boolean = false
)

// @Schema(description = "Todo 응답")
data class TodoResponse(
    // @Schema(description = "Todo ID", example = "1")
    val id: Long,
    
    // @Schema(description = "Todo 제목", example = "아침일찍 일어나기")
    val title: String,
    
    // @Schema(description = "Todo 설명", example = "주중은 오전 7시, 주말은 오전 9시")
    val description: String?,
    
    // @Schema(description = "완료 여부", example = "true")
    @JsonProperty("isDone")
    val isDone: Boolean
) {
    companion object {
        fun from(todo: Todo): TodoResponse {
            return TodoResponse(
                id = todo.id!!,
                title = todo.title,
                description = todo.description,
                isDone = todo.isDone  // completed 프로퍼티 사용
            )
        }
    }
}
