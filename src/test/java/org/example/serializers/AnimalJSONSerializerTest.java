package org.example.serializers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.apache.commons.io.FileUtils;
import org.example.entities.Animal;
import org.example.enums.Path;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AnimalJSONSerializerTest {

    @After
    public void deleteTempFiles() {
        File directory = new File(Path.PATH_TO_FOLDER.toString());
        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            System.out.println("cannot clean directory");
            e.printStackTrace();
        }
    }

    @Test
    public void testSerialize() {
        Animal dog = new Animal("Jessie", "Jack russel-terrier", 1);
        AnimalJSONSerializer serializer = new AnimalJSONSerializer();

        serializer.serialize(dog);

        int amountOfFiles = new File(Path.PATH_TO_FOLDER.toString()).listFiles().length;
        int expectedAmountOfFiles = 1;

        Assert.assertEquals(expectedAmountOfFiles, amountOfFiles);
    }

    @Test
    public void testSerializedName() {
        Animal dog = new Animal("Jessie", "Jack russel-terrier", 1);
        AnimalJSONSerializer serializer = new AnimalJSONSerializer();

        serializer.serialize(dog);

        File dogSerialized = new File(Path.PATH_TO_FOLDER.toString()).listFiles()[0];
        String fileName = dogSerialized.getName();
        String expectedFileName = dog.getName() + "[" + dog.hashCode() + "]" + ".json";

        Assert.assertEquals(expectedFileName, fileName);
    }

    @Test
    public void testDeserializeAll() {
        AnimalJSONSerializer serializer = new AnimalJSONSerializer();
        JsonMapper mapper = new JsonMapper();
        Animal testAnimal = new Animal("testDog", "tDog", 1);
        Animal anotherTestAnimal = new Animal("testDog2", "tDog2", 2);

        try {
            mapper.writeValue(new File(Path.PATH_TO_FOLDER +
                    testAnimal.getName() +
                    "[" + testAnimal.hashCode() + "]" + ".json"), testAnimal);
            mapper.writeValue(new File(Path.PATH_TO_FOLDER +
                    anotherTestAnimal.getName() +
                    "[" + anotherTestAnimal.hashCode() + "]" + ".json"), anotherTestAnimal);
        } catch (IOException e) {
            System.out.println("cannot to write file");
            e.printStackTrace();
        }

        List<Animal> animals = serializer.deserializeAll();
        Animal expectedTestAnimal = animals.get(1);
        Animal expectedAnotherTestAnimal = animals.get(0);

        Assert.assertEquals(expectedTestAnimal, testAnimal);
        Assert.assertEquals(expectedAnotherTestAnimal, anotherTestAnimal);
    }
}