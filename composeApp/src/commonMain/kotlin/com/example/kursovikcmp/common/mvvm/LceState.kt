package com.example.kursovikcmp.common.mvvm

data class LceState(
    val isLoading: Boolean = false,
    val errorState: ErrorState? = null,
    val isRootScreen: Boolean = false,
)