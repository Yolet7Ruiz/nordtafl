package  com.example.nordtafl.features.auth.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
fun OtpScreen(
    onOtpVerified: () -> Unit = {}
) {
    var otpCode by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF0F1115))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "VERIFICACIÓN OTP",
            color = Color(0xFFD4AF37),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ingresa el código enviado a tu correo",
            color = Color(0xFF8A90A2),
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        // 6 Inputs de OTP
        OutlinedTextField(
            value = otpCode,
            onValueChange = { if (it.length <= 6) otpCode = it },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFD4AF37),
                unfocusedBorderColor = Color(0xFF2A2F3D),
                focusedTextColor = Color(0xFFE8EAF0),
                unfocusedTextColor = Color(0xFFE8EAF0)
            ),
            modifier = Modifier.fillMaxWidth(0.8f)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "El código expira en 04:59",
            color = Color(0xFF8A90A2),
            fontSize = 12.sp
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onOtpVerified,
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8B0000)),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("VERIFICAR CÓDIGO", color = Color(0xFFE8EAF0), fontWeight = FontWeight.Bold)
        }
    }
}