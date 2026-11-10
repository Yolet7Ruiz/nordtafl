package com.example.nordtafl.features.onboarding.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TutorialScreen(
    onFinishTutorial: () -> Unit = {}
) {
    var step by remember { mutableIntStateOf(0) }

    val titles = listOf(
        "EL TABLERO ASIMÉTRICO",
        "MOVIMIENTO DE FICHA",
        "CAPTURA POR FLANQUEO",
        "VICTORIA DEL REY"
    )

    val descriptions = listOf(
        "El Rey (centro) y sus 12 Defensores luchan contra 24 Atacantes rodeando el trono.",
        "Todas las fichas se mueven en línea recta horizontal o vertical (como la Torre en ajedrez).",
        "Capturas una ficha enemiga cuando la flanqueas entre dos de tus fichas.",
        "El Defensor gana si el Rey llega a cualquier esquina. Los Atacantes ganan rodeando al Rey."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Cabecera
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "APRENDE HNEFATAFL",
                color = Color(0xFFD4AF37),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            TextButton(onClick = onFinishTutorial) {
                Text("SALTAR", color = Color(0xFF8A90A2))
            }
        }

        // Simulador de Video / Ilustración Animada
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .background(Color(0xFF1A1D24), RoundedCornerShape(12.dp))
                .border(1.dp, Color(0xFF2A2F3D), RoundedCornerShape(12.dp)),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = when(step) {
                        0 -> "🛡️ VS ⚔️"
                        1 -> "↔️ ↕️"
                        2 -> "⚔️ 🛡️ ⚔️"
                        else -> "👑 ➔ 🚩"
                    },
                    fontSize = 60.sp
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "[ Simulación interactiva de regla ${step + 1} ]",
                    color = Color(0xFF4A90E2),
                    fontSize = 12.sp
                )
            }
        }

        // Explicación
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = titles[step],
                color = Color(0xFFE8EAF0),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = descriptions[step],
                color = Color(0xFF8A90A2),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

        // Botones de navegación
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (step > 0) {
                OutlinedButton(
                    onClick = { step-- },
                    border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFF2A2F3D)))
                ) {
                    Text("ANTERIOR", color = Color(0xFFE8EAF0))
                }
            } else {
                Spacer(modifier = Modifier.width(1.dp))
            }

            Button(
                onClick = {
                    if (step < 3) step++ else onFinishTutorial()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000))
            ) {
                Text(if (step < 3) "SIGUIENTE" else "ENTENDIDO", color = Color(0xFFE8EAF0))
            }
        }
    }
}