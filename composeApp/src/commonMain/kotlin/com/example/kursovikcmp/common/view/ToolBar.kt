package com.example.kursovikcmp.common.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.kursovikcmp.MR
import com.example.kursovikcmp.base.mvvm.DefaultUiEvent
import com.example.kursovikcmp.base.ui.MyText
import com.example.kursovikcmp.common.extensions.color
import com.example.kursovikcmp.common.mvvm.TitleBarState
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.compose.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

private val ToolbarHeight = 40.dp

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    toolbarState: TitleBarState = TitleBarState(),
) {
    ToolbarWithContent(
        onNavigateBackClicked = { toolbarState.onDefaultUiEvent(DefaultUiEvent.OnBackClicked) },
        navigateBackIcon = toolbarState.backIcon,
        modifier = modifier,
        title = toolbarState.title,
        isNavigateBackVisible = toolbarState.isNavigateBackVisible,
        contentColor = toolbarState.contentColor.color(),
    )
}

@Composable
fun ToolbarWithContent(
    onNavigateBackClicked: () -> Unit,
    title: TextState,
    contentColor: Color = MR.colors.white.color(),
    navigateBackIcon: ImageResource = MR.images.ic_titlebar_back,
    modifier: Modifier = Modifier,
    isNavigateBackVisible: Boolean = true,
    endContent: @Composable RowScope.() -> Unit = {},
) {
    Column {
        Spacer(
            Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(Color.Transparent)
        )
        Box(
            modifier = modifier
                .fillMaxWidth()
                .height(ToolbarHeight),
            contentAlignment = Alignment.CenterStart,
        ) {
            if (title.value.isNotBlank()) {
                MyText(
                    state = title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(horizontal = 50.dp)
                )
            }

            if (isNavigateBackVisible) {
                val focusManager = LocalFocusManager.current
                IconButton(
                    onClick = {
                        focusManager.clearFocus()
                        onNavigateBackClicked()
                    },
                    modifier = Modifier.padding(start = 2.dp),
                    content = {
                        Icon(
                            painter = painterResource(navigateBackIcon),
                            contentDescription = null,
                            tint = contentColor,
                        )
                    }
                )
            }

            Row(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 2.dp)
            ) {
                endContent()
            }
        }
    }
}

@Suppress("MagicNumber")
@Preview()
@Composable
private fun PreviewToolbar() {
    MaterialTheme  {
        Column {
            Toolbar(
                toolbarState = TitleBarState.getMock(),
            )
        }
    }
}