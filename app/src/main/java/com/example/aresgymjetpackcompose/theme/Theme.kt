package com.example.aresgymjetpackcompose.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.fontResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.aresgymjetpackcompose.R

val colorTheme = lightColorScheme(
    primary = black,
    secondary = red,
    tertiary = blue,
    background = white,
    onPrimary = black_light,
    onTertiary = blue_light,
    onSecondary = red_light,
    onBackground = white
)

@OptIn(ExperimentalUnitApi::class)
val JetnewsTypography = Typography(
    titleMedium = TextStyle(
        textAlign = TextAlign.Center,
        fontSize = TextUnit(30f, TextUnitType.Sp),
        fontFamily = FontFamily(Font(R.font.merriweather_bold))
    ),
    labelMedium = TextStyle(
        textAlign = TextAlign.Left,
        fontSize = TextUnit(18f, TextUnitType.Sp),
        fontFamily = FontFamily(Font(R.font.oxygen_bold))
    ),
    labelSmall = TextStyle(
        textAlign = TextAlign.Left,
        fontSize = TextUnit(16f, TextUnitType.Sp),
        fontFamily = FontFamily(Font(R.font.oxygen_light))
    ),
    labelLarge = TextStyle(
        textAlign = TextAlign.Left,
        fontSize = TextUnit(20f, TextUnitType.Sp),
        fontFamily = FontFamily(Font(R.font.montserrat_regular))
    ),
    titleLarge = TextStyle(
        textAlign = TextAlign.Left,
        fontSize = TextUnit(40f, TextUnitType.Sp),
        fontFamily = FontFamily(Font(R.font.roboto_light))
    )
)

val shapes = Shapes(
    medium = RoundedCornerShape(9.dp)
)


@Composable
fun Theme(content : @Composable () -> Unit ){

    //val darkTheme : Boolean = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = colorTheme,
        shapes = shapes,
        typography = JetnewsTypography,
        content = content
    )

}