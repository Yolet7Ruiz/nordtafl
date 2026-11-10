package com.nordtafl.features.history.presentation.historyList

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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

data class GameRecord(val id: String, val opponent: String, val result: String, val date: String, val turns: Int)

@Composable
fun HistoryScreen(
    onBackToLobby: () -> Unit = {}
) {
    var selectedRecord by remember { mutableStateOf<GameRecord?>(null) }
    var replayStep by remember { mutableFloatStateOf(0f) }

    val mockRecords = listOf(
        GameRecord("1", "Ivar_ElDesalmado", "VICTORIA (Rey Escapó)", "10 Nov 2026", 24),
        GameRecord("2", "Bjorn_PielDeHierro", "DERROTA (Rey Capturado)", "08 Nov 2026", 32),
        GameRecord("3", "Asistente Heurístico", "VICTORIA (Rey Escapó)", "05 Nov 2026", 18)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onBackToLobby) {
                Text("← VOLVER AL LOBBY", color = Color(0xFF8A90A2))
            }
            Text(
                text = "HISTORIAL Y REPLAY",
                color = Color(0xFFD4AF37),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

        // Si se selecciona una partida, muestra el panel de Replay jugada a jugada
        if (selectedRecord != null) {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1D24)),
                border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFD4AF37))),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "REPRODUCIENDO: vs ${selectedRecord?.opponent}",
                        color = Color(0xFFE8EAF0),
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Turno ${replayStep.toInt()} de ${selectedRecord?.turns}",
                        color = Color(0xFF4A90E2),
                        fontSize = 12.sp
                    )
                    Slider(
                        value = replayStep,
                        onValueChange = { replayStep = it },
                        valueRange = 0f..(selectedRecord?.turns?.toFloat() ?: 10f),
                        colors = SliderDefaults.colors(
                            thumbColor = Color(0xFFD4AF37),
                            activeTrackColor = Color(0xFF8B0000)
                        )
                    )
                    TextButton(onClick = { selectedRecord = null }, modifier = Modifier.align(Alignment.End)) {
                        Text("CERRAR REPLAY", color = Color(0xFF8A90A2))
                    }
                }
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(mockRecords) { record ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1D24)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, Color(0xFF2A2F3D), RoundedCornerShape(8.dp)),
                    onClick = {
                        selectedRecord = record
                        replayStep = 0f
                    }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(text = "vs ${record.opponent}", color = Color(0xFFE8EAF0), fontWeight = FontWeight.Bold)
                            Text(text = "${record.date} • ${record.turns} turnos", color = Color(0xFF8A90A2), fontSize = 12.sp)
                        }
                        Text(
                            text = record.result,
                            color = if (record.result.contains("VICTORIA")) Color(0xFFD4AF37) else Color(0xFF8B0000),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}