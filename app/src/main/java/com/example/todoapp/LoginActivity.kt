package com.example.todoapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val autoLogin = true
        if (autoLogin){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        val inputPassword = findViewById<EditText>(R.id.inputPassword)
        val inputEmail = findViewById<EditText>(R.id.inputEmail)
        val buttonLogin = findViewById<Button>(R.id.buttonLogin)
        val textForgotPassword = findViewById<TextView>(R.id.textForgotPassword)

        inputPassword.hint = "Password"
        inputPassword.inputType =InputType.TYPE_TEXT_VARIATION_PASSWORD
        buttonLogin.text = "SIGN IN"
        textForgotPassword.setOnClickListener {
            Toast.makeText(this, "TEST", Toast.LENGTH_SHORT).show()

        }



        buttonLogin.setOnClickListener {

            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()

            if (email =="" && password ==""){
                Toast.makeText(this, "Please add full info", Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }

        }



    }
}