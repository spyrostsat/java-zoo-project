package com.spyrostsat;


public abstract class Animal implements IAnimal {
    private int id;
    private String name;
    private String category;
    private double weight;
    private int max_age;
    private Gender gender;
    private AnimalType animalType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getMax_age() {
        return max_age;
    }

    public void setMax_age(int max_age) {
        this.max_age = max_age;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Animal(int id, String name, String category, double weight, int max_age, Gender gender, AnimalType animalType) {
        setId(id);
        setName(name);
        setCategory(category);
        setWeight(weight);
        setMax_age(max_age);
        setGender(gender);
        setAnimalType(animalType);
    }

    public void print_animal_features() {
        String features = "id = " + getId() + "\nname = " + getName() + "\ncategory = " + getCategory() + "\nweight = " + getWeight() + "\nmax age = " + getMax_age() + "\ngender = " + getGender();
        System.out.println("========" + getAnimalType() + "FEATURES========");
        System.out.println(features);
        System.out.println("============================");
    }

    @Override
    public void feed() {
    }

    @Override
    public String toString() {
        return getAnimalType() + "{id = " + getId() + ", name = " + getName() + ", category = " + getCategory() + ", weight = " + getWeight() + ", max age = " + getMax_age() + ", gender = " + getGender() + "}";
    }
}
