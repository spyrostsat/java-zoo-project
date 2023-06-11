package com.spyrostsat;

public class Horse extends Animal {
    public Horse(int id, String name, String category, double weight, int max_age, Gender gender, AnimalType animalType) {
        super(id, name, category, weight, max_age, gender, animalType);
    }

    @Override
    public void feed() {
        System.out.println("The horse " + getName() + " is being fed...");
    }

    @Override
    public void make_noise() {
        System.out.println("Making a horse noise");

    }
}
