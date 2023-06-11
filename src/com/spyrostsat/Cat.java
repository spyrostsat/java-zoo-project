package com.spyrostsat;

public class Cat extends Animal {

    public Cat(int id, String name, String category, double weight, int max_age, Gender gender, AnimalType animalType) {
        super(id, name, category, weight, max_age, gender, animalType);
    }

    @Override
    public void feed() {
        System.out.println("The cat " + getName() + " is being fed...");
    }
}
