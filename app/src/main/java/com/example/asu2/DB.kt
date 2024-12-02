package com.example.asu2

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DB(val context:Context, val factory: SQLiteDatabase.CursorFactory?) :
    SQLiteOpenHelper(context, "app", factory, 1){

    override fun onCreate(db: SQLiteDatabase?) {
        val query = "CREATE TABLE animals (id INT PRIMARY KEY, image TEXT, title TEXT, weight INT, height INT, age INT)"
        db!!.execSQL(query)

        val animals = DB2().getAnimals()
        for (x in animals) {
            val id = x.id
            val image = x.image
            val title = x.title
            val weight = x.weight
            val height = x.height
            val age = x.age

            db.execSQL("INSERT INTO animals (id, image, title, weight, height, age) VALUES ('$id','$image','$title','$weight','$height','$age')");
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        var query = "DROP TABLE IF EXISTS animals"
        db!!.execSQL(query)
        onCreate(db)
    }

    fun addAnimal(x:Animal){
        val id = x.id
        val image = x.image
        val title = x.title
        val weight = x.weight
        val height = x.height
        val age = x.age

        this.readableDatabase.execSQL("INSERT INTO animals (id, image, title, weight, height, age) VALUES ('$id','$image','$title','$weight','$height','$age')");

    }



    @SuppressLint("Range", "Recycle")
    fun getAnimals(): List<Animal>
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM animals", null)

        val animals = mutableListOf<Animal>()
        if (result.moveToFirst()) {
            do {
                val id = result.getInt(result.getColumnIndex("id"))
                val image = result.getString(result.getColumnIndex("image"))
                val title = result.getString(result.getColumnIndex("title"))
                val weight = result.getInt(result.getColumnIndex("weight"))
                val height = result.getInt(result.getColumnIndex("height"))
                val age = result.getInt(result.getColumnIndex("age"))
                animals.add(Animal(id, image, title, weight, height, age))
            } while (result.moveToNext())
        }

        return animals
    }

    @SuppressLint("Range")
    fun getAnimal(id:Int): Animal?
    {
        val db = this.readableDatabase

        val result = db.rawQuery("SELECT * FROM animals WHERE id = '$id'", null)

        if (result.moveToFirst()) {
            val animal = Animal(
                id = result.getInt(result.getColumnIndex("id")),
                image = result.getString(result.getColumnIndex("image")),
                title = result.getString(result.getColumnIndex("title")),
                weight = result.getInt(result.getColumnIndex("weight")),
                height = result.getInt(result.getColumnIndex("height")),
                age = result.getInt(result.getColumnIndex("age"))
            )
            result.close()
            return animal
        }
        return null
    }

}