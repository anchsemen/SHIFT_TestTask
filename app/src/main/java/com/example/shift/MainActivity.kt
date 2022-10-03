package com.example.shift

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val registrationButton = findViewById<Button>(R.id.button)
        registrationButton.setOnClickListener { onClickRegistration() }
    }

    fun onClickRegistration() {     //Кнопка регистрации
        if (dataValidation()) {
            val user = createUser()
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("KEY_NAME", user)
            startActivity(intent)
        }
    }

    private fun createUser(): User = User(      //Сохранение данных пользователя
        surname = findViewById<EditText>(R.id.surnameText).text.toString(), //фамилия
        name = findViewById<EditText>(R.id.nameText).text.toString(),       //имя
        date = findViewById<EditText>(R.id.dateText).text.toString()        //дата
    )

    private fun dataValidation(): Boolean {     //Валидация данных (общая)
        return surnameVerification() && nameVerification() && dateVerification() && passwordVerification()
    }

    private fun surnameValidation(): Boolean {  //Проверка фамилии на корректность
        val surname = findViewById<EditText>(R.id.surnameText)
        return surname.text.length > 2
    }

    private fun surnameVerification(): Boolean {
        if (!surnameValidation()) {
            val text = "Некорректная фамилия, попробуйте ещё раз."
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }
        return surnameValidation()
    }

    private fun nameValidation(): Boolean {     //Проверка имени на корректность
        val name = findViewById<EditText>(R.id.nameText)
        return name.text.length >= 2
    }

    private fun nameVerification(): Boolean {
        if (!nameValidation()) {
            val text = "Некорректное имя, попробуйте ещё раз."
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }
        return nameValidation()
    }

    private fun dateValidation(): Boolean {     //Проверка даты на корректность
        val date = findViewById<EditText>(R.id.dateText)
        return date.text.isNotEmpty()
    }

    private fun dateVerification(): Boolean {
        if (!dateValidation()) {
            val text = "Некорректная дата, попробуйте ещё раз."
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
        }
        return dateValidation()
    }

    private fun passwordValidation(): Int {     //Проверка пароля на корректность
        val password = findViewById<EditText>(R.id.passwordText)
        val confirmPassword = findViewById<EditText>(R.id.confirmPasswordText)
        return if (password.text.length < 6 || !password.text.contains("[A-Za-z0-9]".toRegex())) 1
        else if (password.text.toString() != confirmPassword.text.toString()) 2
        else 0
    }

    private fun passwordVerification(): Boolean {
        return if (passwordValidation() == 1) {
            val text = "Некорректный пароль, попробуйте ещё раз."
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
            false
        } else if (passwordValidation() == 2) {
            val text = "Пароли не совпадают, попробуйте ещё раз."
            Toast.makeText(applicationContext, text, Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }
}