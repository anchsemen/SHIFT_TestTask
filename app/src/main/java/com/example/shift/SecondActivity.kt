package com.example.shift

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val buttonWelcome = findViewById<Button>(R.id.buttonWelcome)
        buttonWelcome.setOnClickListener { onClickWelcome() }
    }

    private fun onClickWelcome() {  //Кнопка приветствия
        val user = intent.getSerializableExtra("KEY_NAME") as User
        val text = "Добро пожаловать в наше приложение, ${user.surname} ${user.name}!"
        Toast.makeText(applicationContext, text, Toast.LENGTH_LONG).show()
    }
}
