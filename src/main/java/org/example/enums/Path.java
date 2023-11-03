package org.example.enums;

public enum Path {

    PATH_TO_FOLDER("src/main/resources/");

    private String path;

    Path(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return path;
    }
}
