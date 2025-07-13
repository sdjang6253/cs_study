package com.example.demo.entity

import jakarta.persistence.*

@Entity
@Table(name = "todos")
data class Todo(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    
    @Column(nullable = false)
    var title: String = "",
    
    @Column(length = 1000)
    var description: String? = null,
    
    @Column(name = "is_done")
    var isDone: Boolean = false,
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null
)
