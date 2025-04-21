// ui/theme/ScannerScreen.kt
package com.example.abarrotes.ui.theme

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FlashOff
import androidx.compose.material.icons.filled.FlashOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.abarrotes.R

@Composable
fun ScannerScreen(onClose: () -> Unit, navController: NavController) {
    var isFlashOn by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.LightGray.copy(alpha = 0.6f)), // Fondo similar al diseño
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("menu de escaner", style = MaterialTheme.typography.titleMedium)
            IconButton(onClick = onClose) {
                Icon(Icons.Filled.Close, contentDescription = "Cerrar Escáner", tint = Color.Red)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Escanea el código de barras", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Área para centrar el código de barras (simulado con un Box)
        Box(
            modifier = Modifier
                .size(200.dp) // Ajusta el tamaño según tu diseño
                .border(BorderStroke(2.dp, Color.Black)) // Borde visible
        ) {
            // Aquí iría la vista de la cámara en el futuro
            Text(
                text = "Área de escaneo",
                modifier = Modifier.fillMaxSize(),
                textAlign = TextAlign.Center,
                fontSize = 20.sp
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text("Código inválido escanea de nuevo", color = Color.Red, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Button(onClick = { /* TODO: Implementar consultar precio */ }) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Filled.Search, contentDescription = "Consultar precio", modifier = Modifier.size(24.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("Consultar precio")
                    }
                }
                Text("En caso de que sea necesario podremos escribir el código o el producto", textAlign = TextAlign.Center, fontSize = 10.sp)
            }

            Button(onClick = { /* TODO: Implementar botón cancelar venta */ }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red, contentColor = Color.White)) {
                Text("Cancelar venta")
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { isFlashOn = !isFlashOn }) {
                Icon(
                    imageVector = if (isFlashOn) Icons.Filled.FlashOn else Icons.Filled.FlashOff,
                    contentDescription = "Flash ${if (isFlashOn) "On" else "Off"}"
                )
            }
            Spacer(modifier = Modifier.weight(1f)) // Mantenemos el Spacer para la distribución
            Button(onClick = { /* TODO: Implementar botón cobrar */ }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(painterResource(id = R.drawable.sale_complete_icon), contentDescription = "Cobrar", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Cobrar")
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { /* TODO: Implementar botón agregar producto */ }) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Add, contentDescription = "Producto", modifier = Modifier.size(24.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Producto")
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        // Navegación inferior (simulada aquí, podrías usar NavigationBar)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { navController.navigate("home") }) { Text("Inicio") }
            Button(onClick = { /* TODO: Navegar a inventario */ }) { Text("Inventario") }
            Button(onClick = { /* TODO: Navegar a ventas */ }) { Text("Ventas") }
            Button(onClick = { /* TODO: Navegar a usuario */ }) { Text("Usuario") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScannerScreenPreview() {
    val navController = rememberNavController()
    ScannerScreen(onClose = {}, navController = navController)
}