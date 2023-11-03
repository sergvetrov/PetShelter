package org.example.serializers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.example.entities.Animal;
import org.example.enums.Path;
import org.example.service.FileService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AnimalJSONSerializer {
    private final String path = Path.PATH_TO_FOLDER.toString();
    private final JsonMapper mapper = new JsonMapper();

    public void serialize(Animal animal) {
        try {
            mapper.writeValue(new File(path +
                    animal.getName() +
                    "[" + animal.hashCode() + "]" + ".json"), animal);
        } catch (IOException e) {
            System.out.println("Cannot create file");
            e.printStackTrace();
        }
    }

    public List<Animal> deserializeAll() {
        List<Animal> animals = new ArrayList<>();
        List<File> files = FileService.getFiles(path);
        for (File file : files) {
            try {
                animals.add(mapper.readValue(file, Animal.class));
            } catch (IOException e) {
                System.out.println("Cannot read file");
                e.printStackTrace();
            }
        }
        return animals;
    }
}
