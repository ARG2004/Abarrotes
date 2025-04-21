package com.example.abarrotes.ui.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.Locale

// Simulación de un item del carrito con opciones de edición
data class CartItem(
    val name: String,
    var quantity: Int,
    val price: Double
)

@Composable
fun PayScreen(navController: NavController) {
    // Simulación de la lista de productos en el carrito (reemplazar con tu lógica real)
    val cartItems = remember {
        mutableStateListOf(
            CartItem("Coca-Cola 600ml", 2, 20.0),
            CartItem("Gansito", 1, 17.0),
            CartItem("Aceite 123", 1, 42.0),
            CartItem("LaLa deslactosada...", 2, 37.0),
            CartItem("Tortillas Tía Rosa...", 1, 34.0)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Encabezado
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar a escanear")
            }
            Text(
                "menú de cobro",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
        }

        // Lista de productos
        LazyColumn(modifier = Modifier.weight(1f)) {
            itemsIndexed(cartItems) { index, item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Nombre del producto
                    Text(item.name, modifier = Modifier.weight(1f))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        // Botón para disminuir la cantidad
                        Button(
                            onClick = { if (item.quantity > 1) item.quantity-- },
                            modifier = Modifier.size(32.dp),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("-", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        // Cantidad
                        Text(
                            "${item.quantity} pz",
                            modifier = Modifier.width(40.dp),
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        // Botón para aumentar la cantidad
                        Button(
                            onClick = { item.quantity++ },
                            modifier = Modifier.size(32.dp),
                            shape = RoundedCornerShape(50),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text("+", fontSize = 20.sp)
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        // Precio por unidad
                        Text("$${String.format(Locale.getDefault(), "%.2f", item.price)} c/u", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                        // Precio total del producto
                        Text(
                            "$${String.format(Locale.getDefault(), "%.2f", item.price * item.quantity)}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                HorizontalDivider()
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Total a pagar
        Text(
            "Total: $${String.format(Locale.getDefault(), "%.2f", cartItems.sumOf { it.price * it.quantity })}",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Forma de pago
        Text("Forma de pago", fontWeight = FontWeight.Bold, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            var showEfectivoPayment by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    showEfectivoPayment = true
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF81C784), contentColor = Color.White) // Verde similar al efectivo
            ) {
                Text("Efectivo")
            }
            Spacer(modifier = Modifier.width(16.dp))
            var showTarjetaPayment by remember { mutableStateOf(false) }
            Button(
                onClick = {
                    showTarjetaPayment = true
                    showEfectivoPayment = false
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF64B5F6), contentColor = Color.White) // Azul similar a tarjeta
            ) {
                Text("Tarjeta")
            }
        }

        // Vista de pago en efectivo (inicialmente oculta)
        var efectivoAmount by remember { mutableStateOf("") }
        var showEfectivoMessage by remember { mutableStateOf(false) }
        val showEfectivoPayment = false
        if (showEfectivoPayment) {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.3f))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Pago en efectivo", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Total a pagar: $${String.format(Locale.getDefault(), "%.2f", cartItems.sumOf { it.price * it.quantity })}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = efectivoAmount,
                    onValueChange = { efectivoAmount = it },
                    label = { Text("Ingresa el pago") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (showEfectivoMessage) {
                    Text(
                        "El monto es menor al total",
                        color = Color.Red,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Button(onClick = {
                    val total = cartItems.sumOf { it.price * it.quantity }
                    val amountEntered = efectivoAmount.toDoubleOrNull() ?: 0.0
                    if (amountEntered < total) {
                        showEfectivoMessage = true
                    } else {
                        showEfectivoMessage = false
                        amountEntered - total
                        // TODO: Implementar lógica de finalizar venta con efectivo y vuelto
                    }
                }) {
                    Text("Aceptar")
                }
                Spacer(modifier = Modifier.height(16.dp))
                val total = cartItems.sumOf { it.price * it.quantity }
                val amountEntered = efectivoAmount.toDoubleOrNull() ?: 0.0
                val vuelto = if (amountEntered >= total) amountEntered - total else 0.0
                Text("Vuelto: $${String.format(Locale.getDefault(), "%.2f", vuelto)}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Una vez entregado el cambio presionar para finalizar la venta", textAlign = TextAlign.Center, fontSize = 12.sp)
                Button(onClick = { /* TODO: Implementar lógica de finalizar venta (confirmación de vuelto entregado) */ }) {
                    Text("Finalizar Venta")
                }
            }
        }

        // Vista de pago con tarjeta
        var folioTarjeta by remember { mutableStateOf("") }
        val showTarjetaPayment = false
        if (showTarjetaPayment) {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray.copy(alpha = 0.3f))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text("Pago con tarjeta", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Total a pagar: $${String.format(Locale.getDefault(), "%.2f", cartItems.sumOf { it.price * it.quantity })}", fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(16.dp))
                OutlinedTextField(
                    value = folioTarjeta,
                    onValueChange = { folioTarjeta = it },
                    label = { Text("Ingrese folio") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { /* TODO: Implementar lógica de procesar pago con tarjeta usando el folio */ }) {
                    Text("Aceptar")
                }
                Spacer(modifier = Modifier.height(16.dp))
                Text("Una vez completada la transacción presionar para finalizar la venta", textAlign = TextAlign.Center, fontSize = 12.sp)
                Button(onClick = { /* TODO: Implementar lógica de finalizar venta (confirmación de pago con tarjeta) */ }) {
                    Text("Finalizar Venta")
                }
            }
        }

        // Navegación inferior (si es necesario en esta pantalla)
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { navController.navigate("home") }, modifier = Modifier.weight(1f)) { Text("Inicio") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* TODO: Navegar a inventario */ }, modifier = Modifier.weight(1f)) { Text("Inventario") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* TODO: Navegar a ventas */ }, modifier = Modifier.weight(1f)) { Text("Ventas") }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { /* TODO: Navegar a usuario */ }, modifier = Modifier.weight(1f)) { Text("Usuario") }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PayScreenPreview() {
    val navController = rememberNavController()
    PayScreen(navController = navController)
}