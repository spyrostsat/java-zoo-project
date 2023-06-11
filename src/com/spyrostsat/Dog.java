package com.spyrostsat;

public class Dog extends Animal {

    public Dog(int id, String name, String category, double weight, int max_age, Gender gender, AnimalType animalType) {
        super(id, name, category, weight, max_age, gender, animalType);
    }

    @Override
    public void feed() {
        System.out.println("The dog " + getName() + " is being fed...");
    }
}
