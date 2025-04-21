package com.example.abarrotes.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.abarrotes.R

data class BottomNavItem(val title: String, val iconResId: Int, val route: String, val onClick: () -> Unit)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) { // Recibimos el NavController

    val showMenu = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Inventario Super Lina") },
                navigationIcon = {
                    IconButton(onClick = { showMenu.value = !showMenu.value }) {
                        Icon(Icons.Filled.Menu, contentDescription = "Menú de opciones")
                    }
                    DropdownMenu(
                        expanded = showMenu.value,
                        onDismissRequest = { showMenu.value = false }
                    ) {
                        DropdownMenuItem(text = { Text("Registrar Usuario") }, onClick = { navController.navigate("register_user") ; showMenu.value = false })
                        DropdownMenuItem(text = { Text("Nuevo Producto") }, onClick = { navController.navigate("new_product"); showMenu.value = false })
                        DropdownMenuItem(text = { Text("Eliminar Producto") }, onClick = { navController.navigate("delete_product"); showMenu.value = false })
                        DropdownMenuItem(text = { Text("Proveedores") }, onClick = { navController.navigate("providers"); showMenu.value = false })
                        DropdownMenuItem(text = { Text("Configuración") }, onClick = { navController.navigate("settings"); showMenu.value = false })
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val items = listOf(
                    BottomNavItem("Inicio", R.drawable.home_icon, "home") { navController.navigate("home") },
                    BottomNavItem("Inventario", R.drawable.inventory_icon, "inventory") { navController.navigate("inventory") },
                    BottomNavItem("Ventas", R.drawable.sales_icon, "sales") { navController.navigate("sales") },
                    BottomNavItem("Usuario", R.drawable.users_icon, "user") { navController.navigate("user") },
                )
                items.forEach { item ->
                    NavigationBarItem(
                        icon = {
                            Image(
                                painter = painterResource(id = item.iconResId),
                                contentDescription = item.title,
                                modifier = Modifier.size(45.dp)
                            )
                        },
                        label = { Text(item.title) },
                        selected = item.route == "home", // TODO: Manejar selección actual
                        onClick = item.onClick
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 45.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        value = "", // TODO: Vincular al estado de búsqueda
                        onValueChange = { /* TODO: Manejar cambio de texto de búsqueda */ },
                        label = { Text("Buscar") },
                        leadingIcon = {
                            Icon(Icons.Filled.Search, contentDescription = "Buscar productos")
                        },
                        modifier = Modifier.weight(1f)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(onClick = { /* TODO: Manejar venta completa */ }) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.sale_complete_icon),
                                contentDescription = "Venta completa",
                                modifier = Modifier.size(45.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Venta completa")
                        }
                    }
                }

                Spacer(modifier = Modifier.height(86.dp))

                Button(
                    onClick = { navController.navigate("scanner") }, // Navegamos a la ruta "scanner"
                    modifier = Modifier.fillMaxWidth(0.7f)
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(
                            painter = painterResource(id = R.drawable.scanner),
                            contentDescription = "Escáner",
                            modifier = Modifier.size(100.dp)
                        )
                        Text("Escáner", fontSize = 24.sp)
                    }
                }

                // Puedes agregar más elementos visuales aquí
            }
        }
    }
}

@Composable
fun HomePreview() {
    // Para la Preview, necesitamos proporcionar un NavController falso
    val navController = rememberNavController()
    Home(navController = navController)
}