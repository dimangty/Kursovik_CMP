package com.example.kursovikcmp.feature.Favorites.Details

import com.example.kursovikcmp.MR
import com.example.kursovikcmp.base.mvvm.BaseViewState
import com.example.kursovikcmp.common.mvvm.TitleBarState
import com.example.kursovikcmp.common.view.ButtonState
import com.example.kursovikcmp.common.view.TextState
import com.example.kursovikcmp.common.view.updateValue

data class FavoriteDetailsState(
    val imageUrl: String? = null,
    val dateState: TextState = TextState.latoRegular(13, MR.colors.black),
    val titleState: TextState = TextState.latoSemibold(17, MR.colors.black),
    val textState: TextState = TextState.latoRegular(13, MR.colors.black),
    val favoriteButton: ButtonState = ButtonState.image(image = MR.images.favorite_off_icon),
    val openButton: ButtonState = ButtonState.primary("Open"),
    override val titleBarState: TitleBarState = TitleBarState.getMock()
) : BaseViewState {

    companion object {
        fun getMock() = FavoriteDetailsState().run {
            copy(
                dateState = dateState.updateValue("3 march"),
                titleState = titleState.updateValue("title"),
                textState = textState.updateValue("text"),
                openButton = openButton.updateValue("Open"),
                imageUrl = "https://cdnstatic.rg.ru/uploads/images/2025/02/25/zagruzhennoe_f42.jpg",
            )
        }
    }
}