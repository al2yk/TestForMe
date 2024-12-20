package com.example.testforme.presentation.validation

import android.text.TextUtils

fun String.IsEmailValid():Boolean
{
    return !TextUtils.isEmpty(this) && android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}