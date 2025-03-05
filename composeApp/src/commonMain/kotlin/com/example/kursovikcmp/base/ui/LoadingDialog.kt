package com.example.kursovikcmp.base.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.kursovikcmp.MR
import com.example.kursovikcmp.common.extensions.color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingDialog() {
    LocalFocusManager.current.clearFocus()
    Dialog(
        properties = DialogProperties(
            dismissOnClickOutside = false,
            dismissOnBackPress = false,
            usePlatformDefaultWidth = false,
        ),
        onDismissRequest = {}
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(64.dp),
                strokeWidth = 6.dp,
                color = MR.colors.red.color(),
                backgroundColor = MR.colors.loader_background.color().copy(alpha = 0.2f),
            )
        }
    }
}

@Preview
@Composable
private fun PreviewLoadingDialog() {
    MaterialTheme {
        LoadingDialog()
    }
}