package com.javaops.webapp.storage;

import com.javaops.webapp.exeption.StorageException;
import com.javaops.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage extends AbstractStorage {
    public static final int STORAGE_LIMIT = 10000;
    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;

    @Override
    protected void doUpdate(Resume resume, Object searchKey) {
        storage[(Integer) searchKey] = resume;
    }

    @Override
    protected void doSave(Resume resume, Object searchKey) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", resume.getUuid());
        } else {
            saveElement(resume, (Integer) searchKey);
            size++;
        }
    }

    @Override
    public void doDelete(Object searchKey) {
        deleteElement((Integer) searchKey);
        storage[size - 1] = null;
        size--;
    }

    @Override
    public Resume doGet(Object searchKey) {
        return storage[(Integer) searchKey];
    }

    @Override
    protected boolean isExist(Object searchKey) {
        return (Integer) searchKey >= 0;
    }

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract Integer getSearchKey(String uuid);
    protected abstract void saveElement(Resume resume, int index);
    protected abstract void deleteElement(int index);
}