package com.example.kursovikcmp.feature.News.List

import com.example.kursovikcmp.MR
import com.example.kursovikcmp.base.mvvm.BaseViewState
import com.example.kursovikcmp.common.view.ButtonState
import com.example.kursovikcmp.common.view.TextState
import com.example.kursovikcmp.common.mvvm.TitleBarState
import com.example.kursovikcmp.common.view.updateValue
import dev.icerock.moko.resources.ColorResource

data class NewsListState(
    val newsItems: List<NewsUiState> = listOf(),
    override val titleBarState: TitleBarState = TitleBarState.getMock(),
    val backGroundColor: ColorResource = MR.colors.grey,
) : BaseViewState {
    companion object {
        fun getMock() = NewsListState().run {
            copy(
                newsItems = listOf(NewsUiState.getMock(), NewsUiState.getMock(), NewsUiState.getMock(),)
            )
        }
    }
}

data class NewsUiState(
    val id: String = "",
    val title: String = "",
    val text: String = "",
    val date: String = "",
    val imageUrl: String? = "",
    val isFavorite: Boolean = false,
    val cellBackground: ColorResource = MR.colors.white,
) {
    val titleState: TextState = TextState.latoSemibold(17, MR.colors.black).updateValue(title)
    val textState: TextState = TextState.latoRegular(13, MR.colors.black).updateValue(text)
    val dateState: TextState = TextState.latoRegular(13, MR.colors.black).updateValue(date)
    val favoriteButton: ButtonState = ButtonState.image(image = if(isFavorite)  MR.images.favorite_on_icon else MR.images.favorite_off_icon)

    companion object {
        fun getMock() = NewsUiState().run {
            copy(
                title = "title",
                text = "text",
                date = "date",
                imageUrl = "https://cdnstatic.rg.ru/uploads/images/2025/02/25/zagruzhennoe_f42.jpg",
            )
        }
    }
}