package com.example.kursovikcmp.common.mvvm


import com.example.kursovikcmp.MR
import com.example.kursovikcmp.base.mvvm.DefaultUiEvent
import com.example.kursovikcmp.common.view.TextState
import com.example.kursovikcmp.common.view.getMock
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.ImageResource

@Suppress("MagicNumber")
data class TitleBarState(
    val title: TextState = TextState.latoMedium(16, MR.colors.black),
    val isNavigateBackVisible: Boolean = false,
    val backIcon: ImageResource = MR.images.ic_titlebar_back,
    val contentColor: ColorResource = MR.colors.black,
    val onDefaultUiEvent: (DefaultUiEvent) -> Unit = {}
) {
    companion object {
        fun getMock() = TitleBarState().run {
            copy(
                title = title.getMock("Title bar"),
                isNavigateBackVisible = true,
            )
        }
    }
}