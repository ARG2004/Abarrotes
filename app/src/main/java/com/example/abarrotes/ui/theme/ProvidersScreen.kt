package com.example.abarrotes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Datos de ejemplo de proveedores (reemplazar con tu lógica real)
data class Provider(val id: Int, val name: String)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProvidersScreen(navController: NavController) {
    val providers = remember {
        mutableStateListOf(
            Provider(1, "Proveedor A"),
            Provider(2, "Proveedor B"),
            Provider(3, "Proveedor C")
        )
    }

    var searchText by remember { mutableStateOf("") }
    val filteredProviders = remember(providers, searchText) {
        if (searchText.isBlank()) {
            providers
        } else {
            providers.filter { it.name.contains(searchText, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Proveedores") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: Implementar lógica para agregar proveedor */ }) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar proveedor")
            }
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    label = { Text("Buscar Proveedor") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar proveedor") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                filteredProviders.forEach { provider ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(provider.name)
                        Spacer(modifier = Modifier.weight(1f))
                        IconButton(onClick = { /* TODO: Implementar lógica para editar proveedor ${provider.name} */ }) {
                            Icon(Icons.Filled.Edit, contentDescription = "Editar proveedor ${provider.name}")
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    )
}