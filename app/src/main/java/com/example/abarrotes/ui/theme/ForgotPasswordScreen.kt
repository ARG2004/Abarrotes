// ui/ForgotPasswordScreen.kt
package com.example.abarrotes.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(onResetLinkSent: () -> Unit) {
    var email by remember { mutableStateOf("") }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Restablecer\ncontraseña",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Ingresa tu correo electrónico") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    // TODO: Implement logic to send reset link
                    onResetLinkSent() // Simulate link sent and navigate
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Enviar")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Se ha enviado un enlace a tu correo,\nda clic en él para restablecer tu contraseña",
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ForgotPasswordScreenPreview() {
    ForgotPasswordScreen(onResetLinkSent = {})
}

annotation class ForgotPasswordScreen
