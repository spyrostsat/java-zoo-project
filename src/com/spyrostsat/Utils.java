package com.spyrostsat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utils {
    static List<Animal> all_animals = new ArrayList<Animal>();
    static final String FILEPATH = "./src/animals.csv";

    static int Menu() {
        System.out.println("================= MENU =================");
        System.out.println("1. Show all available animals at the zoo");
        System.out.println("2. Add new animal");
        System.out.println("3. Search animal by name");
        System.out.println("4. Search animal by id");
        System.out.println("5. Delete animal by id");
        System.out.println("6. Feed all animals");
        System.out.println("7. Exit program");
        System.out.println("========================================");

        Scanner input = new Scanner(System.in);
        int choice;

        do {
            try {
                System.out.print("Enter your choice (1-7): ");
                choice = Integer.parseInt(input.nextLine());
                if (choice >=1 && choice <=7)
                    break;
                System.out.println("Please enter an integer between 1 and 7.");
            }
            catch (Exception e) {
                System.out.println("Only integers are allowed.");
            }
        } while (true);

        return choice;
    }

    static void choosePath(int choice) {
        switch (choice) {
            case 1:
                Utils.print_all_animals();
                break;
            case 2:
                Utils.add_new_animal();
                break;
            case 3:
                Utils.search_animal_by_name();
                break;
            case 4:
                Utils.search_animal_by_id();
                break;
            case 5:
                Utils.delete_animal_by_id();
                break;
            case 6:
                Utils.feed_animals();
                break;
            case 7:
                Utils.exitProgram();
                break;
        }
    }

    static void read_animals_from_file() {
        try {
            File file = new File(Utils.FILEPATH);
            Scanner input = new Scanner(file);

            int counter = 0;
            String[] line_elements;

            Utils.all_animals.clear();

            while (input.hasNextLine()) {
                if (counter == 0) {
                    counter++; // in line 0 of the file exist the titles of the fields, so we pass over that line
                    continue;
                }
                String line = input.nextLine();

                line_elements = line.split(",");

                String animal_type = line_elements[line_elements.length-1];

                switch (animal_type) {
                    case "dog":
                        Utils.all_animals.add(new Dog(Integer.parseInt(line_elements[0]), line_elements[1], line_elements[2], Double.parseDouble(line_elements[3]), Integer.parseInt(line_elements[4]), (line_elements[5].equals("male")) ? Gender.MALE : Gender.FEMALE, AnimalType.Dog));
                        break;
                    case "cat":
                        Utils.all_animals.add(new Cat(Integer.parseInt(line_elements[0]), line_elements[1], line_elements[2], Double.parseDouble(line_elements[3]), Integer.parseInt(line_elements[4]), (line_elements[5].equals("male")) ? Gender.MALE : Gender.FEMALE, AnimalType.Cat));
                        break;
                    case "horse":
                        Utils.all_animals.add(new Horse(Integer.parseInt(line_elements[0]), line_elements[1], line_elements[2], Double.parseDouble(line_elements[3]), Integer.parseInt(line_elements[4]), (line_elements[5].equals("male")) ? Gender.MALE : Gender.FEMALE, AnimalType.Horse));
                        break;
                    case "bird":
                        Utils.all_animals.add(new Bird(Integer.parseInt(line_elements[0]), line_elements[1], line_elements[2], Double.parseDouble(line_elements[3]), Integer.parseInt(line_elements[4]), (line_elements[5].equals("male")) ? Gender.MALE : Gender.FEMALE, AnimalType.Bird));
                        break;
                }
            }
            input.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    static void print_all_animals() {
        Utils.read_animals_from_file();

        if (Utils.all_animals.size() > 0)
        {
            System.out.println("\nThe zoo has the following animals:");
            for (Animal animal : Utils.all_animals) {
                System.out.println(animal);
            }
            System.out.println("\n");
        }
        else {
            System.out.println("\nThe zoo has no entries yet.\n");
        }
    }

    static void add_new_animal() {
        Utils.read_animals_from_file();

        int new_id = 1;
        boolean empty_file = true;

        if (Utils.all_animals.size() > 0)
        {
            new_id = all_animals.get(all_animals.size() - 1).getId() + 1;
            empty_file = false;
        }

        System.out.println("\nLet's add the new animal!");
        Scanner input = new Scanner(System.in);

        System.out.println("Animal type (1-dog, 2-cat, 3-horse, 4-bird): ");
        int type_number = Integer.parseInt(input.nextLine());
        String animal_type = null;
        switch (type_number) {
            case 1:
                animal_type = "dog";
                break;
            case 2:
                animal_type = "cat";
                break;
            case 3:
                animal_type = "horse";
                break;
            case 4:
                animal_type = "bird";
                break;
        }

        System.out.println("Name: ");
        String name = input.nextLine();

        System.out.println("Category: ");
        String category = input.nextLine();

        System.out.println("Weight: ");
        double weight = Double.parseDouble(input.nextLine());

        System.out.println("Max age: ");
        int max_age = Integer.parseInt(input.nextLine());

        String gender = null;
        System.out.println("Gender (1-male, 2-female): ");
        int gender_number = Integer.parseInt(input.nextLine());

        switch (gender_number) {
            case 1:
                gender = "male";
                break;
            case 2:
                gender = "female";
                break;
        }

        String entry = Integer.toString(new_id) + "," + name + "," + category + "," + Double.toString(weight) + "," + Integer.toString(max_age) + "," + gender + "," + animal_type;

        try {
            FileWriter fileWriter = new FileWriter(Utils.FILEPATH, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            if (!empty_file) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(entry);
            bufferedWriter.flush(); // Write the data to the file
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("The new animal has been added to the zoo!\n");
    }

    static void search_animal_by_name() {
        Utils.read_animals_from_file();
        System.out.println("\nLet's search an animal by its name!");
        System.out.print("Name: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine();

        System.out.println("We found the following animals:");
        boolean found = false;
        for (Animal animal : all_animals) {
            if (animal.getName().equals(name)) {
                found = true;
                System.out.println(animal);
            }
        }
        if (!found) {
            System.out.println("No animal with the name " + name + " was found at the zoo.\n");
        }
        else {
            System.out.println("\n");
        }
    }

    static void search_animal_by_id() {
        Utils.read_animals_from_file();
        System.out.println("\nLet's search an animal by its id!");
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        String id = input.nextLine();

        System.out.println("We found the following animals:");
        boolean found = false;
        for (Animal animal : all_animals) {
            if (Integer.toString(animal.getId()).equals(id)) {
                found = true;
                System.out.println(animal);
                break;
            }
        }
        if (!found) {
            System.out.println("No animal with the ID " + id + " was found at the zoo.\n");
        }
        else {
            System.out.println("\n");
        }
    }

    static void delete_animal_by_id() {
        System.out.println("Let's remove an animal by its id!");
        String id_to_delete;
        System.out.print("ID: ");
        Scanner input = new Scanner(System.in);
        id_to_delete = input.nextLine();

        List<String> lines = new ArrayList<String>();
        
        File file = new File(Utils.FILEPATH);

        try {
            Scanner file_input = new Scanner(file);

            while (file_input.hasNextLine()) {
                String line = file_input.nextLine();
                lines.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int counter = 0;
        int index_to_delete = -1;

        for (String line : lines) {
            String[] line_elements = line.split(",");
            if (line_elements[0].equals(id_to_delete)) {
                index_to_delete = counter;
                break;
            }
            counter++;
        }

        if (index_to_delete != -1) {
            lines.remove(index_to_delete);
            try {
                counter = 0;
                for (String line : lines) {
                    if (counter == 0) {
                        FileWriter fileWriter = new FileWriter(Utils.FILEPATH, false);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.write(line);
                        bufferedWriter.flush(); // Write the data to the file
                    }
                    else {
                        FileWriter fileWriter = new FileWriter(Utils.FILEPATH, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                        bufferedWriter.newLine();
                        bufferedWriter.write(line);
                        bufferedWriter.flush(); // Write the data to the file
                    }
                    counter++;
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }

            System.out.println("\nThe animal with id " + id_to_delete + " has been removed from the zoo.\n");
        }
        else {
            System.out.println("\nNo record with id " + id_to_delete + " was found at the zoo.\n");
        }
    }

    static void feed_animals() {
        Utils.read_animals_from_file();
        System.out.println("\nLet's feed the animals:");
        for (Animal animal : Utils.all_animals) {
            animal.feed();
        }
        System.out.println("\n");
    }

    static void exitProgram() {
        System.out.println("Bye bye...");
        System.exit(0);
    }
}
