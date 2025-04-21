package com.example.abarrotes.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RegisterUserScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Datos del nuevo empleado")
        Spacer(modifier = Modifier.height(16.dp))

        var nombre by remember { mutableStateOf("") }
        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        var apellidos by remember { mutableStateOf("") }
        OutlinedTextField(
            value = apellidos,
            onValueChange = { apellidos = it },
            label = { Text("Apellidos") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        var colonia by remember { mutableStateOf("") }
        OutlinedTextField(
            value = colonia,
            onValueChange = { colonia = it },
            label = { Text("Colonia") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        var calleNumero by remember { mutableStateOf("") }
        OutlinedTextField(
            value = calleNumero,
            onValueChange = { calleNumero = it },
            label = { Text("Calle y número") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(8.dp))

        var email by remember { mutableStateOf("") }
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("E-mail") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var contrasena by remember { mutableStateOf("") }
        OutlinedTextField(
            value = contrasena,
            onValueChange = { contrasena = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var confirmarContrasena by remember { mutableStateOf("") }
        OutlinedTextField(
            value = confirmarContrasena,
            onValueChange = { confirmarContrasena = it },
            label = { Text("Confirmar Contraseña") },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var numTelefono by remember { mutableStateOf("") }
        OutlinedTextField(
            value = numTelefono,
            onValueChange = { numTelefono = it },
            label = { Text("Num. Teléfono") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(8.dp))

        var rfc by remember { mutableStateOf("") }
        OutlinedTextField(
            value = rfc,
            onValueChange = { rfc = it },
            label = { Text("RFC") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
        )
        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancelar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = {
                    // Aquí debes añadir la lógica para validar el formulario
                    // y registrar al usuario.
                    println("Registrando usuario con: $nombre, $apellidos, ...")
                    // TODO: Add validations
                    println("Validating data")
                    if(validateData(nombre, apellidos, colonia, calleNumero, email, contrasena, confirmarContrasena, numTelefono, rfc)){
                        println("Data Validated")
                        println("Registering User")
                    }
                    else{
                        println("Data invalid")
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Crear")
            }
        }
    }
}

fun validateData(nombre: String, apellidos:String, colonia: String, calleNumero:String, email: String, contrasena:String, confirmarContrasena: String, numTelefono:String, rfc:String):Boolean{
    if(nombre.isEmpty()|| apellidos.isEmpty()|| colonia.isEmpty()|| calleNumero.isEmpty()|| email.isEmpty()|| contrasena.isEmpty()|| confirmarContrasena.isEmpty()|| numTelefono.isEmpty()|| rfc.isEmpty()){
        return false
    }
    if(contrasena != confirmarContrasena){
        return false
    }
    return true
}