package org.example.service;

import org.example.entities.Animal;
import org.example.serializers.AnimalJSONSerializer;

import java.io.File;
import java.util.List;

public class AnimalService {
    private final AnimalJSONSerializer serializer = new AnimalJSONSerializer();

    public void add(String name, String breed, int age) {
        serializer.serialize(new Animal(name, breed, age));
    }

    public List<Animal> showAll() {
        return serializer.deserializeAll();
    }

    public boolean remove(String fullFileName) {
        return FileService.remove(new File(fullFileName));
    }
}
