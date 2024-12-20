package com.example.testforme.presentation.screens.singup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun SingUp(controller: NavHostController) {

    val ViewModel = viewModel { singupviewmodel() }
    val state = ViewModel.state
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        ViewModel.context = context
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text("Регистрация")
        Spacer(modifier = Modifier.height(30.dp))
        Text("Email")
        TextFieldSignUPPass(state.email, "Email") { ViewModel.updatestate(state.copy(email = it)) }
        Spacer(modifier = Modifier.height(30.dp))
        Text("Password")
        TextFieldSignUPPass(
            state.password,
            "Password"
        ) { ViewModel.updatestate(state.copy(password = it)) }

        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { ViewModel.SignUpViewModel(controller) })
        {Text("Зарегестрироваться")}
    }

}

@Composable
fun TextFieldSignUPPass(value: String, placeholder: String, onvaluechange: (String) -> Unit) {

    TextField(
        value = value,
        onValueChange = { onvaluechange(it) },
        modifier = Modifier.fillMaxWidth(),
        placeholder = { placeholder }
    )
}
