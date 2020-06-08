package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.ExistStorageException;
import com.javaops.webapp.exeption.NotExistStorageException;
import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage{
    public final int STORAGE_LIMIT = 10000;
    public Resume[] storage = new Resume[STORAGE_LIMIT];
    public int size;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume resume) {
        String uuid = resume.getUuid();
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(resume.getUuid());
        } else {
            storage[index] = resume;
        }
    }

    public void save(Resume resume) {
        String uuid = resume.getUuid();

        if (getIndex(uuid) > 0) {
            throw new ExistStorageException(uuid);
        } else if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveElement(resume, uuid);
            size++;
        }
    }

    protected abstract void saveElement(Resume resume, String uuid);

    public void delete(String uuid) {
        int index = getIndex(uuid);
        storage[size - 1] = null;

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        } else {
            deleteElement(index);
            size--;
        }
    }

    protected abstract void deleteElement(int index);

    public int size() {
        return size;
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);

        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract int getIndex(String uuid);
}