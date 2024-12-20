package com.example.testforme.presentation.screens.SignIn

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.PopUpToBuilder
import com.example.testforme.data.states.SignInState
import com.example.testforme.domain.constants
import com.example.testforme.domain.repository.UserRepository
import com.example.testforme.presentation.navigation.navigationroutes
import com.example.testforme.presentation.validation.IsEmailValid
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email
import kotlinx.coroutines.launch

class SignInViewModel : ViewModel() {


    private val _state = mutableStateOf(SignInState())
    val state: SignInState get() = _state.value

    @SuppressLint("StaticFieldLeak")
    lateinit var context: Context

    fun updatestate(newstate: SignInState) {
        _state.value = newstate
    }


    fun SignUp(controller: NavHostController) {
        viewModelScope.launch {
            try {
                if (state.email.IsEmailValid()) {
                    constants.supabase.auth.signOut()
                    constants.supabase.auth.signInWith(Email)
                    {
                        email = state.email
                        password = state.password
                    }
                    Log.d("sign in success", "Вход в аккаунт прошёл успешно")

                    var CurrentUser = constants.supabase.auth.currentUserOrNull()
                    if (CurrentUser != null) {
                        UserRepository.UUIDCurrentUser = CurrentUser.id
                    }

                    UserRepository.act = 2
                    controller.navigate(navigationroutes.PAGEAFTERSIGNIN) {
                        popUpTo(navigationroutes.SIGNIN)
                        { inclusive = true }
                    }
                } else {
                }
            } catch (e: Exception) {

            }
        }
    }


}