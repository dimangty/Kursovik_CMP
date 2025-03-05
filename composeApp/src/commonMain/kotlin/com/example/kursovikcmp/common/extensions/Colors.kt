package com.example.kursovikcmp.common.extensions

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.kursovikcmp.MR
import com.example.kursovikcmp.common.view.TextFontState
import dev.icerock.moko.resources.ColorResource
import dev.icerock.moko.resources.compose.colorResource
import dev.icerock.moko.resources.compose.fontFamilyResource

@Composable
fun ColorResource.color(): Color {
    return colorResource(this)
}

@Composable
fun TextFontState.textStyle(): TextStyle {
    return TextStyle(
        fontFamily = fontFamilyResource(this.font),
        fontSize = this.fontSize.sp,
        lineHeight = this.lineHeight.sp,
    )
}