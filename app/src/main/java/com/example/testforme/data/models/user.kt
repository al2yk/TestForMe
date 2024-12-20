package com.example.testforme.data.models

import android.provider.ContactsContract.CommonDataKinds.Email
import kotlinx.serialization.Serializable

@Serializable
data class user (

    var id:String,
    var email: String,
    var created_at: String
)