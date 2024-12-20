package com.example.testforme.presentation.screens.singup

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.testforme.data.models.user
import com.example.testforme.data.states.SignUpState
import com.example.testforme.domain.constants
import com.example.testforme.domain.repository.UserRepository
import com.example.testforme.presentation.navigation.navigationroutes
import com.example.testforme.presentation.validation.IsEmailValid
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.launch
import java.sql.Timestamp

class singupviewmodel : ViewModel() {

    private val _state = mutableStateOf(SignUpState())
    val state: SignUpState get() = _state.value

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun updatestate(newstate: SignUpState) {
        _state.value = newstate
    }

    fun SignUpViewModel(controller: NavHostController) {

        viewModelScope.launch {
            try {
                if (state.email.IsEmailValid()) {
                    constants.supabase.auth.signUpWith(Email) {
                        email = state.email
                        password = state.password
                    }
                    var currentuser = constants.supabase.auth.currentUserOrNull()

                    if (currentuser != null) {
                        constants.supabase.from("User2TestForMe").insert(
                            user(
                                id = currentuser.id,
                                email = state.email,
                                created_at = Timestamp(System.currentTimeMillis()).toString()
                            )
                        )
                        Log.d("create user", "супер")

                        UserRepository.act  = 2
                        controller.navigate(navigationroutes.PAGEAFTERSIGNUP){
                            popUpTo(navigationroutes.SIGNUP) {
                                inclusive = true
                            }
                        }
                    }
                } else {
                    Toast.makeText(context, "Неверный формат почты", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.d("sign in error", e.message.toString())
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }

    }
}