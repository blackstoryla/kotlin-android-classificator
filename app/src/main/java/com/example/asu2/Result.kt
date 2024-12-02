package com.example.asu2

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Result : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val imageBitmap: Bitmap? = intent.getBundleExtra("imagedata")?.get("data") as? Bitmap
        val pred: String? = intent.getStringExtra("pred")

        val imageView: ImageView = findViewById(R.id.image)
        imageView.setImageBitmap(imageBitmap)

        val textView: TextView = findViewById(R.id.label)
        textView.text = pred
    }
}