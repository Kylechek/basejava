package com.javaops.webapp;

import java.io.*;

public class MainFile {
    public static void main(String[] args) {
        File dir = new File("./src/com/");
        findFiles(dir, "");
    }

    public static void findFiles(File dir, String s) {
        File[] files = dir.listFiles();

//        String hot = "Hi";
//        int b = 0;
//
//        try {
//            FileOutputStream file = new FileOutputStream("C:/Users/ikoni/Documents/1.txt");
//            file.write(hot.getBytes());
//            file.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String s = null;
//        try {
//            FileReader file1 = new FileReader("C:/Users/ikoni/Documents/1.txt");
//            BufferedReader br = new BufferedReader(file1);
//            while ((s = br.readLine()) != null) {
//                System.out.println(s);
//            }
//            br.close();
//
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(s + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println(s + file.getName());
                    findFiles(file, s + " ");
                }
            }
        }
    }
}

