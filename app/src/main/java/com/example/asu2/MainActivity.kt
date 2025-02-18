package com.example.asu2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val userLogin:EditText = findViewById(R.id.user_login)
        val userPassword:EditText = findViewById(R.id.user_password)
        val button:Button = findViewById(R.id.button_reg)


        button.setOnClickListener {
            val login = userLogin.text.toString().trim()
            val password = userPassword.text.toString().trim()

            if (login == "" || password == "")
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_LONG).show()

            else if (login == "admin" && password == "admin"){
                val intent = Intent(this, Menu::class.java)

//                    val intent = Intent(this, ListAnimal::class.java)
                startActivity(intent)
            }

            else {
                Toast.makeText(this, "Неверный пароль, попробуйте ещё раз", Toast.LENGTH_LONG).show()
            }
        }
    }
}