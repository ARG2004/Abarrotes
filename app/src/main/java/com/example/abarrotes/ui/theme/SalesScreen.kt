package com.example.abarrotes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalesScreen(navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    var selectedDay by remember { mutableStateOf("Seleccionar día") }
    val salesDays = remember { listOf("Hoy", "Ayer", "Últimos 7 días", "Este mes") }
    val totalSales = remember { mutableIntStateOf(1) } // Simulación de ventas totales
    val totalMoney = remember { mutableDoubleStateOf(267.0) } // Simulación de dinero total

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú de ventas") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Implementar acción del menú */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menú")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = "sales")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedDay,
                    onValueChange = { },
                    label = { Text("Seleccionar día") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    salesDays.forEach { day ->
                        DropdownMenuItem(
                            text = { Text(day) },
                            onClick = {
                                selectedDay = day
                                expanded = false
                                // TODO: Implementar lógica para cargar datos según el día seleccionado
                            }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    totalSales.intValue.toString(), // Corregido: Convertir Int a String para Text
                    style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Ventas totales (diario)")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    String.format(Locale.getDefault(), "%.2f", totalMoney.doubleValue), // Corregido: Usar String.format con Locale
                    style = androidx.compose.material3.MaterialTheme.typography.headlineLarge
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text("Dinero total (diario)")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(onClick = { /* TODO: Implementar generar reporte */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Generar reporte")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { /* TODO: Implementar corte final de caja */ }, modifier = Modifier.fillMaxWidth()) {
                Text("Corte final de caja")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SalesScreenPreview() {
    val navController = rememberNavController()
    SalesScreen(navController = navController)
}