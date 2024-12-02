package com.example.asu2

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Menu : AppCompatActivity() {

    var cameraRequestCode: Int = 1

    var classifier: Classifier? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button: Button = findViewById(R.id.button5)

        button.setOnClickListener {
            val intent = Intent(this, ListAnimal::class.java)
            startActivity(intent)

        }

        classifier = Classifier(Utils.assetFilePath(this, "mobilenet-v2.pt"))

        val capture: Button = findViewById(R.id.capture)

        capture.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(cameraIntent, cameraRequestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == cameraRequestCode && resultCode == RESULT_OK) {
            val resultView = Intent(this, Result::class.java).apply {
                putExtra("imagedata", data?.extras)
            }

            val imageBitmap = data?.extras?.get("data") as? Bitmap
            val pred = classifier?.predict(imageBitmap)
            resultView.putExtra("pred", pred)

            startActivity(resultView)
        }
    }
}