package com.javaops.webapp;

import com.javaops.webapp.model.Resume;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MainCollection {
    private static final String UUID_1 = "uuid1";
    private static final Resume resume1 = new Resume(UUID_1, "fullName1");

    private static final String UUID_2 = "uuid2";
    private static final Resume resume2 = new Resume(UUID_2, "fullName2");

    private static final String UUID_3 = "uuid3";
    private static final Resume resume3 = new Resume(UUID_3, "fullName3");

    private static final String UUID_4 = "uuid4";
    private static final Resume resume4 = new Resume(UUID_4, "fullName4");

    public static void main(String[] args) {
        Collection<Resume> collection = new ArrayList<>();
        collection.add(resume1);
        collection.add(resume2);
        collection.add(resume3);

        for(Resume r : collection) {
            System.out.println(r);
        }

        Map<String, Resume> map = new HashMap<>();
        map.put(UUID_1, resume1);
        map.put(UUID_2, resume2);
        map.put(UUID_3, resume3);
    }
}
