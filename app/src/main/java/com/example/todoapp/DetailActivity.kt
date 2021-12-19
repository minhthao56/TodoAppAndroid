package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val buttonBackDetail = findViewById<ImageView>(R.id.buttonBackDetail)
        buttonBackDetail.setOnClickListener {
            finish()

        }
    }
}