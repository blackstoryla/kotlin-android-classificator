package com.example.asu2

class DB2 {
    var animals = arrayListOf<Animal>()

    constructor(){
        animals.add(Animal(1,"a1", "Кабан", 110,110,5))
        animals.add(Animal(2,"a2", "Хорек", 2,20,4))
        animals.add(Animal(3,"a3", "Панда", 150,150,3))
        animals.add(Animal(4,"a4", "Медведь", 240,240,2))
        animals.add(Animal(5,"a5", "Верблюд", 250,250,1))
        animals.add(Animal(6,"a6", "Осел", 140,140, 4))
        animals.add(Animal(7,"a7", "Ягуар", 140,140, 3))
        animals.add(Animal(8,"a8", "Утка", 2,25, 2))
        animals.add(Animal(9,"a9", "Бегемот", 140,140, 1))
        animals.add(Animal(10,"a10", "Жираф", 300,300, 3))
        animals.add(Animal(11,"a11", "Енот", 4,40, 2))
    }

    fun getAnimals(): List<Animal>
    {
        return animals
    }
}