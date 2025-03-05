package com.example.kursovikcmp.base.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.kursovikcmp.base.mvvm.DefaultUiEvent
import com.example.kursovikcmp.common.mvvm.ErrorState
import com.example.kursovikcmp.common.mvvm.LceState

@Composable
fun BaseScreen(
    lceState: LceState,
    onDefaultUiEvent: (DefaultUiEvent) -> Unit,
    content: @Composable () -> Unit) {

    DisposableEffect(Unit) {
        onDefaultUiEvent(DefaultUiEvent.OnScreenCreated)

        onDispose {
            onDefaultUiEvent(DefaultUiEvent.OnScreenDestroyed)
        }
    }

    LifecycleEventEffect(Lifecycle.Event.ON_RESUME) {
        onDefaultUiEvent(DefaultUiEvent.OnScreenResumed)
    }

    content()

    LceStateHandlerView(
        lceState = lceState
    )
}

@Composable
private fun LceStateHandlerView(
    lceState: LceState
) {
    when (val errorState = lceState.errorState) {
        null -> Unit
        is ErrorState.ApiAlertError -> {
//            MyErrorDialog(state = errorState) {
//
//            }
        }

        is ErrorState.AlertError -> {
//            MyAlertDialog(state = errorState) {
//
//            }
        }
    }

//    if (lceState.isLoading) {
//        LoadingDialog()
//    }
}