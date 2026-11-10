package com.example.nordtafl.core.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

// Colores Nórdicos para NordTafl
val DeepBlack = Color(0xFF0F1115)
val GoldNorse = Color(0xFFD4AF37)
val BloodRed = Color(0xFF8B0000)
val IceBlue = Color(0xFF4A90E2)
val BoneWhite = Color(0xFFE8EAF0)
val DarkSurface = Color(0xFF1A1D24)

private val NordTaflColorScheme = darkColorScheme(
    primary = GoldNorse,
    secondary = BloodRed,
    tertiary = IceBlue,
    background = DeepBlack,
    surface = DarkSurface,
    onPrimary = DeepBlack,
    onSecondary = BoneWhite,
    onBackground = BoneWhite,
    onSurface = BoneWhite
)

@Composable
fun NordTaflTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = NordTaflColorScheme
    val view = LocalView.current

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}