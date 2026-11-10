package com.example.nordtafl.features.ranking.presentation.leaderboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class LeaderboardPlayer(val rank: Int, val name: String, val elo: Int, val isCurrentUser: Boolean = false)

@Composable
fun RankingScreen(
    onBackToLobby: () -> Unit = {}
) {
    val players = listOf(
        LeaderboardPlayer(1, "Odin_Allfather", 2100),
        LeaderboardPlayer(2, "Thor_Mjolnir", 1950),
        LeaderboardPlayer(3, "Loki_Trickster", 1880),
        LeaderboardPlayer(14, "VikingoRagnar (Tú)", 1450, isCurrentUser = true),
        LeaderboardPlayer(15, "Freya_Shield", 1420)
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
                Text("← VOLVER", color = Color(0xFF8A90A2))
            }
            Text(
                text = "CLASIFICACIÓN ELO",
                color = Color(0xFFD4AF37),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(players) { _, player ->
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = if (player.isCurrentUser) Color(0xFF8B0000).copy(alpha = 0.3f) else Color(0xFF1A1D24)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = if (player.isCurrentUser) 1.5.dp else 1.dp,
                            color = if (player.isCurrentUser) Color(0xFFD4AF37) else Color(0xFF2A2F3D),
                            shape = RoundedCornerShape(8.dp)
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "#${player.rank}",
                                color = when (player.rank) {
                                    1 -> Color(0xFFD4AF37)
                                    2 -> Color(0xFFC0C0C0)
                                    3 -> Color(0xFFCD7F32)
                                    else -> Color(0xFF8A90A2)
                                },
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                modifier = Modifier.width(36.dp)
                            )
                            Text(
                                text = player.name,
                                color = Color(0xFFE8EAF0),
                                fontWeight = if (player.isCurrentUser) FontWeight.ExtraBold else FontWeight.Normal
                            )
                        }
                        Text(
                            text = "${player.elo} PTS",
                            color = Color(0xFFD4AF37),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}