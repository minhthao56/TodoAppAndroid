package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        var inputPassword = findViewById<EditText>(R.id.inputPassword)
        inputPassword.hint = "Password"
        var inputEmail = findViewById<EditText>(R.id.inputEmail)
        var buttonLogin = findViewById<Button>(R.id.buttonLogin)
        buttonLogin.text = "SIGN IN"

    }
}