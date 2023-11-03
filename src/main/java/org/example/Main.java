package org.example;

import org.example.entities.Animal;
import org.example.enums.Path;
import org.example.service.AnimalService;
import org.example.service.FileService;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        AnimalService service = new AnimalService();
        System.out.println("Hello in pet shelter, select one function: ");
        System.out.println("1. Add pet (add);");
        System.out.println("2. Show all pets (show);");
        System.out.println("3. Take pet from shelter (take);");
        System.out.println("3. Exit (exit);");

        while (true) {
            String menuItem = input.nextLine();
            switch (menuItem) {
                case "add" -> {
                    System.out.println("Name: ");
                    String name = input.nextLine();
                    System.out.println("Breed: ");
                    String breed = input.nextLine();
                    System.out.println("Age: ");
                    int age = input.nextInt();

                    service.add(name, breed, age);
                    System.out.println(name + " added");
                }
                case "show" -> {
                    List<Animal> animals = service.showAll();
                    for (Animal animal : animals) {
                        System.out.println(animal);
                    }
                }
                case "take" -> {
                    boolean remove = false;
                    List<File> files = FileService.getFiles(Path.PATH_TO_FOLDER.toString());
                    System.out.println("Please write animal id to take from shelter");
                    for (File file : files) {
                        int endName = file.getPath().lastIndexOf("]") + 1;
                        int startName = file.getPath().lastIndexOf("\\") + 1;
                        String animalNameWithID = file.getPath().substring(startName, endName);
                        System.out.println(animalNameWithID);
                    }
                    int animalID = input.nextInt();
                    for (File file : files) {
                        String fullFileName = file.getPath();
                        if (fullFileName.contains(String.valueOf(animalID))) {
                            remove = service.remove(fullFileName);
                        }
                    }
                    System.out.println(remove ? "animal taked from shelter" : "cannot find animal");
                }
                case "exit" -> System.exit(0);
                default -> System.out.println("Chose correct menu item(add, show, take, exit)");
            }
        }
    }
}
