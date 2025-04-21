// ui/NewPasswordScreen.kt
package com.example.abarrotes.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewPasswordScreen(onPasswordChangedSuccess: () -> Unit, onPasswordMismatch: () -> Unit) {
    var newPassword by remember { mutableStateOf("") }
    var confirmNewPassword by remember { mutableStateOf("") }
    var passwordMismatchError by remember { mutableStateOf(false) }
    var passwordChangedSuccessMessage by remember { mutableStateOf(false) }

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
                text = "Ingresa tu nueva\ncontraseña",
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 32.dp),
                textAlign = TextAlign.Center
            )

            OutlinedTextField(
                value = newPassword,
                onValueChange = { newPassword = it },
                label = { Text("Nueva contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "8 caracteres mínimo",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Usa al menos una mayúscula",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Text(
                text = "Usa al menos un dígito numérico (0-9)",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = confirmNewPassword,
                onValueChange = { confirmNewPassword = it },
                label = { Text("Repite tu nueva contraseña") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (newPassword == confirmNewPassword) {
                        // TODO: Implement password update logic here
                        passwordMismatchError = false
                        passwordChangedSuccessMessage = true
                        onPasswordChangedSuccess() // Notify success
                    } else {
                        passwordMismatchError = true
                        passwordChangedSuccessMessage = false
                        onPasswordMismatch() // Notify mismatch
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Aceptar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (passwordMismatchError) {
                Text(
                    text = "Las contraseñas no coinciden intenta de nuevo",
                    color = MaterialTheme.colorScheme.error,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }

            if (passwordChangedSuccessMessage) {
                Text(
                    text = "La contraseña ha sido cambiada correctamente",
                    color = Color.Green,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NewPasswordScreenPreview() {
    NewPasswordScreen(onPasswordChangedSuccess = {}, onPasswordMismatch = {})
}

annotation class NewPasswordScreen
