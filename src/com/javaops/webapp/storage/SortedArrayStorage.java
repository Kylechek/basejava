package com.javaops.webapp.storage;

import com.javaops.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveElement(Resume resume, String uuid) {
        int index = -getIndex(uuid) - 1;
        System.arraycopy(storage, index,storage,index +1,size - index);
        storage[index] = resume;
    }

    @Override
    protected void deleteElement(int index) {
        int numMoved = size - index -1;
        if(numMoved > 0) {
            System.arraycopy(storage, index + 1, storage, index, numMoved);
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
