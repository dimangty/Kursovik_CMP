package com.example.kursovikcmp.base

interface UiEvent

sealed class DefaultUiEvent : UiEvent {
    data object OnScreenCreated : DefaultUiEvent()
    data object OnScreenResumed : DefaultUiEvent()
    data object OnScreenDestroyed : DefaultUiEvent()
    data object OnBackClicked : DefaultUiEvent()
}