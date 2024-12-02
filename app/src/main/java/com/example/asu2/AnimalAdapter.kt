package com.example.asu2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AnimalAdapter(var animals: List<Animal>, var context: Context) : RecyclerView.Adapter<AnimalAdapter.MyViewHolder>()
{
    class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        val image: ImageView = view.findViewById(R.id.imageListAnimal)
        val title: TextView = view.findViewById(R.id.nameAnimal)
        val description: TextView = view.findViewById(R.id.descriptionAnimal)
        val btn: Button = view.findViewById(R.id.button)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.animal_in_list, parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return animals.count()
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = animals[position].title
        holder.description.text = animals[position].id.toString()

        val imageId = context.resources.getIdentifier(animals[position].image, "drawable", context.packageName)

        holder.image.setImageResource(imageId)

        holder.btn.setOnClickListener {
            val intent = Intent(context, InfoAnimal::class.java)

            intent.putExtra("Id", animals[position].id)

            context.startActivity(intent)
        }
    }

}