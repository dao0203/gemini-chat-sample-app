package com.example.geminiapp.domain

data class Chat(
    val id: String,
    val name: String,
    val message: String,
    val isUser: Boolean,
)
