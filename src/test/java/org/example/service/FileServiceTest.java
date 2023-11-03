package org.example.service;

import org.apache.commons.io.FileUtils;
import org.example.enums.Path;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class FileServiceTest {

    @Before
    public void createTempFiles(){
        File newFile = new File(Path.PATH_TO_FOLDER.toString() + "test1.json");
        File anotherNewFile = new File(Path.PATH_TO_FOLDER.toString() + "test2.json");

        try {
            newFile.createNewFile();
            anotherNewFile.createNewFile();
        } catch (IOException e) {
            System.out.println("file not created");
            e.printStackTrace();
        }
    }

    @After
    public void deleteTempFiles(){
        File directory = new File(Path.PATH_TO_FOLDER.toString());
        try {
            FileUtils.cleanDirectory(directory);
        } catch (IOException e) {
            System.out.println("cannot clean directory");
            e.printStackTrace();
        }
    }

    @Test
    public void testGetFiles() {
        List<File> files = FileService.getFiles(Path.PATH_TO_FOLDER.toString());
        int amountOfFiles = files.size();
        int expectedAmountOfFiles = 2;

        Assert.assertEquals(expectedAmountOfFiles, amountOfFiles);
    }

    @Test
    public void testRemove() {
        FileService.remove(new File(Path.PATH_TO_FOLDER.toString() + "test1.json"));
        int amountOfFiles = new File(Path.PATH_TO_FOLDER.toString()).listFiles().length;
        int expectedAmountOfFiles = 1;

        Assert.assertEquals(expectedAmountOfFiles, amountOfFiles);
    }
}
