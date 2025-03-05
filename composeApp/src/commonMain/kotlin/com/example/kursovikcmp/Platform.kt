package com.example.kursovikcmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform