package com.nordtafl.features.profile.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    onBackToLobby: () -> Unit = {},
    onLogout: () -> Unit = {}
) {
    var isBiometricsEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = onBackToLobby) {
                    Text("← LOBBY", color = Color(0xFF8A90A2))
                }
                Text(
                    text = "PERFIL DE JUGADOR",
                    color = Color(0xFFD4AF37),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
            }

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(Color(0xFF1A1D24), CircleShape)
                    .border(2.dp, Color(0xFFD4AF37), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "🛡️", fontSize = 42.sp)
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "VikingoRagnar",
                color = Color(0xFFE8EAF0),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "ragnar@valhalla.nord",
                color = Color(0xFF8A90A2),
                fontSize = 13.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Opción de Biometría
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1D24)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = "Acceso Biométrico (WebAuthn)", color = Color(0xFFE8EAF0), fontWeight = FontWeight.Bold)
                        Text(text = "Usar huella o rostro para iniciar sesión rápido", color = Color(0xFF8A90A2), fontSize = 11.sp)
                    }
                    Switch(
                        checked = isBiometricsEnabled,
                        onCheckedChange = { isBiometricsEnabled = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color(0xFFD4AF37),
                            checkedTrackColor = Color(0xFF8B0000)
                        )
                    )
                }
            }
        }

        Button(
            onClick = onLogout,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("CERRAR SESIÓN", color = Color(0xFFE8EAF0), fontWeight = FontWeight.Bold)
        }
    }
}