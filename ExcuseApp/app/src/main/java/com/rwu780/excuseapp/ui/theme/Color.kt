package com.rwu780.excuseapp.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF322158)
val Teal200 = Color(0xFF03DAC5)

val background1 = Color(0xFF6A16BF)
val background2 = Color(0xFF1A0624)

val screen_background_gradient_brush = Brush.verticalGradient(
    colors = listOf(
        background1,
        background2
    )
)

val card_background_gradient_brush = Brush.verticalGradient(
    colors = listOf(
        background2,
        background1
    )
)



val elementBackgroundGradientBrush = Brush.verticalGradient(
    colors = listOf(
        Color.Black,
        Purple700
    )
)