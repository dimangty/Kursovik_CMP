package com.example.kursovikcmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.kursovikcmp.feature.News.List.Compose.NewsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsScreen()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    NewsScreen()
}