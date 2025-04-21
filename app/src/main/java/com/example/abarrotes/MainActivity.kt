package com.example.abarrotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.abarrotes.ui.theme.AbarrotesTheme
import com.example.abarrotes.ui.theme.DeleteProductScreen
import com.example.abarrotes.ui.theme.ForgotPasswordScreen
import com.example.abarrotes.ui.theme.Home
import com.example.abarrotes.ui.theme.LoginScreen
import com.example.abarrotes.ui.theme.NewPasswordScreen
import com.example.abarrotes.ui.theme.ScannerScreen
import com.example.abarrotes.ui.theme.PayScreen
import com.example.abarrotes.ui.theme.InventoryScreen
import com.example.abarrotes.ui.theme.NewProductScreen
import com.example.abarrotes.ui.theme.ProvidersScreen
import com.example.abarrotes.ui.theme.RegisterUserScreen
import com.example.abarrotes.ui.theme.SalesScreen
import com.example.abarrotes.ui.theme.SettingsScreen
import com.example.abarrotes.ui.theme.UserScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AbarrotesTheme {
                AppNavigation() // Llamamos a la función que configura la navegación
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController() // Creamos una instancia del NavController
    NavHost(navController = navController, startDestination = "login") { // Configuramos el NavHost
        composable("login") {
            LoginScreen(
                onLoginSuccess = { navController.navigate("home") },
                onForgotPasswordClick = { navController.navigate("forgotPassword") }
            )
        }
        composable("forgotPassword") {
            ForgotPasswordScreen(onResetLinkSent = { navController.navigate("newPassword") })
        }
        composable("newPassword") {
            NewPasswordScreen(
                onPasswordChangedSuccess = { navController.navigate("home") },
                onPasswordMismatch = {
                    // Opcionalmente, podríamos manejar aquí un estado de error en NewPasswordScreen
                }
            )
        }
        composable("home") { Home(navController = navController) } // Definimos la ruta "home" y su Composable
        composable("register_user") { RegisterUserScreen(navController = navController) }
        composable("new_product") { NewProductScreen(navController = navController) }
        composable("delete_product") { DeleteProductScreen(navController = navController) }
        composable("settings") { SettingsScreen(navController = navController) }
        composable("providers") { ProvidersScreen(navController = navController) }
        composable("pay") { PayScreen(navController = navController) } // Definimos la ruta "pay" y su Composable
        composable("scanner") { ScannerScreen(onClose = { navController.popBackStack() }, navController = navController) } // Definimos la ruta "scanner" y su Composable
        composable("inventory") { InventoryScreen(navController = navController) }
        composable("sales") { SalesScreen(navController = navController) }
        composable("user") { UserScreen(navController = navController) }
    }
}