package org.example.service;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class FileService {

    public static List<File> getFiles(String pathToFolder) {
        File dir = new File(pathToFolder);
        File[] arrFiles = dir.listFiles();
        return Arrays.asList(arrFiles);
    }

    public static boolean remove(File file) {
        return file.delete();
    }
}
