package com.example.nordtafl.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nordtafl.features.splash.presentation.SplashScreen
import com.example.nordtafl.features.onboarding.presentation.TutorialScreen
import com.example.nordtafl.features.auth.presentation.login.LoginScreen
import com.example.nordtafl.features.auth.presentation.login.OtpScreen
import com.example.nordtafl.features.lobby.presentation.home.LobbyScreen
import com.example.nordtafl.features.game.presentation.board.GameScreen
import com.example.nordtafl.features.history.presentation.historyList.HistoryScreen
import com.example.nordtafl.features.ranking.presentation.leaderboard.RankingScreen
import com.example.nordtafl.features.profile.presentation.profile.ProfileScreen


@Composable
fun NordTaflNavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable("splash") {
            SplashScreen(
                onNavigateNext = { navController.navigate("tutorial") }
            )
        }

        composable("tutorial") {
            TutorialScreen(
                onFinishTutorial = { navController.navigate("login") }
            )
        }

        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("lobby") },
                onNavigateToOtp = { navController.navigate("otp") }
            )
        }

        composable("otp") {
            OtpScreen(
                onOtpVerified = { navController.navigate("lobby") }
            )
        }

        composable("lobby") {
            LobbyScreen(
                onNavigateToGame = { navController.navigate("game") },
                onNavigateToHistory = { navController.navigate("history") },
                onNavigateToRanking = { navController.navigate("ranking") },
                onNavigateToProfile = { navController.navigate("profile") }
            )
        }

        composable("game") {
            GameScreen(
                onLeaveGame = { navController.popBackStack() }
            )
        }

        composable("history") {
            HistoryScreen(
                onBackToLobby = { navController.popBackStack() }
            )
        }

        composable("ranking") {
            RankingScreen(
                onBackToLobby = { navController.popBackStack() }
            )
        }

        composable("profile") {
            ProfileScreen(
                onBackToLobby = { navController.popBackStack() },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("lobby") { inclusive = true }
                    }
                }
            )
        }
    }
}