package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int index = Index(r.getUuid());

        if (index < 0) {
            System.out.println("Resume not found");
        } else {
            storage[size] = r;
        }
    }

    public void save(Resume r) {
        if (Index(r.getUuid()) > 0) {
            System.out.println("Resume already written");
        } else if (Index(r.getUuid()) > 10000) {
            System.out.println("Storage full");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        int index = Index(uuid);

        if (index < 0) {
            System.out.println("Resume not found");
        } else {
            return storage[index];
        }
        return null;
    }

    public void delete(String uuid) {
        int index = Index(uuid);

        if (index < 0) {
            System.out.println("Resume not found");
        } else {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    public int Index(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}