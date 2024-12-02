package com.example.asu2

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InfoAnimal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_info_animal)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val title:TextView = findViewById(R.id.AnimalName)
        val weight:TextView = findViewById(R.id.AnimalWeight)
        val height:TextView = findViewById(R.id.AnimalHeight)
        val age:TextView = findViewById(R.id.AnimalAge)
        val img:ImageView = findViewById(R.id.AnimalImage)

        val id:Int = intent.getIntExtra("Id",0)
        val animal = DB(this, null).getAnimal(id)

        if (animal != null) {
            title.text = animal.title
            weight.text = animal.weight.toString() + " кг"
            height.text = animal.height.toString() + " см"
            if (animal.age % 10 == 1 && animal.age / 10 % 10 != 1) {
                age.text = animal.age.toString() + " год"
            }
            else if (animal.age % 10 >= 2 &&animal.age % 10 <= 4 && animal.age / 10 % 10 != 1) {
                age.text = animal.age.toString() + " года"
            }
            else {
                age.text = animal.age.toString() + " лет"
            }

            val imageId = this.resources.getIdentifier(animal.image, "drawable", this.packageName)
            img.setImageResource(imageId)
        }

    }
}