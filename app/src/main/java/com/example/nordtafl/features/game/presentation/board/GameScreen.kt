package com.nordtafl.features.game.presentation.board

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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

// Tipos de Ficha
enum class PieceType { KING, DEFENDER, ATTACKER }

@Composable
fun GameBoardScreen(
    onLeaveGame: () -> Unit = {}
) {
    var selectedTile by remember { mutableStateOf<Pair<Int, Int>?>(null) }
    var showAssistantOverlay by remember { mutableStateOf(false) }

    // Representación simplificada de la cuadrícula 11x11 de Hnefatafl
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header de estado
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextButton(onClick = onLeaveGame) {
                Text("← ABANDONAR", color = Color(0xFF8A90A2))
            }
            Text(
                text = "TURNO: DEFENSOR",
                color = Color(0xFF4A90E2),
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
            IconButton(onClick = { showAssistantOverlay = !showAssistantOverlay }) {
                Text("🐉", fontSize = 24.sp)
            }
        }

        // TABLERO 11x11 RENDERIZADO
        Box(
            modifier = Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .background(Color(0xFF1A1D24), RoundedCornerShape(8.dp))
                .border(2.dp, Color(0xFF2A2F3D), RoundedCornerShape(8.dp))
                .padding(4.dp)
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                for (row in 0 until 11) {
                    Row(modifier = Modifier.weight(1f)) {
                        for (col in 0 until 11) {
                            val isCorner = (row == 0 || row == 10) && (col == 0 || col == 10)
                            val isThrone = row == 5 && col == 5
                            val isSelected = selectedTile == Pair(row, col)

                            // Simulación de Fichas (Rey al centro, Defensores e Atacantes)
                            val piece = when {
                                isThrone -> PieceType.KING
                                (row in 3..7 && col in 3..7) && !(row == 3 && col == 3) && !(row == 7 && col == 7) -> PieceType.DEFENDER
                                (row == 0 && col in 3..7) || (row == 10 && col in 3..7) || (col == 0 && row in 3..7) || (col == 10 && row in 3..7) -> PieceType.ATTACKER
                                else -> null
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight()
                                    .border(0.5.dp, Color(0xFF2A2F3D))
                                    .background(
                                        when {
                                            isSelected -> Color(0xFFD4AF37).copy(alpha = 0.4f)
                                            isCorner || isThrone -> Color(0xFF8B0000).copy(alpha = 0.2f)
                                            else -> Color.Transparent
                                        }
                                    )
                                    .clickable { selectedTile = Pair(row, col) },
                                contentAlignment = Alignment.Center
                            ) {
                                when (piece) {
                                    PieceType.KING -> Box(
                                        modifier = Modifier
                                            .size(20.dp)
                                            .background(Color(0xFFD4AF37), CircleShape)
                                            .border(1.dp, Color(0xFFE8EAF0), CircleShape),
                                        contentAlignment = Alignment.Center
                                    ) { Text("👑", fontSize = 10.sp) }

                                    PieceType.DEFENDER -> Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .background(Color(0xFF4A90E2), CircleShape)
                                    )

                                    PieceType.ATTACKER -> Box(
                                        modifier = Modifier
                                            .size(16.dp)
                                            .background(Color(0xFF8B0000), CircleShape)
                                    )

                                    null -> if (isCorner) Text("ᛉ", color = Color(0xFFD4AF37).copy(alpha = 0.5f), fontSize = 10.sp)
                                }
                            }
                        }
                    }
                }
            }
        }

        // ASISTENTE DE IA EXPLICABLE (OVERLAY / PANEL INFERIOR)
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFF1A1D24)),
            border = ButtonDefaults.outlinedButtonBorder.copy(brush = androidx.compose.ui.graphics.SolidColor(Color(0xFFD4AF37))),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("💡 CONSEJO DEL ASISTENTE", color = Color(0xFFD4AF37), fontWeight = FontWeight.Bold, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = if (selectedTile != null)
                        "Mover a (${selectedTile?.first}, ${selectedTile?.second}) protege tu flanco de un posible encierro de los atacantes."
                    else
                        "Selecciona una ficha para calcular sugerencias explicables con el algoritmo Minimax.",
                    color = Color(0xFFE8EAF0),
                    fontSize = 13.sp
                )
            }
        }
    }
}