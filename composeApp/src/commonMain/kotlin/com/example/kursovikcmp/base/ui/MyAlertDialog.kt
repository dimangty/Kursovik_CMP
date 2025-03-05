package com.example.kursovikcmp.base.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.kursovikcmp.common.mvvm.ErrorState
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun MyAlertDialog(
    state: ErrorState.AlertError,
    onDismissed: () -> Unit,
) {
    LocalFocusManager.current.clearFocus()
    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false,
        ),
        onDismissRequest = onDismissed,
    ) {
//        BackHandler {
//            if (state.isCancellable) {
//                onDismissed()
//            }
//        }

        val interactionSource = remember { MutableInteractionSource() }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    onClick = onDismissed,
                    interactionSource = interactionSource,
                    indication = null,
                    enabled = state.isCancellable,
                ),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .clip(AppShapes.rounded)
                    .background(Color.White)
                    .clickable(enabled = false, onClick = {})
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    VSpacer(24.dp)
                    if (state.title.isNotBlank()) {
                        Row {
                            MyText(state = state.titleState, maxLines = 3)
                        }

                        VSpacer(16.dp)
                    }
                    if (state.message.isNotBlank()) {
                        MyText(state = state.textState, maxLines = 7)
                    }
                    VSpacer(24.dp)
                    ButtonsView(state, onDismissed)
                    VSpacer(24.dp)
                }
            }
        }
    }
}

@Composable
private fun ButtonsView(
    state: ErrorState.AlertError,
    onDismissed: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier.fillMaxWidth()
    ) {
        if (state.negativeButtonText.isNotBlank()) {
            MyText(state = state.negativeState,
                modifier = Modifier.clickable(onClick = {
                    state.negativeAction()
                    onDismissed()
                })
            )
        }
        HSpacer(18.dp)
        MyText(state = state.positiveState,
            modifier = Modifier.clickable(onClick = {
                state.positiveAction()
                onDismissed()
            })
        )
        HSpacer(24.dp)
    }
}

@Preview()
@Composable
private fun PreviewAlertDialog() {
    MaterialTheme {
        MyAlertDialog(state = ErrorState.AlertError.getMock(),
            onDismissed = {})
    }
}