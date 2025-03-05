package com.example.kursovikcmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.kursovikcmp.feature.News.List.Compose.NewsScreen

fun MainViewController() = ComposeUIViewController { NewsScreen() }