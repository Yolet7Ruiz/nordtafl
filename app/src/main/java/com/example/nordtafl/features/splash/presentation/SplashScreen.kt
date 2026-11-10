package com.example.nordtafl.features.splash.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

val DeepBlack = Color(0xFF0F1115)
val GoldNorse = Color(0xFFD4AF37)
val BloodRed = Color(0xFF8B0000)
val IceBlue = Color(0xFF4A90E2)
val BoneWhite = Color(0xFFE8EAF0)

@Composable
fun SplashScreen(
    onNavigateNext: () -> Unit = {}
) {
    var animationStage by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        delay(400)
        animationStage = 1 // Aparece Ruleta y Dragón
        delay(1200)
        animationStage = 2 // Revela el Nombre NordTafl
        delay(800)
        animationStage = 3 // Muestra el Botón "Entrar"
    }

    val infiniteTransition = rememberInfiniteTransition(label = "RuneRotation")
    val runeRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(14000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "RuneRotationAngle"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(Color(0xFF1D212A), DeepBlack),
                    radius = 1200f
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // LAYER 1: Ruleta Rúnica
        if (animationStage >= 1) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .rotate(runeRotation)
                    .border(
                        width = 2.dp,
                        brush = Brush.sweepGradient(listOf(GoldNorse, BloodRed, IceBlue, GoldNorse)),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "ᚠ ᚢ ᚦ ᚨ ᚱ ᚲ ᚷ ᚹ ᚺ ᚾ ᛁ ᛃ ᛇ ᛈ ᛉ ᛋ ᛏ",
                    color = GoldNorse.copy(alpha = 0.45f),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // LAYER 2: Contenido Principal
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = animationStage >= 1,
                enter = fadeIn() + scaleIn(initialScale = 0.4f)
            ) {
                Box(
                    modifier = Modifier
                        .size(150.dp)
                        .background(BloodRed.copy(alpha = 0.25f), shape = CircleShape)
                        .border(2.dp, GoldNorse, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🐉", fontSize = 70.sp)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            AnimatedVisibility(
                visible = animationStage >= 2,
                enter = fadeIn() + scaleIn()
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "NORDTAFL",
                        color = BoneWhite,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 6.sp,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "AJEDREZ VIKINGO Y ESTRATEGIA EXPLICABLE",
                        color = GoldNorse,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(40.dp))

            AnimatedVisibility(
                visible = animationStage >= 3,
                enter = fadeIn()
            ) {
                Button(
                    onClick = onNavigateNext,
                    colors = ButtonDefaults.buttonColors(containerColor = BloodRed),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .width(220.dp)
                        .height(50.dp)
                        .shadow(12.dp, ambientColor = GoldNorse, spotColor = BloodRed)
                ) {
                    Text(
                        text = "ENTRAR AL REINO",
                        color = BoneWhite,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }
        }
    }
}