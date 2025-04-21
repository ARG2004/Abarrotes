package com.example.abarrotes.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Configuración") })
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    "Permisos",
                    style = MaterialTheme.typography.headlineSmall
                )
                Spacer(modifier = Modifier.height(16.dp))

                var cameraPermission by remember { mutableStateOf(true) }
                PermissionRow(
                    label = "Cámara",
                    checked = cameraPermission,
                    onCheckedChange = { cameraPermission = it }
                )

                var notificationsPermission by remember { mutableStateOf(false) }
                PermissionRow(
                    label = "Notificaciones",
                    checked = notificationsPermission,
                    onCheckedChange = { notificationsPermission = it }
                )

                var storagePermission by remember { mutableStateOf(true) }
                PermissionRow(
                    label = "Almacenamiento",
                    checked = storagePermission,
                    onCheckedChange = { storagePermission = it }
                )

                var backupPermission by remember { mutableStateOf(true) }
                PermissionRow(
                    label = "Copia de seguridad",
                    checked = backupPermission,
                    onCheckedChange = { backupPermission = it }
                )
            }
        }
    )
}

@Composable
fun PermissionRow(label: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onCheckedChange(!checked) },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, modifier = Modifier.weight(1f))
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange
        )
    }
}