package com.javaops.webapp;

import java.io.File;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("./src/com/javaops/webapp");
        findFiles(dir);
    }
    public static void findFiles(File dir) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(file.getName());
                } else if (file.isDirectory()) {
                    findFiles(file);
                }
            }
        }
    }
}

