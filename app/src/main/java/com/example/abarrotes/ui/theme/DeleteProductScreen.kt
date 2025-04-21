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
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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

// Datos de ejemplo de productos (reemplazar con tu lógica real)
data class ProductToDelete(val id: Int, val name: String, var isSelected: Boolean = false)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteProductScreen(navController: NavController) {
    val products = remember {
        mutableStateListOf(
            ProductToDelete(1, "Ruffles Flamin Hot 185g"),
            ProductToDelete(2, "Ruffles Swrich 185g"),
            ProductToDelete(3, "Ruffles Original 385g"),
            ProductToDelete(4, "Ruffles Queso 290g"),
            ProductToDelete(5, "Ruffles Cheddar 240g"),
            ProductToDelete(6, "Ruffles Salsa negra 185g")
        )
    }

    var searchText by remember { mutableStateOf("") }
    val filteredProducts = remember(products, searchText) {
        if (searchText.isBlank()) {
            products
        } else {
            products.filter { it.name.contains(searchText, ignoreCase = true) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Eliminar Producto") })
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
                    label = { Text("Producto a eliminar") },
                    leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar producto") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                filteredProducts.forEach { product ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = product.isSelected,
                            onCheckedChange = { isChecked ->
                                val index = products.indexOf(product)
                                if (index != -1) {
                                    products[index] = product.copy(isSelected = isChecked)
                                }
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(product.name)
                    }
                }
                Spacer(modifier = Modifier.weight(1f)) // Empuja el botón hacia abajo
                Button(
                    onClick = {
                        val productsToDelete = products.filter { it.isSelected }
                        // TODO: Implementar lógica para eliminar los productos seleccionados
                        println("Eliminando productos: $productsToDelete")
                        navController.popBackStack() // Volver a la pantalla anterior después de (intentar) eliminar
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = products.any { it.isSelected } // Habilitar solo si hay productos seleccionados
                ) {
                    Text("Eliminar")
                }
            }
        }
    )
}