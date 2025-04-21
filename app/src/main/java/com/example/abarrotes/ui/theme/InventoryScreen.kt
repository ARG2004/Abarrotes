package com.example.abarrotes.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.abarrotes.R


data class ProductItem(val name: String, val stock: Int, val price: Double)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InventoryScreen(navController: NavController) {
    val products = remember {
        mutableStateOf(
            listOf(
                ProductItem("Coca-cola 600ml", 50, 20.0),
                ProductItem("Galletas Oreo 114g", 30, 15.50),
                ProductItem("Gansito 50g", 45, 12.0),
                ProductItem("LaLa deslactosada 1L", 25, 35.0),
                ProductItem("Sabritas (Naturales) 160g", 60, 22.80)
                // Agrega más productos aquí
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Menú de inventario") },
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Implementar acción del menú */ }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menú")
                    }
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(navController = navController, currentRoute = "inventory")
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = "", // TODO: Implementar estado de búsqueda
                onValueChange = { /* TODO: Implementar lógica de búsqueda */ },
                label = { Text("Buscar producto") },
                leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Buscar") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))

            Column {
                products.value.forEach { product ->
                    ProductRow(product = product)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@SuppressLint("DefaultLocale")
@Composable
fun ProductRow(product: ProductItem) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded }
            .padding(vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(product.name, modifier = Modifier.weight(1f))
            Icon(Icons.Filled.ArrowDropDown, contentDescription = if (isExpanded) "Ocultar" else "Mostrar")
        }
        if (isExpanded) {
            Spacer(modifier = Modifier.height(8.dp))
            Text("Stock: ${product.stock} pz", fontSize = 14.sp)
            Text("Precio: $${String.format("%.2f", product.price)}", fontSize = 14.sp)
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String) {
    NavigationBar {
        val items = listOf(
            BottomNavItem("Inicio", R.drawable.home_icon, "home") { navController.navigate("home") },
            BottomNavItem("Inventario", R.drawable.inventory_icon, "inventory") { navController.navigate("inventory") },
            BottomNavItem("Ventas", R.drawable.sales_icon, "sales") { /* TODO: Navegar a ventas */ },
            BottomNavItem("Usuario", R.drawable.users_icon, "user") { /* TODO: Navegar a usuario */ }
        )
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painterResource(id = item.iconResId),
                        contentDescription = item.title,
                        modifier = Modifier.size(24.dp) // Ajusta el tamaño del icono
                    )
                },
                label = { Text(item.title, fontSize = 12.sp) },
                selected = currentRoute == item.route,
                onClick = item.onClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InventoryScreenPreview() {
    val navController = rememberNavController()
    InventoryScreen(navController = navController)
}